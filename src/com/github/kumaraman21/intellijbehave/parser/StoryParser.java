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
    if (root_ == EXAMPLE) {
      result_ = example(builder_, 0);
    }
    else if (root_ == GIVEN) {
      result_ = given(builder_, 0);
    }
    else if (root_ == META_INFO) {
      result_ = metaInfo(builder_, 0);
    }
    else if (root_ == NARRATIVE) {
      result_ = narrative(builder_, 0);
    }
    else if (root_ == SCENARIO) {
      result_ = scenario(builder_, 0);
    }
    else if (root_ == SCENARIO_HEADER) {
      result_ = scenarioHeader(builder_, 0);
    }
    else if (root_ == TABLE) {
      result_ = table(builder_, 0);
    }
    else if (root_ == THEN) {
      result_ = then(builder_, 0);
    }
    else if (root_ == WHEN) {
      result_ = when(builder_, 0);
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
  // EXAMPLE_TYPE EXAMPLE_TEXT
  public static boolean example(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "example")) return false;
    if (!nextTokenIs(builder_, EXAMPLE_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, EXAMPLE_TYPE, EXAMPLE_TEXT);
    exit_section_(builder_, marker_, EXAMPLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // GIVEN_TYPE STEP_TEXT?
  public static boolean given(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "given")) return false;
    if (!nextTokenIs(builder_, GIVEN_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, GIVEN_TYPE);
    result_ = result_ && given_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, GIVEN, result_);
    return result_;
  }

  // STEP_TEXT?
  private static boolean given_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "given_1")) return false;
    consumeToken(builder_, STEP_TEXT);
    return true;
  }

  /* ********************************************************** */
  // META_TYPE META_KEY META_TEXT
  public static boolean metaInfo(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "metaInfo")) return false;
    if (!nextTokenIs(builder_, META_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, META_TYPE, META_KEY, META_TEXT);
    exit_section_(builder_, marker_, META_INFO, result_);
    return result_;
  }

  /* ********************************************************** */
  // STORY_DESCRIPTION STORY_DESCRIPTION*
  public static boolean narrative(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "narrative")) return false;
    if (!nextTokenIs(builder_, STORY_DESCRIPTION)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STORY_DESCRIPTION);
    result_ = result_ && narrative_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, NARRATIVE, result_);
    return result_;
  }

  // STORY_DESCRIPTION*
  private static boolean narrative_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "narrative_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!consumeToken(builder_, STORY_DESCRIPTION)) break;
      if (!empty_element_parsed_guard_(builder_, "narrative_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // scenarioHeader (metaInfo | example | given | when | then | table | COMMENT)*
  public static boolean scenario(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenario")) return false;
    if (!nextTokenIs(builder_, SCENARIO_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = scenarioHeader(builder_, level_ + 1);
    result_ = result_ && scenario_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SCENARIO, result_);
    return result_;
  }

  // (metaInfo | example | given | when | then | table | COMMENT)*
  private static boolean scenario_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenario_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!scenario_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "scenario_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  // metaInfo | example | given | when | then | table | COMMENT
  private static boolean scenario_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenario_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = metaInfo(builder_, level_ + 1);
    if (!result_) result_ = example(builder_, level_ + 1);
    if (!result_) result_ = given(builder_, level_ + 1);
    if (!result_) result_ = when(builder_, level_ + 1);
    if (!result_) result_ = then(builder_, level_ + 1);
    if (!result_) result_ = table(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SCENARIO_TYPE SCENARIO_TEXT
  public static boolean scenarioHeader(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "scenarioHeader")) return false;
    if (!nextTokenIs(builder_, SCENARIO_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, SCENARIO_TYPE, SCENARIO_TEXT);
    exit_section_(builder_, marker_, SCENARIO_HEADER, result_);
    return result_;
  }

  /* ********************************************************** */
  // narrative scenario* | BAD_CHARACTER
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

  // narrative scenario*
  private static boolean storyFile_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storyFile_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = narrative(builder_, level_ + 1);
    result_ = result_ && storyFile_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // scenario*
  private static boolean storyFile_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storyFile_0_1")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!scenario(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "storyFile_0_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

  /* ********************************************************** */
  // TABLE_DELIM (TABLE_CELL TABLE_DELIM)+
  public static boolean table(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table")) return false;
    if (!nextTokenIs(builder_, TABLE_DELIM)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TABLE_DELIM);
    result_ = result_ && table_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, TABLE, result_);
    return result_;
  }

  // (TABLE_CELL TABLE_DELIM)+
  private static boolean table_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_1")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = table_1_0(builder_, level_ + 1);
    int pos_ = current_position_(builder_);
    while (result_) {
      if (!table_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "table_1", pos_)) break;
      pos_ = current_position_(builder_);
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TABLE_CELL TABLE_DELIM
  private static boolean table_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_1_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, TABLE_CELL, TABLE_DELIM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // THEN_TYPE STEP_TEXT?
  public static boolean then(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "then")) return false;
    if (!nextTokenIs(builder_, THEN_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THEN_TYPE);
    result_ = result_ && then_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, THEN, result_);
    return result_;
  }

  // STEP_TEXT?
  private static boolean then_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "then_1")) return false;
    consumeToken(builder_, STEP_TEXT);
    return true;
  }

  /* ********************************************************** */
  // WHEN_TYPE STEP_TEXT?
  public static boolean when(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "when")) return false;
    if (!nextTokenIs(builder_, WHEN_TYPE)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, WHEN_TYPE);
    result_ = result_ && when_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, WHEN, result_);
    return result_;
  }

  // STEP_TEXT?
  private static boolean when_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "when_1")) return false;
    consumeToken(builder_, STEP_TEXT);
    return true;
  }

}
