package com.github.kumaraman21.intellijbehave.psi;

import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes.*;
import static java.util.Locale.ENGLISH;

public class StoryKeywordProviderImpl implements StoryKeywordProvider {
    private final Map<Locale, StoryKeywordTable> myLanguageKeywords = new HashMap<Locale, StoryKeywordTable>();

    public StoryKeywordProviderImpl() {
        myLanguageKeywords.put(ENGLISH, getStoryKeywordTableFor(ENGLISH));
    }

    @Override
    @NotNull
    public StoryKeywordTable getKeywordsTable(Locale locale) {
        StoryKeywordTable keywordTable = myLanguageKeywords.get(locale);

        if (keywordTable != null) {
            return keywordTable;
        }

        keywordTable = getStoryKeywordTableFor(locale);
        myLanguageKeywords.put(locale, keywordTable);

        return keywordTable;
    }

    @NotNull
    private StoryKeywordTable getStoryKeywordTableFor(Locale locale) {
        LocalizedKeywords localizedKeywords = new LocalizedStorySupport().getKeywords(locale);

        StoryKeywordTable myKeywordsTable = new StoryKeywordTable();
        myKeywordsTable.put(NARRATIVE_TYPE, localizedKeywords.narrative());
        myKeywordsTable.put(SCENARIO_TYPE, localizedKeywords.scenario());
        myKeywordsTable.put(STEP_TYPE_GIVEN, localizedKeywords.given());
        myKeywordsTable.put(STEP_TYPE_WHEN, localizedKeywords.when());
        myKeywordsTable.put(STEP_TYPE_THEN, localizedKeywords.then());
        myKeywordsTable.put(STEP_TYPE_AND, localizedKeywords.and());
        return myKeywordsTable;
    }
}
