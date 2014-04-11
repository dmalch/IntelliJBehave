// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static com.github.kumaraman21.intellijbehave.parser.StoryTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class StoryParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("com.github.kumaraman21.intellijbehave.parser.StoryParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == EXAMPLE_BLOCK_PSI_ELEMENT) {
      result_ = exampleBlockPsiElement(builder_, 0);
    }
    else if (root_ == META_INFO_PSI_ELEMENT) {
      result_ = metaInfoPsiElement(builder_, 0);
    }
    else if (root_ == NARRATIVE_BLOCK_PSI_ELEMENT) {
      result_ = narrativeBlockPsiElement(builder_, 0);
    }
    else if (root_ == SCENARIO_BLOCK_PSI_ELEMENT) {
      result_ = scenarioBlockPsiElement(builder_, 0);
    }
    else if (root_ == SCENARIO_HEADER_PSI_ELEMENT) {
      result_ = scenarioHeaderPsiElement(builder_, 0);
    }
    else if (root_ == STEP_PSI_ELEMENT) {
      result_ = stepPsiElement(builder_, 0);
    }
    else if (root_ == STEP_TEXT_PSI_ELEMENT) {
      result_ = stepTextPsiElement(builder_, 0);
    }
    else if (root_ == STEP_TYPE_PSI_ELEMENT) {
      result_ = stepTypePsiElement(builder_, 0);
    }
    else if (root_ == TABLE_PSI_ELEMENT) {
      result_ = tablePsiElement(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return storyFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // EXAMPLE EXAMPLE_TEXT
  public static boolean exampleBlockPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exampleBlockPsiElement")) return false;
    if (!nextTokenIs(builder_, EXAMPLE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, EXAMPLE, EXAMPLE_TEXT);
    exit_section_(builder_, marker_, EXAMPLE_BLOCK_PSI_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // META META_KEY META_TEXT
  public static boolean metaInfoPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaInfoPsiElement")) return false;
    if (!nextTokenIs(builder_, META)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, META, META_KEY, META_TEXT);
    exit_section_(builder_, marker_, META_INFO_PSI_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // STORY_DESCRIPTION STORY_DESCRIPTION*
  public static boolean narrativeBlockPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "narrativeBlockPsiElement")) return false;
    if (!nextTokenIs(builder_, STORY_DESCRIPTION)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORY_DESCRIPTION);
    result_ = result_ && narrativeBlockPsiElement_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, NARRATIVE_BLOCK_PSI_ELEMENT, result_);
    return result_;
  }

  // STORY_DESCRIPTION*
  private static boolean narrativeBlockPsiElement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "narrativeBlockPsiElement_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, STORY_DESCRIPTION)) break;
      if (!empty_element_parsed_guard_(builder_, "narrativeBlockPsiElement_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // scenarioHeaderPsiElement (metaInfoPsiElement | exampleBlockPsiElement | stepPsiElement | tablePsiElement | COMMENT)*
  public static boolean scenarioBlockPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenarioBlockPsiElement")) return false;
    if (!nextTokenIs(builder_, SCENARIO)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = scenarioHeaderPsiElement(builder_, level_ + 1);
    result_ = result_ && scenarioBlockPsiElement_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SCENARIO_BLOCK_PSI_ELEMENT, result_);
    return result_;
  }

  // (metaInfoPsiElement | exampleBlockPsiElement | stepPsiElement | tablePsiElement | COMMENT)*
  private static boolean scenarioBlockPsiElement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenarioBlockPsiElement_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!scenarioBlockPsiElement_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "scenarioBlockPsiElement_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // metaInfoPsiElement | exampleBlockPsiElement | stepPsiElement | tablePsiElement | COMMENT
  private static boolean scenarioBlockPsiElement_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenarioBlockPsiElement_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = metaInfoPsiElement(builder_, level_ + 1);
    if (!result_) result_ = exampleBlockPsiElement(builder_, level_ + 1);
    if (!result_) result_ = stepPsiElement(builder_, level_ + 1);
    if (!result_) result_ = tablePsiElement(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SCENARIO SCENARIO_TEXT
  public static boolean scenarioHeaderPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenarioHeaderPsiElement")) return false;
    if (!nextTokenIs(builder_, SCENARIO)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, SCENARIO, SCENARIO_TEXT);
    exit_section_(builder_, marker_, SCENARIO_HEADER_PSI_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // stepTypePsiElement stepTextPsiElement?
  public static boolean stepPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stepPsiElement")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<step psi element>");
    result_ = stepTypePsiElement(builder_, level_ + 1);
    result_ = result_ && stepPsiElement_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, STEP_PSI_ELEMENT, result_, false, null);
    return result_;
  }

  // stepTextPsiElement?
  private static boolean stepPsiElement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stepPsiElement_1")) return false;
    stepTextPsiElement(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // STEP_TEXT
  public static boolean stepTextPsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stepTextPsiElement")) return false;
    if (!nextTokenIs(builder_, STEP_TEXT)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STEP_TEXT);
    exit_section_(builder_, marker_, STEP_TEXT_PSI_ELEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // GIVEN | WHEN | THEN
  public static boolean stepTypePsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stepTypePsiElement")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<step type psi element>");
    result_ = consumeToken(builder_, GIVEN);
    if (!result_) result_ = consumeToken(builder_, WHEN);
    if (!result_) result_ = consumeToken(builder_, THEN);
    exit_section_(builder_, level_, marker_, STEP_TYPE_PSI_ELEMENT, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // narrativeBlockPsiElement scenarioBlockPsiElement* | BAD_CHARACTER
  static boolean storyFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storyFile")) return false;
    if (!nextTokenIs(builder_, "", BAD_CHARACTER, STORY_DESCRIPTION)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = storyFile_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BAD_CHARACTER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // narrativeBlockPsiElement scenarioBlockPsiElement*
  private static boolean storyFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storyFile_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = narrativeBlockPsiElement(builder_, level_ + 1);
    result_ = result_ && storyFile_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // scenarioBlockPsiElement*
  private static boolean storyFile_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storyFile_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!scenarioBlockPsiElement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "storyFile_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // TABLE_DELIM (TABLE_CELL TABLE_DELIM)+
  public static boolean tablePsiElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tablePsiElement")) return false;
    if (!nextTokenIs(builder_, TABLE_DELIM)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TABLE_DELIM);
    result_ = result_ && tablePsiElement_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, TABLE_PSI_ELEMENT, result_);
    return result_;
  }

  // (TABLE_CELL TABLE_DELIM)+
  private static boolean tablePsiElement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tablePsiElement_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = tablePsiElement_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!tablePsiElement_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "tablePsiElement_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TABLE_CELL TABLE_DELIM
  private static boolean tablePsiElement_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tablePsiElement_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, TABLE_CELL, TABLE_DELIM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
