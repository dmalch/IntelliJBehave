package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.github.kumaraman21.intellijbehave.utility.ScanUtils;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class StepPsiReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                psiElement(PsiElement.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        if (!(element instanceof StoryStepPsiElement)) {
                            return new PsiReference[0];
                        }

                        final StoryStepPsiElement storyStepPsiElement = (StoryStepPsiElement) element;

                        final StepAnnotationFinder stepAnnotationFinder = new StepAnnotationFinder(storyStepPsiElement);
                        ScanUtils.iterateInContextOf(storyStepPsiElement, stepAnnotationFinder);
                        final StepDefinitionAnnotation definitionAnnotation = stepAnnotationFinder.getMatchingAnnotation();
                        if (definitionAnnotation != null) {
                            final PsiAnnotation annotation = definitionAnnotation.getAnnotation();

                            return new PsiReference[]{new StoryStepPsiReference(annotation, annotation.getTextRange())};
                        }

                        return new PsiReference[0];
                    }
                }
        );
    }
}
