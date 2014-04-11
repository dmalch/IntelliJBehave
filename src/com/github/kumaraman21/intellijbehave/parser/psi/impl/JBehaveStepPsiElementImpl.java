package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import com.github.kumaraman21.intellijbehave.parser.psi.JBehaveStepPsiElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceService;
import org.jetbrains.annotations.NotNull;

public class JBehaveStepPsiElementImpl extends ASTWrapperPsiElement implements JBehaveStepPsiElement {
    public JBehaveStepPsiElementImpl(ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return PsiReferenceService.getService().getContributedReferences(this);
    }
}
