// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.kumaraman21.intellijbehave.parser.psi.impl.*;

public interface StoryTypes {

  IElementType EXAMPLE_BLOCK_PSI_ELEMENT = new StoryElementType("EXAMPLE_BLOCK_PSI_ELEMENT");
  IElementType META_INFO_PSI_ELEMENT = new StoryElementType("META_INFO_PSI_ELEMENT");
  IElementType NARRATIVE_BLOCK_PSI_ELEMENT = new StoryElementType("NARRATIVE_BLOCK_PSI_ELEMENT");
  IElementType SCENARIO_BLOCK_PSI_ELEMENT = new StoryElementType("SCENARIO_BLOCK_PSI_ELEMENT");
  IElementType SCENARIO_HEADER_PSI_ELEMENT = new StoryElementType("SCENARIO_HEADER_PSI_ELEMENT");
  IElementType STEP_PSI_ELEMENT = new StoryElementType("STEP_PSI_ELEMENT");
  IElementType STEP_TEXT_PSI_ELEMENT = new StoryElementType("STEP_TEXT_PSI_ELEMENT");
  IElementType STEP_TYPE_PSI_ELEMENT = new StoryElementType("STEP_TYPE_PSI_ELEMENT");
  IElementType TABLE_PSI_ELEMENT = new StoryElementType("TABLE_PSI_ELEMENT");

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
       if (type == EXAMPLE_BLOCK_PSI_ELEMENT) {
        return new StoryExampleBlockPsiElementImpl(node);
      }
      else if (type == META_INFO_PSI_ELEMENT) {
        return new StoryMetaInfoPsiElementImpl(node);
      }
      else if (type == NARRATIVE_BLOCK_PSI_ELEMENT) {
        return new StoryNarrativeBlockPsiElementImpl(node);
      }
      else if (type == SCENARIO_BLOCK_PSI_ELEMENT) {
        return new StoryScenarioBlockPsiElementImpl(node);
      }
      else if (type == SCENARIO_HEADER_PSI_ELEMENT) {
        return new StoryScenarioHeaderPsiElementImpl(node);
      }
      else if (type == STEP_PSI_ELEMENT) {
        return new StoryStepPsiElementImpl(node);
      }
      else if (type == STEP_TEXT_PSI_ELEMENT) {
        return new StoryStepTextPsiElementImpl(node);
      }
      else if (type == STEP_TYPE_PSI_ELEMENT) {
        return new StoryStepTypePsiElementImpl(node);
      }
      else if (type == TABLE_PSI_ELEMENT) {
        return new StoryTablePsiElementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
