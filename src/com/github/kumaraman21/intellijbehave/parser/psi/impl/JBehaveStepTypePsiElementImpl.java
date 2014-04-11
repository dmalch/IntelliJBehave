package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import com.github.kumaraman21.intellijbehave.parser.psi.JBehaveStepTypePsiElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jbehave.core.steps.StepType;

public class JBehaveStepTypePsiElementImpl extends ASTWrapperPsiElement implements JBehaveStepTypePsiElement {
    public JBehaveStepTypePsiElementImpl(ASTNode node) {
        super(node);
    }

    @Override
    public StepType getStepType() {
        return null;
    }
}
