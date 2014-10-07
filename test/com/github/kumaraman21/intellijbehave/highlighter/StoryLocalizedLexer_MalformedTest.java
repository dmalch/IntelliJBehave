package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import static com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes.*;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLocalizedLexer_MalformedTest {

    private StoryLocalizedLexer storyLexer;

    @Test
    public void parse_tableWithoutPreamble() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start("| Travis | Pacman |");

        assertToken(TABLE_DELIM, "|");
        advanceAndAssert(TABLE_CELL, " Travis ");
        advanceAndAssert(TABLE_DELIM, "|");
        advanceAndAssert(TABLE_CELL, " Pacman ");
        advanceAndAssert(TABLE_DELIM, "|");
        advanceAndAssert(null);
    }

    @Test
    public void parse_scenarioWithoutText() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start("Scenario:\n" +
                "Given\n" +
                "When\n" +
                "Then\n" +
                "And\n");

        assertToken(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_AND, "And");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(null);
    }

    @Test
    public void parse_scenarioWithStepAsText() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start("Scenario: Given a nice\n" +
                "Given\n" +
                "When\n" +
                "Then\n");

        assertToken(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "Given a nice");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(null);
    }

    private void advanceAndAssert(@Nullable IElementType storyTokenType) {
        storyLexer.advance();
        assertThat(storyLexer.getTokenType()).isEqualTo(storyTokenType);
    }

    private void advanceAndAssert(IElementType storyTokenType, String content) {
        storyLexer.advance();
        assertToken(storyTokenType, content);
    }

    private void assertToken(IElementType storyTokenType, String content) {
        assertThat(storyLexer.getTokenSequence()).isEqualTo(content);
        assertThat(storyLexer.getTokenType()).isEqualTo(storyTokenType);
    }

    private static void traceAll(String content, StoryLocalizedLexer storyLexer) {
        storyLexer.start(content);

        IElementType tokenType;
        do {
            tokenType = storyLexer.getTokenType();
            System.out.println(
                    rightPad("" + storyLexer.getPosition(), 3) + " " +
                            "[" + rightPad(tokenType, "STORY_DESCRIPTION".length()) + "]" +
                            rightPad(storyLexer.lexerState(), "IN_DIRECTIVE".length()) +
                            ": >>" + escape(storyLexer.getTokenSequence()) + "<<");

            storyLexer.advance();
            tokenType = storyLexer.getTokenType();
        }
        while (tokenType != null);
    }

    private static String rightPad(Object object, int length) {
        StringBuilder builder = new StringBuilder(object.toString());
        while (builder.length() < length) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private static String escape(CharSequence tokenSequence) {
        return tokenSequence.toString().replace("\n", "\\n").replace("\r", "\\r");
    }


}
