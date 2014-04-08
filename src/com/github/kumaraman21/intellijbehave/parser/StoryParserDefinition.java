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

import com.github.kumaraman21.intellijbehave.lexer.StoryLexerFactory;
import com.github.kumaraman21.intellijbehave.language.StoryLanguage;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jbehave.core.steps.StepType;
import org.jetbrains.annotations.NotNull;

public class StoryParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
//    public static final TokenSet COMMENTS = TokenSet.create(StoryTypes.COMMENT, StoryTypes.COMMENT_WITH_LOCALE);
    public static final TokenSet COMMENTS = TokenSet.create(StoryTypes.COMMENT);

    public static final IFileElementType FILE = new IFileElementType(StoryLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new StoryLexerFactory().createLexer();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new StoryFile(fileViewProvider);
    }

    @Override
    public PsiParser createParser(Project project) {
        return new StoryParser();
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();
        if (type == StoryElementType.GIVEN_STEP) {
            return new StepPsiElement(node, StepType.GIVEN);
        } else if (type == StoryElementType.WHEN_STEP) {
            return new StepPsiElement(node, StepType.WHEN);
        } else if (type == StoryElementType.THEN_STEP) {
            return new StepPsiElement(node, StepType.THEN);
        }

        return new ASTWrapperPsiElement(node);
    }
}
