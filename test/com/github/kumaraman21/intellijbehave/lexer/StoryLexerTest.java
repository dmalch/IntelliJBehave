package com.github.kumaraman21.intellijbehave.lexer;

import com.github.kumaraman21.intellijbehave.Samples;
import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.intellij.psi.tree.IElementType;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLexerTest {

    private StoryLexerAdapter storyLexerAdapter;

    @Test
    public void parseSimpleSample() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.SIMPLE_SAMPLE);

        assertToken(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TYPE, "Meta:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
    }

    @Test
    public void parseMultilineSample() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.MULTILINE_SAMPLE);

        assertToken(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TYPE, "Meta:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
    }

    @Test
    public void parseMultilineLong() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.MULTILINE_LONG_SAMPLE);

        assertToken(StoryTypes.STORY_DESCRIPTION, "Narrative: ");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "In order to play a game");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "As a player");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "I want to be able to create and manage my account");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TYPE, "Meta:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "A known user cannot be logged using a wrong password");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "the following existing users:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " nickname ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " password ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Travis ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   PacMan ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "And ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "he is the user with nickname: \"Bomo\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"McCallum\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "And ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "he tries to login using the password \"Bimo\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "A known user can be logged using the right password");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "the following existing users:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " nickname ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " password ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Travis ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   PacMan ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"PacMan\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "And ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "a welcome message is displayed");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try again to login using the password \"PacMan\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
    }

    @Test
    public void parseMetaSample() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.META_SAMPLE);

        assertToken(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TYPE, "Meta:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@author");
        advanceAndAssert(StoryTypes.META_TEXT, " carmen");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
    }

    @Test
    public void parseLongSample() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.LONG_SAMPLE);

        assertToken(StoryTypes.STORY_DESCRIPTION, "Narrative: ");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "In order to play a game");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "As a player");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.STORY_DESCRIPTION, "I want to be able to create and manage my account");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_TYPE, "Meta:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.META_KEY, "@skip");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "A known user cannot be logged using a wrong password");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "the following existing users:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " nickname ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, " password ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Travis ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   PacMan ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"McCallum\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);

        // ...
    }

    @Test
    public void parseExamples() {
        storyLexerAdapter = new StoryLexerAdapter();
        storyLexerAdapter.start(Samples.EXAMPLES_SAMPLE);

        assertToken(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i am the user with nickname: \"<input>\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHEN_TYPE, "When ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.THEN_TYPE, "Then ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.EXAMPLE_TYPE, "Examples:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  login   ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   password   ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  Travis  ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Pacman     ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "  Vlad    ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.TABLE_CELL, "   Thundercat ");
        advanceAndAssert(StoryTypes.TABLE_DELIM);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, "A known user can be logged using the right password");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.WHITE_SPACE);
        advanceAndAssert(StoryTypes.GIVEN_TYPE, "Given ");
        advanceAndAssert(StoryTypes.STEP_TEXT, "the following existing users:");
        advanceAndAssert(StoryTypes.WHITE_SPACE);
    }

    private void advanceAndAssert(final IElementType storyTokenType) {
        storyLexerAdapter.advance();
        assertThat(storyLexerAdapter.getTokenType()).isEqualTo(storyTokenType);
    }

    private void advanceAndAssert(final IElementType storyTokenType, final String content) {
        storyLexerAdapter.advance();
        assertToken(storyTokenType, content);
    }

    private void assertToken(final IElementType storyTokenType, final String content) {
        assertThat(storyLexerAdapter.getTokenType()).isEqualTo(storyTokenType);
        assertThat(storyLexerAdapter.getTokenText().replaceAll("\\n", "")).isEqualTo(content);
    }
}
