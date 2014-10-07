/*
 * Copyright 2011-12 Aman Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kumaraman21.intellijbehave.parser;

import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiReference;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.Function;
import org.jbehave.core.steps.StepType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.openapi.util.text.StringUtil.trim;

public class JBehaveStep extends ASTWrapperPsiElement {
    private StepType stepType;

    public JBehaveStep(@NotNull ASTNode node, StepType stepType) {
        super(node);
        this.stepType = stepType;
    }

    @Override
    @NotNull
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this);
    }

    public StepType getStepType() {
        return stepType;
    }

    @Nullable
    public ASTNode getKeyword() {
        return getNode().findChildByType(StoryTokenTypes.STEP_TYPES);
    }

    private static TokenSet TEXT_FILTER = TokenSet.create(StoryTokenTypes.STEP_TEXT, TokenType.WHITE_SPACE);

    public String getStepText() {
        ASTNode[] children = getNode().getChildren(TEXT_FILTER);

        return StringUtil.join(children, new Function<ASTNode, String>() {
            public String fun(ASTNode astNode) {
                return astNode.getText();
            }
        }, "").trim();
    }

    public String getStepTextOld() {
        int offset = getStepTextOffset();
        String text = getText();

        if (offset <= 0 || offset >= text.length()) {
            return trim(text);
        } else {
            return trim(text.substring(offset));
        }
    }

    @Nullable
    public String getActualStepPrefix() {
        ASTNode keyword = getKeyword();
        if (keyword == null) { // that's weird!
            return null;
        }
        return keyword.getText();
    }

    public int getStepTextOffset() {
        String stepPrefix = getActualStepPrefix();
        return stepPrefix != null ? stepPrefix.length() + 1 : 0;
    }
}
