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
package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.lexer.StoryLexerAdapter;
import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.google.common.collect.ImmutableMap;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class StorySyntaxHighlighter extends SyntaxHighlighterBase {

    public static TextAttributesKey STORY_DESCRIPTION = createTextAttributesKey("JBEHAVE.STORY_DESCRIPTION", DefaultLanguageHighlighterColors.NUMBER);
    public static TextAttributesKey SCENARIO = createTextAttributesKey("JBEHAVE.SCENARIO", CodeInsightColors.STATIC_FIELD_ATTRIBUTES);
    public static TextAttributesKey SCENARIO_TEXT = createTextAttributesKey("JBEHAVE.SCENARIO_TEXT", CodeInsightColors.STATIC_FIELD_ATTRIBUTES);
    public static TextAttributesKey STEP_TYPE = createTextAttributesKey("JBEHAVE.STEP_TYPE", DefaultLanguageHighlighterColors.KEYWORD);
    public static TextAttributesKey STEP_TEXT = createTextAttributesKey("JBEHAVE.STEP_TEXT", HighlighterColors.TEXT);
    public static TextAttributesKey TABLE_DELIM = createTextAttributesKey("JBEHAVE.TABLE_DELIM", DefaultLanguageHighlighterColors.BRACES);
    public static TextAttributesKey TABLE_CELL = createTextAttributesKey("JBEHAVE.TABLE_CELL", DefaultLanguageHighlighterColors.STRING);
    public static TextAttributesKey META_TYPE = createTextAttributesKey("JBEHAVE.META_TYPE", DefaultLanguageHighlighterColors.KEYWORD);
    public static TextAttributesKey META_KEY = createTextAttributesKey("JBEHAVE.META_KEY", DefaultLanguageHighlighterColors.STRING);
    public static TextAttributesKey META_TEXT = createTextAttributesKey("JBEHAVE.META_TEXT", DefaultLanguageHighlighterColors.STRING);
    public static TextAttributesKey LINE_COMMENT = createTextAttributesKey("JBEHAVE.COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static TextAttributesKey BAD_CHARACTER = createTextAttributesKey("JBEHAVE.BAD_CHARACTER", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE);

    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new ImmutableMap.Builder<IElementType, TextAttributesKey>()
//            .put(StoryTypes.NARRATIVE_TYPE, STORY_DESCRIPTION)
//            .put(StoryTypes.NARRATIVE_TEXT, STORY_DESCRIPTION)
//            .put(StoryTypes.COMMENT_WITH_LOCALE, LINE_COMMENT)
            .put(StoryTypes.STORY_DESCRIPTION, STORY_DESCRIPTION)
            .put(StoryTypes.SCENARIO, SCENARIO)
            .put(StoryTypes.SCENARIO_TEXT, SCENARIO_TEXT)
            .put(StoryTypes.GIVEN, STEP_TYPE)
            .put(StoryTypes.WHEN, STEP_TYPE)
            .put(StoryTypes.THEN, STEP_TYPE)
            .put(StoryTypes.STEP_TEXT, STEP_TEXT)
            .put(StoryTypes.TABLE_DELIM, TABLE_DELIM)
            .put(StoryTypes.TABLE_CELL, TABLE_CELL)
            .put(StoryTypes.META, META_TYPE)
            .put(StoryTypes.META_KEY, META_KEY)
            .put(StoryTypes.META_TEXT, META_TEXT)
            .put(StoryTypes.COMMENT, LINE_COMMENT)
            .put(StoryTypes.BAD_CHARACTER, BAD_CHARACTER)
            .build();

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new StoryLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}
