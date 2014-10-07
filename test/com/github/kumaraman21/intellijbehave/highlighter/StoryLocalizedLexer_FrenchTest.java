package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import static com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes.*;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLocalizedLexer_FrenchTest {

    private StoryLocalizedLexer storyLexer;

    @Test
    public void parse_basicScenario() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        storyLexer.changeLocale(FRENCH);
        storyLexer.start("Scénario: une simple sortie\n" +
                "Etant donné que nous allons promener notre chienne\n" +
                "Quand on sera dehors\n" +
                "Alors elle pourra se soulager!");

        assertToken(SCENARIO_TYPE, "Scénario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "une simple sortie");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Etant donné que");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "nous allons promener notre chienne");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "Quand");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "on sera dehors");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Alors");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "elle pourra se soulager!");
        advanceAndAssert(null);
    }

    @Test
    public void parse_commentAllowsToSwitchLanguage() {
        storyLexer = new StoryLocalizedLexer(new LocalizedStorySupport());
        // make sure one is not in fr by default
        storyLexer.changeLocale(ENGLISH);
        storyLexer.start("!-- language:fr\n" +
                "Scénario: une simple sortie\n" +
                "Etant donné que nous allons promener notre chienne\n" +
                "Quand on sera dehors\n" +
                "Alors elle pourra se soulager!");

        assertToken(COMMENT_WITH_LOCALE, "!-- language:fr");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TYPE, "Scénario:");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(SCENARIO_TEXT, "une simple sortie");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_GIVEN, "Etant donné que");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "nous allons promener notre chienne");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_WHEN, "Quand");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "on sera dehors");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TYPE_THEN, "Alors");
        advanceAndAssert(WHITE_SPACE);
        advanceAndAssert(STEP_TEXT, "elle pourra se soulager!");
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
