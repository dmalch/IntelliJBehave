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

import com.github.kumaraman21.intellijbehave.highlighter.StoryLocalizedLexer;
import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jbehave.core.steps.StepType;
import org.jetbrains.annotations.NotNull;

import static com.github.kumaraman21.intellijbehave.parser.StoryElementTypes.*;

public class StoryParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new StoryLocalizedLexer();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new StoryParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return STORY_FILE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create(StoryTokenTypes.WHITE_SPACE);
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return StoryTokenTypes.COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();
        if (type == GIVEN_STEP) {
            return new JBehaveStep(node, StepType.GIVEN);
        } else if (type == WHEN_STEP) {
            return new JBehaveStep(node, StepType.WHEN);
        } else if (type == THEN_STEP) {
            return new JBehaveStep(node, StepType.THEN);
        }

        return new ASTWrapperPsiElement(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new StoryFile(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
