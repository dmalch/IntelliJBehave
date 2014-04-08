package com.github.kumaraman21.intellijbehave.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class StoryLexerAdapter extends FlexAdapter {
    public StoryLexerAdapter() {
        super(new StoryLexer((Reader) null));
    }
}
