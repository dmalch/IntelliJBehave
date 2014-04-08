package com.github.kumaraman21.intellijbehave.lexer;

import com.github.kumaraman21.intellijbehave.highlighter.StoryLocalizedLexer;
import com.intellij.lexer.Lexer;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLexerFactory {
    public static final boolean USE_LOCALIZED = false;

    public Lexer createLexer() {
        if (USE_LOCALIZED) {
            return new StoryLocalizedLexer();
        } else {
            return new StoryLexerAdapter();
        }
    }
}
