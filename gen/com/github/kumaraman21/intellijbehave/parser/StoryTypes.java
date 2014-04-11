// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.kumaraman21.intellijbehave.parser.psi.impl.*;

public interface StoryTypes {

  IElementType EXAMPLE_BLOCK = new StoryElementType("EXAMPLE_BLOCK");
  IElementType GIVEN_STEP = new StoryElementType("GIVEN_STEP");
  IElementType META_INFO = new StoryElementType("META_INFO");
  IElementType NARRATIVE_BLOCK = new StoryElementType("NARRATIVE_BLOCK");
  IElementType SCENARIO_BLOCK = new StoryElementType("SCENARIO_BLOCK");
  IElementType SCENARIO_HEADER = new StoryElementType("SCENARIO_HEADER");
  IElementType TABLE = new StoryElementType("TABLE");
  IElementType THEN_STEP = new StoryElementType("THEN_STEP");
  IElementType WHEN_STEP = new StoryElementType("WHEN_STEP");

  IElementType BAD_CHARACTER = new StoryTokenType("BAD_CHARACTER");
  IElementType COMMENT = new StoryTokenType("COMMENT");
  IElementType EXAMPLE = new StoryTokenType("EXAMPLE");
  IElementType EXAMPLE_TEXT = new StoryTokenType("EXAMPLE_TEXT");
  IElementType GIVEN = new StoryTokenType("GIVEN");
  IElementType META = new StoryTokenType("META");
  IElementType META_KEY = new StoryTokenType("META_KEY");
  IElementType META_TEXT = new StoryTokenType("META_TEXT");
  IElementType SCENARIO = new StoryTokenType("SCENARIO");
  IElementType SCENARIO_TEXT = new StoryTokenType("SCENARIO_TEXT");
  IElementType STEP_TEXT = new StoryTokenType("STEP_TEXT");
  IElementType STORY_DESCRIPTION = new StoryTokenType("STORY_DESCRIPTION");
  IElementType TABLE_CELL = new StoryTokenType("TABLE_CELL");
  IElementType TABLE_DELIM = new StoryTokenType("TABLE_DELIM");
  IElementType THEN = new StoryTokenType("THEN");
  IElementType WHEN = new StoryTokenType("WHEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == EXAMPLE_BLOCK) {
        return new StoryExampleBlockImpl(node);
      }
      else if (type == GIVEN_STEP) {
        return new StoryGivenStepImpl(node);
      }
      else if (type == META_INFO) {
        return new StoryMetaInfoImpl(node);
      }
      else if (type == NARRATIVE_BLOCK) {
        return new StoryNarrativeBlockImpl(node);
      }
      else if (type == SCENARIO_BLOCK) {
        return new StoryScenarioBlockImpl(node);
      }
      else if (type == SCENARIO_HEADER) {
        return new StoryScenarioHeaderImpl(node);
      }
      else if (type == TABLE) {
        return new StoryTableImpl(node);
      }
      else if (type == THEN_STEP) {
        return new StoryThenStepImpl(node);
      }
      else if (type == WHEN_STEP) {
        return new StoryWhenStepImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
