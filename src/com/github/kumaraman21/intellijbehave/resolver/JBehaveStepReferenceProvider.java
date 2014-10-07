package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.github.kumaraman21.intellijbehave.parser.JBehaveStep;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.cucumber.psi.GherkinElementTypes;
import org.jetbrains.plugins.cucumber.psi.GherkinTokenTypes;
import org.jetbrains.plugins.cucumber.psi.impl.GherkinStepImpl;
import org.jetbrains.plugins.cucumber.steps.reference.CucumberStepReference;

public class JBehaveStepReferenceProvider extends PsiReferenceProvider {
    @NotNull
    @Override
    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        if (element instanceof JBehaveStep) {
            TokenSet textSet = TokenSet.create(StoryTokenTypes.STEP_TEXT);
            ASTNode textNode = element.getNode().findChildByType(textSet);

            if (textNode != null) {
                int start = textNode.getTextRange().getStartOffset();
                int end = textNode.getTextRange().getEndOffset();
                int endBeforeSpace = end;

                textSet = TokenSet.orSet(textSet, TokenSet.create(TokenType.WHITE_SPACE));
                for (textNode = textNode.getTreeNext(); textNode != null && textSet.contains(textNode.getElementType()); textNode = textNode.getTreeNext()) {
                    if (textNode.getElementType() == TokenType.WHITE_SPACE) {
                        endBeforeSpace = end;
                    } else {
                        endBeforeSpace = textNode.getTextRange().getEndOffset();
                    }

                    end = textNode.getTextRange().getEndOffset();
                }

                TextRange tr = new TextRange(start, endBeforeSpace);
                return new PsiReference[]{new JBehaveStepReference((JBehaveStep) element, tr.shiftRight(-element.getTextOffset()))};
            }
        }

        return PsiReference.EMPTY_ARRAY;
    }

    @NotNull
    public PsiReference[] getReferencesByElement2(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        if (element instanceof GherkinStepImpl) {
            TokenSet textAndParamSet = TokenSet.create(GherkinTokenTypes.TEXT, GherkinTokenTypes.STEP_PARAMETER_TEXT, GherkinTokenTypes.STEP_PARAMETER_BRACE, GherkinElementTypes.STEP_PARAMETER);
            ASTNode textNode = element.getNode().findChildByType(textAndParamSet);
            if (textNode != null) {
                int start = textNode.getTextRange().getStartOffset();
                int end = textNode.getTextRange().getEndOffset();
                int endBeforeSpace = end;

                textAndParamSet = TokenSet.orSet(textAndParamSet, TokenSet.create(TokenType.WHITE_SPACE));
                for (textNode = textNode.getTreeNext(); textNode != null && textAndParamSet.contains(textNode.getElementType()); textNode = textNode.getTreeNext()) {
                    if (textNode.getElementType() == TokenType.WHITE_SPACE) {
                        endBeforeSpace = end;
                    } else {
                        endBeforeSpace = textNode.getTextRange().getEndOffset();
                    }

                    end = textNode.getTextRange().getEndOffset();
                }

                TextRange tr = new TextRange(start, endBeforeSpace);
                return new PsiReference[]{new CucumberStepReference(element, tr.shiftRight(-element.getTextOffset()))};
            }
        }

        return PsiReference.EMPTY_ARRAY;
    }
}
