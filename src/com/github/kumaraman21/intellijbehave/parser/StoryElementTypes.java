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

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

import static com.github.kumaraman21.intellijbehave.language.StoryLanguage.STORY_LANGUAGE;

public interface StoryElementTypes {
    public static final IFileElementType STORY_FILE = new IFileElementType(STORY_LANGUAGE);

    public static final IElementType UNKNOWN_FRAGMENT = new StoryElementType("UNKNOWN_FRAGMENT");
    public static final IElementType COMMENT = new StoryElementType("COMMENT");
    public static final IElementType STORY = new StoryElementType("STORY");
    public static final IElementType STORY_DESCRIPTION = new StoryElementType("STORY_DESCRIPTION");
    public static final IElementType SCENARIO = new StoryElementType("SCENARIO");
    public static final IElementType META = new StoryElementType("META");
    public static final IElementType EXAMPLES = new StoryElementType("EXAMPLES");
    public static final IElementType TABLE_ROW = new StoryElementType("TABLE_ROW");

    public static final IElementType GIVEN_STEP = new StoryElementType("GIVEN_STEP");
    public static final IElementType WHEN_STEP = new StoryElementType("WHEN_STEP");
    public static final IElementType THEN_STEP = new StoryElementType("THEN_STEP");

    public static final TokenSet STEPS = TokenSet.create(GIVEN_STEP, WHEN_STEP, THEN_STEP);
}
