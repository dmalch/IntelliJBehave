package com.github.kumaraman21.intellijbehave.psi;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;

public class StoryLanguageService {
    private StoryKeywordProvider myKeywordProvider = new StoryKeywordProviderImpl();

    public static StoryLanguageService getInstance(Project project) {
        return ServiceManager.getService(project, StoryLanguageService.class);
    }

    public StoryKeywordProvider getKeywordProvider() {
        return myKeywordProvider;
    }
}
