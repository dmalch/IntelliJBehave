package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;
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
                        if (!(element instanceof StepPsiElement)) {
                            return new PsiReference[0];
                        }

                        final StepPsiElement stepPsiElement = (StepPsiElement) element;

                        final StepAnnotationFinder stepAnnotationFinder = new StepAnnotationFinder(stepPsiElement);
                        ScanUtils.iterateInContextOf(stepPsiElement, stepAnnotationFinder);
                        final StepDefinitionAnnotation definitionAnnotation = stepAnnotationFinder.getMatchingAnnotation();
                        final PsiAnnotation annotation = definitionAnnotation.getAnnotation();

                        return new PsiReference[]{new StepPsiReference(annotation, annotation.getTextRange())};
                    }
                }
        );
    }
}
