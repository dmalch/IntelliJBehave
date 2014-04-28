package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.codeInspector.StepUsageFinder;
import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;
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

import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepDeclarationInspection.JBEHAVE_ANNOTATIONS;
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

                        return from(findAllSteps(psiLiteral.getProject()))
                                .filter(stepReferencesInStories(psiLiteral))
                                .transform(toStepAnnotationPsiReferences(psiLiteral))
                                .toArray(StepAnnotationPsiReference.class);
                    }
                }
        );
    }

    private static Function<StepPsiElement, StepAnnotationPsiReference> toStepAnnotationPsiReferences(final PsiLiteralExpression psiLiteral) {
        return new Function<StepPsiElement, StepAnnotationPsiReference>() {
            @Override
            public StepAnnotationPsiReference apply(StepPsiElement stepPsiElement) {
                return new StepAnnotationPsiReference(psiLiteral, stepPsiElement);
            }
        };
    }

    private static Predicate<StepPsiElement> stepReferencesInStories(final PsiLiteralExpression psiLiteral) {
        return new Predicate<StepPsiElement>() {
            @Override
            public boolean apply(StepPsiElement stepPsiElement) {
                final PsiAnnotation psiAnnotation = findAnnotationFor(psiLiteral);
                StepMatcher stepMatcher = STEP_PATTERN_PARSER.parseStep(getStepType(psiAnnotation), String.valueOf(psiLiteral.getValue()));
                boolean matches = stepMatcher.matches(stepPsiElement.getStepText());
                //super slow
                /*if (matches) {
                    StepPsiReference reference = stepPsiElement.getReference();
                    return reference.isReferenceTo(psiLiteral);
                }
                return false;*/
                return matches;
            }
        };
    }

    private static PsiAnnotation findAnnotationFor(PsiLiteralExpression psiLiteral) {
        return (PsiAnnotation) psiLiteral.getParent().getParent().getParent();
    }

    private static Set<StepPsiElement> findAllSteps(Project project) {
        StepUsageFinder stepUsageFinder = new StepUsageFinder(project);
        ProjectRootManager.getInstance(project).getFileIndex().iterateContent(stepUsageFinder);
        return stepUsageFinder.getStepUsages();
    }

    private static StepType getStepType(PsiAnnotation psiAnnotation) {
        return ANNOTATION_TO_STEP_TYPE_MAPPING.get(psiAnnotation.getQualifiedName());
    }
}
