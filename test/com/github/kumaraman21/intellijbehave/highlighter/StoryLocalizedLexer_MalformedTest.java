package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

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

        assertToken(StoryTypes.TABLE_DELIM, "|");
        advanceAndAssert(StoryTypes.TABLE_CELL, " Travis ");
        advanceAndAssert(StoryTypes.TABLE_DELIM, "|");
        advanceAndAssert(StoryTypes.TABLE_CELL, " Pacman ");
        advanceAndAssert(StoryTypes.TABLE_DELIM, "|");
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

        assertToken(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "And");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(null);
    }

    @Test
    public void parse_scenarioWithStepAsText() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start("Scenario: Given a nice\n" +
                "Given\n" +
                "When\n" +
                "Then\n");

        assertToken(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " Given a nice");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(TokenType.WHITE_SPACE);
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
