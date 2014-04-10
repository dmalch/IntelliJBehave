package com.github.kumaraman21.intellijbehave.lexer;

import com.intellij.lexer.Lexer;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLexerFactory {
    public Lexer createLexer() {
        return new StoryLexerAdapter();
    }
}
