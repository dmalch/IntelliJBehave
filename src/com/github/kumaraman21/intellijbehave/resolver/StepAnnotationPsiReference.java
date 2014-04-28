package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StepAnnotationPsiReference extends PsiReferenceBase<PsiLiteralExpression> {
    private final StepPsiElement stepPsiElement;

    public StepAnnotationPsiReference(PsiLiteralExpression element, StepPsiElement stepPsiElement) {
        super(element, TextRange.from(0, element.getTextLength()));
        this.stepPsiElement = stepPsiElement;
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return stepPsiElement;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return EMPTY_ARRAY;
    }
}
