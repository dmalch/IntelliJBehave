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
public class StoryLocalizedLexer_FrenchTest {

    private StoryLocalizedLexer storyLexer;

    @Test
    public void parse_basicScenario() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.changeLocale("fr");
        storyLexer.start("Scénario: une simple sortie\n" +
                "Etant donné que nous allons promener notre chienne\n" +
                "Quand on sera dehors\n" +
                "Alors elle pourra se soulager!");

        assertToken(StoryTypes.SCENARIO_TYPE, "Scénario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " une simple sortie");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Etant donné que");
        advanceAndAssert(StoryTypes.STEP_TEXT, " nous allons promener notre chienne");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "Quand");
        advanceAndAssert(StoryTypes.STEP_TEXT, " on sera dehors");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_STEP_TYPE, "Alors");
        advanceAndAssert(StoryTypes.STEP_TEXT, " elle pourra se soulager!");
        advanceAndAssert(null);
    }

    @Test
    public void parse_commentAllowsToSwitchLanguage() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        // make sure one is not in fr by default
        storyLexer.changeLocale("en");
        storyLexer.start("!-- language:fr\n" +
                "Scénario: une simple sortie\n" +
                "Etant donné que nous allons promener notre chienne\n" +
                "Quand on sera dehors\n" +
                "Alors elle pourra se soulager!");

//        assertToken(StoryTypes.COMMENT_WITH_LOCALE, "!-- language:fr");
        advanceAndAssert(TokenType.WHITE_SPACE);
        advanceAndAssert(StoryTypes.SCENARIO_TYPE, "Scénario:");
        advanceAndAssert(StoryTypes.SCENARIO_TEXT, " une simple sortie");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.GIVEN_STEP_TYPE, "Etant donné que");
        advanceAndAssert(StoryTypes.STEP_TEXT, " nous allons promener notre chienne");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.WHEN_STEP_TYPE, "Quand");
        advanceAndAssert(StoryTypes.STEP_TEXT, " on sera dehors");
        advanceAndAssert(TokenType.WHITE_SPACE);
//        advanceAndAssert(StoryTypes.THEN_STEP_TYPE, "Alors");
        advanceAndAssert(StoryTypes.STEP_TEXT, " elle pourra se soulager!");
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
}
