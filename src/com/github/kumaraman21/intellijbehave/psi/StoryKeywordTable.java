package com.github.kumaraman21.intellijbehave.psi;

import com.google.common.collect.ImmutableList;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes.*;
import static java.util.Collections.emptyList;

public class StoryKeywordTable {
    private Map<IElementType, String> myType2KeywordsTable = new HashMap<IElementType, String>();

    public StoryKeywordTable() {
    }

    public void put(IElementType type, String keyword) {
        if (KEYWORDS.contains(type)) {
            myType2KeywordsTable.put(type, keyword);
        }
    }

    public Collection<String> getStepKeywords() {
        ImmutableList.Builder<String> builder = ImmutableList.builder();

        builder.addAll(getKeywords(STEP_TYPE_GIVEN));
        builder.addAll(getKeywords(STEP_TYPE_WHEN));
        builder.addAll(getKeywords(STEP_TYPE_THEN));
        builder.addAll(getKeywords(STEP_TYPE_AND));

        Collection<String> keywords = builder.build();

        assert keywords != null;

        return keywords;
    }

    @Nullable
    public List<String> getScenarioKeyword() {
        return getKeywords(SCENARIO_TYPE);
    }

    @Nullable
    public List<String> getNarrativeKeywords() {
        return getKeywords(NARRATIVE_TYPE);
    }

    private List<String> getKeywords(IElementType elementType) {
        String keyword = myType2KeywordsTable.get(elementType);
        if (keyword != null) {
            return ImmutableList.of(keyword);
        }
        return emptyList();
    }

}
