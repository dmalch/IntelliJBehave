package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.codeInspector.StepUsageFinder;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepMatcher;
import org.jbehave.core.parsers.StepPatternParser;
import org.jbehave.core.steps.StepType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepsInspection.JBEHAVE_ANNOTATIONS;
import static com.github.kumaraman21.intellijbehave.utility.StepTypeMappings.ANNOTATION_TO_STEP_TYPE_MAPPING;
import static com.google.common.collect.FluentIterable.from;
import static com.intellij.patterns.PsiJavaPatterns.literalExpression;
import static com.intellij.patterns.StandardPatterns.string;

public class StepAnnotationPsiReferenceContributor extends PsiReferenceContributor {

    public static final StepPatternParser STEP_PATTERN_PARSER = new RegexPrefixCapturingPatternParser();

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                literalExpression().annotationParam(string().oneOf(JBEHAVE_ANNOTATIONS), "value"),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        final PsiLiteralExpression psiLiteral = (PsiLiteralExpression) element;
                        final PsiAnnotation psiAnnotation = findAnnotationFor(psiLiteral);

                        return from(findStepsFor(psiAnnotation))
                                .filter(stepReferencesInStories(psiLiteral, psiAnnotation))
                                .transform(toStepAnnotationPsiReferences())
                                .toArray(StepAnnotationPsiReference.class);
                    }
                }
        );
    }

    private Function<StoryStepPsiElement, StepAnnotationPsiReference> toStepAnnotationPsiReferences() {
        return new Function<StoryStepPsiElement, StepAnnotationPsiReference>() {
            @Override
            public StepAnnotationPsiReference apply(StoryStepPsiElement stepPsiElement) {
                return new StepAnnotationPsiReference(stepPsiElement, stepPsiElement.getTextRange());
            }
        };
    }

    private Predicate<StoryStepPsiElement> stepReferencesInStories(final PsiLiteralExpression psiLiteral, final PsiAnnotation psiAnnotation) {
        return new Predicate<StoryStepPsiElement>() {
            @Override
            public boolean apply(StoryStepPsiElement stepPsiElement) {
                StepMatcher stepMatcher
                        = STEP_PATTERN_PARSER.parseStep(getStepType(psiAnnotation), String.valueOf(psiLiteral.getValue()));
                return stepMatcher.matches(stepPsiElement.getStepTextPsiElement().getText());
            }
        };
    }

    private PsiAnnotation findAnnotationFor(PsiLiteralExpression psiLiteral) {
        return (PsiAnnotation) psiLiteral.getParent().getParent().getParent();
    }

    private Set<StoryStepPsiElement> findStepsFor(PsiAnnotation psiAnnotation) {
        Project project = psiAnnotation.getProject();
        StepUsageFinder stepUsageFinder = new StepUsageFinder(project);
        ProjectRootManager.getInstance(project).getFileIndex().iterateContent(stepUsageFinder);
        return stepUsageFinder.getStepUsages();
    }

    private StepType getStepType(PsiAnnotation psiAnnotation) {
        return ANNOTATION_TO_STEP_TYPE_MAPPING.get(psiAnnotation.getQualifiedName());
    }
}
