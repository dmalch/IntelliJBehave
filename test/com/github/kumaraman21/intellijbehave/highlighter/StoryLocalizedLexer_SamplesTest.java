package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.psi.tree.IElementType;
import org.junit.Ignore;
import org.junit.Test;

import static com.github.kumaraman21.intellijbehave.Samples.*;
import static com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes.*;
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

        assertToken(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseMetaSample() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(META_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@author");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_TEXT, "carmen");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseLongSample() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(LONG_SAMPLE);

        assertToken(NARRATIVE_TYPE, "Narrative:");
        advanceAndAssert(STORY_DESCRIPTION, " ");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(NARRATIVE_TYPE, "In order to");
        advanceAndAssert(STORY_DESCRIPTION, " play a game");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(NARRATIVE_TYPE, "As a");
        advanceAndAssert(STORY_DESCRIPTION, " player");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(NARRATIVE_TYPE, "I want to");
        advanceAndAssert(STORY_DESCRIPTION, " be able to create and manage my account");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "A known user cannot be logged using a wrong password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "the following existing users:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, " nickname ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, " password ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "   Travis ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "   PacMan ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"McCallum\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);

        // ...
    }

    @Test
    public void parseExamples() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.start(EXAMPLES_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"<input>\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "When");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Then");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(EXAMPLE_TYPE, "Examples:");
        advanceAndAssert(EXAMPLE_TYPE, " ");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "  login   ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "   password   ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "  Travis  ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "   Pacman     ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "  Vlad    ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(TABLE_CELL, "   Thundercat ");
        advanceAndAssert(TABLE_DELIM);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "A known user can be logged using the right password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Given");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "the following existing users:");
        advanceAndAssert(WHITE_SPACE);

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