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

import com.github.kumaraman21.intellijbehave.parser.StoryElementType;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface StoryTokenTypes {
    public static final IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    public static final IElementType STORY_DESCRIPTION = new StoryElementType("STORY_DESCRIPTION");
    public static final IElementType SCENARIO_TYPE = new StoryElementType("SCENARIO_TYPE");
    public static final IElementType SCENARIO_TEXT = new StoryElementType("SCENARIO_TEXT");
    public static final IElementType STEP_TYPE_GIVEN = new StoryElementType("STEP_TYPE_GIVEN");
    public static final IElementType STEP_TYPE_WHEN = new StoryElementType("STEP_TYPE_WHEN");
    public static final IElementType STEP_TYPE_THEN = new StoryElementType("STEP_TYPE_THEN");
    public static final IElementType STEP_TYPE_AND = new StoryElementType("STEP_TYPE_AND");
    public static final IElementType STEP_TEXT = new StoryElementType("STEP_TEXT");
    public static final IElementType TABLE_DELIM = new StoryElementType("TABLE_DELIM");
    public static final IElementType TABLE_CELL = new StoryElementType("TABLE_CELL");
    public static final IElementType COMMENT = new StoryElementType("COMMENT");
    public static final IElementType COMMENT_WITH_LOCALE = new StoryElementType("COMMENT_WITH_LOCALE");
    public static final IElementType META = new StoryElementType("META");
    public static final IElementType META_KEY = new StoryElementType("META_KEY");
    public static final IElementType META_TEXT = new StoryElementType("META_TEXT");
    public static final IElementType EXAMPLE_TYPE = new StoryElementType("EXAMPLE_TYPE");
    public static final IElementType GIVEN_STORIES = new StoryElementType("GIVEN_STORIES");
    public static final IElementType NARRATIVE_TYPE = new StoryElementType("NARRATIVE_TYPE");
    public static final TokenSet STEP_TYPES = TokenSet.create(STEP_TYPE_GIVEN, STEP_TYPE_WHEN, STEP_TYPE_THEN, STEP_TYPE_AND);

    public static final TokenSet COMMENTS = TokenSet.create(COMMENT, COMMENT_WITH_LOCALE);
    public static final TokenSet KEYWORDS = TokenSet.create(STORY_DESCRIPTION, SCENARIO_TYPE,
            STEP_TYPE_GIVEN, STEP_TYPE_WHEN, STEP_TYPE_THEN, STEP_TYPE_AND,
            GIVEN_STORIES, NARRATIVE_TYPE, EXAMPLE_TYPE);
}
