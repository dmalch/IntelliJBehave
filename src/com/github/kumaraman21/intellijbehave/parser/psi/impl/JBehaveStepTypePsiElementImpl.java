package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.github.kumaraman21.intellijbehave.parser.psi.JBehaveStepTypePsiElement;
import com.google.common.collect.ImmutableMap;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.jbehave.core.steps.StepType;

import java.util.Map;

public class JBehaveStepTypePsiElementImpl extends ASTWrapperPsiElement implements JBehaveStepTypePsiElement {
    public final static Map<IElementType, StepType> ELEMENT_TYPE_TO_STEP_MAPPING = ImmutableMap.<IElementType, StepType>builder()
            .put(StoryTypes.GIVEN, StepType.GIVEN)
            .put(StoryTypes.WHEN, StepType.WHEN)
            .put(StoryTypes.THEN, StepType.THEN)
            .build();

    public JBehaveStepTypePsiElementImpl(ASTNode node) {
        super(node);
    }

    @Override
    public StepType getStepType() {
        return ELEMENT_TYPE_TO_STEP_MAPPING.get(getNode().getFirstChildNode().getElementType());
    }
}
