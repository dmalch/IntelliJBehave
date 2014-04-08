// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.kumaraman21.intellijbehave.psi.impl.*;

public interface StoryTypes {

    IElementType EXAMPLE = new StoryElementType("EXAMPLE");
    IElementType GIVEN = new StoryElementType("GIVEN");
    IElementType GIVEN_STEP = new StoryElementType("GIVEN_STEP");
    IElementType META_INFO = new StoryElementType("META_INFO");
    IElementType NARRATIVE = new StoryElementType("NARRATIVE");
    IElementType SCENARIO = new StoryElementType("SCENARIO");
    IElementType SCENARIO_HEADER = new StoryElementType("SCENARIO_HEADER");
    IElementType TABLE = new StoryElementType("TABLE");
    IElementType THEN = new StoryElementType("THEN");
    IElementType THEN_STEP = new StoryElementType("THEN_STEP");
    IElementType WHEN = new StoryElementType("WHEN");
    IElementType WHEN_STEP = new StoryElementType("WHEN_STEP");

    IElementType BAD_CHARACTER = new StoryTokenType("BAD_CHARACTER");
    IElementType COMMENT = new StoryTokenType("COMMENT");
    IElementType CRLF = new StoryTokenType("CRLF");
    IElementType EXAMPLE_TEXT = new StoryTokenType("EXAMPLE_TEXT");
    IElementType EXAMPLE_TYPE = new StoryTokenType("EXAMPLE_TYPE");
    IElementType GIVEN_STEP_TYPE = new StoryTokenType("GIVEN_STEP_TYPE");
    IElementType GIVEN_TYPE = new StoryTokenType("GIVEN_TYPE");
    IElementType META_KEY = new StoryTokenType("META_KEY");
    IElementType META_TEXT = new StoryTokenType("META_TEXT");
    IElementType META_TYPE = new StoryTokenType("META_TYPE");
    IElementType SCENARIO_TEXT = new StoryTokenType("SCENARIO_TEXT");
    IElementType SCENARIO_TYPE = new StoryTokenType("SCENARIO_TYPE");
    IElementType STEP_TEXT = new StoryTokenType("STEP_TEXT");
    IElementType STORY_DESCRIPTION = new StoryTokenType("STORY_DESCRIPTION");
    IElementType TABLE_CELL = new StoryTokenType("TABLE_CELL");
    IElementType TABLE_DELIM = new StoryTokenType("TABLE_DELIM");
    IElementType THEN_STEP_TYPE = new StoryTokenType("THEN_STEP_TYPE");
    IElementType THEN_TYPE = new StoryTokenType("THEN_TYPE");
    IElementType WHEN_STEP_TYPE = new StoryTokenType("WHEN_STEP_TYPE");
    IElementType WHEN_TYPE = new StoryTokenType("WHEN_TYPE");
    IElementType WHITE_SPACE = new StoryTokenType("WHITE_SPACE");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == EXAMPLE) {
                return new StoryExampleImpl(node);
            }
            else if (type == GIVEN) {
                return new StoryGivenImpl(node);
            }
            else if (type == GIVEN_STEP) {
                return new StoryGivenStepImpl(node);
            }
            else if (type == META_INFO) {
                return new StoryMetaInfoImpl(node);
            }
            else if (type == NARRATIVE) {
                return new StoryNarrativeImpl(node);
            }
            else if (type == SCENARIO) {
                return new StoryScenarioImpl(node);
            }
            else if (type == SCENARIO_HEADER) {
                return new StoryScenarioHeaderImpl(node);
            }
            else if (type == TABLE) {
                return new StoryTableImpl(node);
            }
            else if (type == THEN) {
                return new StoryThenImpl(node);
            }
            else if (type == THEN_STEP) {
                return new StoryThenStepImpl(node);
            }
            else if (type == WHEN) {
                return new StoryWhenImpl(node);
            }
            else if (type == WHEN_STEP) {
                return new StoryWhenStepImpl(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
