// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;

import static com.github.kumaraman21.intellijbehave.parser.StoryTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;

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
        else if (root_ == GIVEN_STEP) {
            result_ = givenStep(builder_, 0);
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
        else if (root_ == THEN_STEP) {
            result_ = thenStep(builder_, 0);
        }
        else if (root_ == WHEN) {
            result_ = when(builder_, 0);
        }
        else if (root_ == WHEN_STEP) {
            result_ = whenStep(builder_, 0);
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
    // EXAMPLE_TYPE EXAMPLE_TEXT CRLF
    public static boolean example(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "example")) return false;
        if (!nextTokenIs(builder_, EXAMPLE_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, EXAMPLE_TYPE, EXAMPLE_TEXT, CRLF);
        exit_section_(builder_, marker_, EXAMPLE, result_);
        return result_;
    }

    /* ********************************************************** */
    // GIVEN_TYPE givenStep
    public static boolean given(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "given")) return false;
        if (!nextTokenIs(builder_, GIVEN_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeToken(builder_, GIVEN_TYPE);
        result_ = result_ && givenStep(builder_, level_ + 1);
        exit_section_(builder_, marker_, GIVEN, result_);
        return result_;
    }

    /* ********************************************************** */
    // GIVEN_STEP_TYPE STEP_TEXT CRLF
    public static boolean givenStep(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "givenStep")) return false;
        if (!nextTokenIs(builder_, GIVEN_STEP_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, GIVEN_STEP_TYPE, STEP_TEXT, CRLF);
        exit_section_(builder_, marker_, GIVEN_STEP, result_);
        return result_;
    }

    /* ********************************************************** */
    // META_TYPE META_KEY META_TEXT CRLF
    public static boolean metaInfo(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "metaInfo")) return false;
        if (!nextTokenIs(builder_, META_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, META_TYPE, META_KEY, META_TEXT, CRLF);
        exit_section_(builder_, marker_, META_INFO, result_);
        return result_;
    }

    /* ********************************************************** */
    // (STORY_DESCRIPTION | WHITE_SPACE)+
    public static boolean narrative(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "narrative")) return false;
        if (!nextTokenIs(builder_, "<narrative>", STORY_DESCRIPTION, WHITE_SPACE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_, level_, _NONE_, "<narrative>");
        result_ = narrative_0(builder_, level_ + 1);
        int pos_ = current_position_(builder_);
        while (result_) {
            if (!narrative_0(builder_, level_ + 1)) break;
            if (!empty_element_parsed_guard_(builder_, "narrative", pos_)) break;
            pos_ = current_position_(builder_);
        }
        exit_section_(builder_, level_, marker_, NARRATIVE, result_, false, null);
        return result_;
    }

    // STORY_DESCRIPTION | WHITE_SPACE
    private static boolean narrative_0(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "narrative_0")) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeToken(builder_, STORY_DESCRIPTION);
        if (!result_) result_ = consumeToken(builder_, WHITE_SPACE);
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    /* ********************************************************** */
    // scenarioHeader (metaInfo | example | given | when | then | table | COMMENT | WHITE_SPACE)+
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

    // (metaInfo | example | given | when | then | table | COMMENT | WHITE_SPACE)+
    private static boolean scenario_1(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "scenario_1")) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = scenario_1_0(builder_, level_ + 1);
        int pos_ = current_position_(builder_);
        while (result_) {
            if (!scenario_1_0(builder_, level_ + 1)) break;
            if (!empty_element_parsed_guard_(builder_, "scenario_1", pos_)) break;
            pos_ = current_position_(builder_);
        }
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    // metaInfo | example | given | when | then | table | COMMENT | WHITE_SPACE
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
        if (!result_) result_ = consumeToken(builder_, WHITE_SPACE);
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    /* ********************************************************** */
    // SCENARIO_TYPE SCENARIO_TEXT CRLF
    public static boolean scenarioHeader(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "scenarioHeader")) return false;
        if (!nextTokenIs(builder_, SCENARIO_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, SCENARIO_TYPE, SCENARIO_TEXT, CRLF);
        exit_section_(builder_, marker_, SCENARIO_HEADER, result_);
        return result_;
    }

    /* ********************************************************** */
    // narrative CRLF+ scenario* | BAD_CHARACTER
    static boolean storyFile(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "storyFile")) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = storyFile_0(builder_, level_ + 1);
        if (!result_) result_ = consumeToken(builder_, BAD_CHARACTER);
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    // narrative CRLF+ scenario*
    private static boolean storyFile_0(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "storyFile_0")) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = narrative(builder_, level_ + 1);
        result_ = result_ && storyFile_0_1(builder_, level_ + 1);
        result_ = result_ && storyFile_0_2(builder_, level_ + 1);
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    // CRLF+
    private static boolean storyFile_0_1(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "storyFile_0_1")) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeToken(builder_, CRLF);
        int pos_ = current_position_(builder_);
        while (result_) {
            if (!consumeToken(builder_, CRLF)) break;
            if (!empty_element_parsed_guard_(builder_, "storyFile_0_1", pos_)) break;
            pos_ = current_position_(builder_);
        }
        exit_section_(builder_, marker_, null, result_);
        return result_;
    }

    // scenario*
    private static boolean storyFile_0_2(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "storyFile_0_2")) return false;
        int pos_ = current_position_(builder_);
        while (true) {
            if (!scenario(builder_, level_ + 1)) break;
            if (!empty_element_parsed_guard_(builder_, "storyFile_0_2", pos_)) break;
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
    // THEN_TYPE thenStep
    public static boolean then(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "then")) return false;
        if (!nextTokenIs(builder_, THEN_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeToken(builder_, THEN_TYPE);
        result_ = result_ && thenStep(builder_, level_ + 1);
        exit_section_(builder_, marker_, THEN, result_);
        return result_;
    }

    /* ********************************************************** */
    // THEN_STEP_TYPE STEP_TEXT CRLF
    public static boolean thenStep(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "thenStep")) return false;
        if (!nextTokenIs(builder_, THEN_STEP_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, THEN_STEP_TYPE, STEP_TEXT, CRLF);
        exit_section_(builder_, marker_, THEN_STEP, result_);
        return result_;
    }

    /* ********************************************************** */
    // WHEN_TYPE whenStep
    public static boolean when(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "when")) return false;
        if (!nextTokenIs(builder_, WHEN_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeToken(builder_, WHEN_TYPE);
        result_ = result_ && whenStep(builder_, level_ + 1);
        exit_section_(builder_, marker_, WHEN, result_);
        return result_;
    }

    /* ********************************************************** */
    // WHEN_STEP_TYPE STEP_TEXT CRLF
    public static boolean whenStep(PsiBuilder builder_, int level_) {
        if (!recursion_guard_(builder_, level_, "whenStep")) return false;
        if (!nextTokenIs(builder_, WHEN_STEP_TYPE)) return false;
        boolean result_ = false;
        Marker marker_ = enter_section_(builder_);
        result_ = consumeTokens(builder_, 0, WHEN_STEP_TYPE, STEP_TEXT, CRLF);
        exit_section_(builder_, marker_, WHEN_STEP, result_);
        return result_;
    }

}
