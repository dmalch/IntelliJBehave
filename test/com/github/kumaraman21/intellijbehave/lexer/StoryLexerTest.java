package com.github.kumaraman21.intellijbehave.lexer;

import com.intellij.psi.tree.IElementType;
import org.junit.Before;
import org.junit.Test;

import static com.github.kumaraman21.intellijbehave.Samples.*;
import static com.github.kumaraman21.intellijbehave.parser.StoryTokenType.*;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLexerTest {

    private StoryLocalizedLexer storyLexer;

    @Before
    public void setUp() throws Exception {
        storyLexer = new StoryLocalizedLexer();
    }

    @Test
    public void parseSimpleSample() {
        storyLexer.start(SIMPLE_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseMultilineSample() {
        storyLexer.start(MULTILINE_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseMultilineLong() {
        storyLexer.start(MULTILINE_LONG_SAMPLE);

        assertToken(STORY_DESCRIPTION, "Narrative: ");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "In order to play a game");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "As a player");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "I want to be able to create and manage my account");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "A known user cannot be logged using a wrong password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
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
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "And ");
        advanceAndAssert(STEP_TEXT, "he is the user with nickname: \"Bomo\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"McCallum\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "And ");
        advanceAndAssert(STEP_TEXT, "he tries to login using the password \"Bimo\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "A known user can be logged using the right password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
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
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"PacMan\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "And ");
        advanceAndAssert(STEP_TEXT, "a welcome message is displayed");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try again to login using the password \"PacMan\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseMetaSample() {
        storyLexer.start(META_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@author");
        advanceAndAssert(META_TEXT, " carmen");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseLongSample() {
        storyLexer.start(LONG_SAMPLE);

        assertToken(STORY_DESCRIPTION, "Narrative: ");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "In order to play a game");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "As a player");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STORY_DESCRIPTION, "I want to be able to create and manage my account");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META, "Meta:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(META_KEY, "@skip");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"weird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "A known user cannot be logged using a wrong password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
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
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"Travis\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"McCallum\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
    }

    @Test
    public void parseExamples() {
        storyLexer.start(EXAMPLES_SAMPLE);

        assertToken(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "An unknown user cannot be logged");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "i am the user with nickname: \"<input>\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHEN_TYPE, "When ");
        advanceAndAssert(STEP_TEXT, "i try to login using the password \"soweird\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(THEN_TYPE, "Then ");
        advanceAndAssert(STEP_TEXT, "i get an error message of type \"Wrong Credentials\"");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(EXAMPLE_TYPE, "Examples:");
        advanceAndAssert(WHITE_SPACE);
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
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scenario: ");
        advanceAndAssert(SCENARIO_TEXT, "A known user can be logged using the right password");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(GIVEN_TYPE, "Given ");
        advanceAndAssert(STEP_TEXT, "the following existing users:");
        advanceAndAssert(WHITE_SPACE);
    }

    private void advanceAndAssert(final IElementType storyTokenType) {
        storyLexer.advance();
        assertThat(storyLexer.getTokenType()).isEqualTo(storyTokenType);
    }

    private void advanceAndAssert(final IElementType storyTokenType, final String content) {
        storyLexer.advance();
        assertToken(storyTokenType, content);
    }

    private void assertToken(final IElementType storyTokenType, final String content) {
        assertThat(storyLexer.getTokenType()).isEqualTo(storyTokenType);
        assertThat(storyLexer.getTokenText().replaceAll("\\n", "")).isEqualTo(content);
    }
}
