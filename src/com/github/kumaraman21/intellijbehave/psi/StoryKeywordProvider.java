package com.github.kumaraman21.intellijbehave.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface StoryKeywordProvider {
    @NotNull
    StoryKeywordTable getKeywordsTable(Locale locale);
}
