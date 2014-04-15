package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.github.kumaraman21.intellijbehave.parser.psi.JBehaveStepPsiElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JBehaveStepPsiElementImpl extends ASTWrapperPsiElement implements JBehaveStepPsiElement {
    public JBehaveStepPsiElementImpl(ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return PsiReferenceService.getService().getContributedReferences(this);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        ASTNode stepTextPsiNode = getNode().findChildByType(StoryTypes.STEP_TEXT_PSI_ELEMENT);
        if (stepTextPsiNode != null) {
            return stepTextPsiNode.getPsi();
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        return getNode().findChildByType(StoryTypes.STEP_TEXT_PSI_ELEMENT).getText();
    }

    @Override
    public PsiElement setName(@NotNull String newName) throws IncorrectOperationException {
        ASTNode stepTextPsiNode = getNode().findChildByType(StoryTypes.STEP_TEXT_PSI_ELEMENT);
        if (stepTextPsiNode != null) {
            PsiElementFactory factory = JavaPsiFacade.getInstance(getManager().getProject()).getElementFactory();
            PsiIdentifier newNameIdentifier = factory.createIdentifier(newName);
            getNode().replaceChild(stepTextPsiNode, newNameIdentifier.getNode());
        }
        return this;
    }
}
