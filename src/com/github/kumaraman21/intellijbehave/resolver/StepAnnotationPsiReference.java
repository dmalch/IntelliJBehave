package com.github.kumaraman21.intellijbehave.resolver;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StepAnnotationPsiReference extends PsiReferenceBase<PsiElement> {
    public StepAnnotationPsiReference(PsiElement element, TextRange range) {
        super(element, range);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        return getElement();
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return EMPTY_ARRAY;
    }
}
