package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.junit.Ignore;
import org.junit.Test;

import static com.github.kumaraman21.intellijbehave.Samples.*;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLocalizedLexer_SamplesTest {

    private StoryLocalizedLexer storyLexer;

    @Test
    @Ignore
    public void traceAll() {
        //traceAll(SIMPLE_SAMPLE);
        traceAll(LONG_SAMPLE);
        //traceAll(META_SAMPLE);
        //traceAll(EXAMPLES_SAMPLE);
        //traceAll(COMPLEX_SAMPLE);
    }

    @Test
    public void parseSimpleSample() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(SIMPLE_SAMPLE);

        assertToken(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " An unknown user cannot be logged");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META, "Meta:");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i am the user with nickname: \"weird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i try to login using the password \"soweird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
    }

    @Test
    public void parseMetaSample() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(META_SAMPLE);

        assertToken(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " An unknown user cannot be logged");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META, "Meta:");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@author");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TEXT, "carmen");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i am the user with nickname: \"weird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
    }

    @Test
    public void parseLongSample() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(LONG_SAMPLE);

//        assertToken(StoryTypes.NARRATIVE_TYPE, "Narrative:");
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, " ");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.NARRATIVE_TYPE, "In order to");
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, " play a game");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.NARRATIVE_TYPE, "As a");
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, " player");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.NARRATIVE_TYPE, "I want to");
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, " be able to create and manage my account");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " An unknown user cannot be logged");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META, "Meta:");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i am the user with nickname: \"weird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i try to login using the password \"soweird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " A known user cannot be logged using a wrong password");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " the following existing users:");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " nickname ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " password ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Travis ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   PacMan ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i am the user with nickname: \"Travis\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i try to login using the password \"McCallum\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(TokenType.WHITE_SPACE);

        // ...
    }

    @Test
    public void parseExamples() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(EXAMPLES_SAMPLE);

        assertToken(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " An unknown user cannot be logged");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i am the user with nickname: \"<input>\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "When");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i try to login using the password \"soweird\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_TYPE, "Then");
        advanceAndAssert(StoryTypes.STEP_TEXT, " i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.EXAMPLE, "Examples:");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  login   ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   password   ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  Travis  ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Pacman     ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  Vlad    ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Thundercat ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO, "Scenario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " A known user can be logged using the right password");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Given");
        advanceAndAssert(StoryTypes.STEP_TEXT, " the following existing users:");
        advanceAndAssert(TokenType.WHITE_SPACE);

        // ...
    }

    private void advanceAndAssert(IElementType storyTokenType) {
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

    private void traceAll(String content) {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
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

    private String rightPad(Object object, int length) {
        StringBuilder builder = new StringBuilder(object.toString());
        while (builder.length() < length) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private String escape(CharSequence tokenSequence) {
        return tokenSequence.toString().replace("\n", "\\n").replace("\r", "\\r");
    }


}
