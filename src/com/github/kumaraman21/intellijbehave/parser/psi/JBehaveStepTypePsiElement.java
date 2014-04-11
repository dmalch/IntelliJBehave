package com.github.kumaraman21.intellijbehave.parser.psi;

import com.intellij.psi.PsiElement;
import org.jbehave.core.steps.StepType;

public interface JBehaveStepTypePsiElement extends PsiElement {
    StepType getStepType();
}
