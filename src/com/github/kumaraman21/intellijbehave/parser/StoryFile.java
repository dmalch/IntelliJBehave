/*
 * Copyright 2011-12 Aman Kumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.kumaraman21.intellijbehave.parser;

import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.github.kumaraman21.intellijbehave.utility.NodeToPsiElement;
import com.github.kumaraman21.intellijbehave.utility.NodeToStepPsiElement;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

import static com.github.kumaraman21.intellijbehave.language.StoryFileType.STORY_FILE_TYPE;
import static com.github.kumaraman21.intellijbehave.parser.StoryElementTypes.*;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;

public class StoryFile extends PsiFileBase {

    public StoryFile(FileViewProvider fileViewProvider) {
        super(fileViewProvider, STORY_FILE_TYPE.getLanguage());
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return STORY_FILE_TYPE;
    }

    @NotNull
    public List<JBehaveStep> getSteps() {

        List<ASTNode> stepNodes = newArrayList();

        for (PsiElement scenario : getScenarios()) {
            ASTNode[] stepNodesOfScenario = scenario.getNode().getChildren(STEPS);
            stepNodes.addAll(asList(stepNodesOfScenario));
        }

        return transform(stepNodes, new NodeToStepPsiElement());
    }

    @NotNull
    private List<PsiElement> getScenarios() {
        PsiElement story = getStory();
        if (story == null) {
            return newArrayList();
        }

        ASTNode[] scenarioNodes = story.getNode().getChildren(TokenSet.create(SCENARIO));
        return transform(asList(scenarioNodes), new NodeToPsiElement());
    }

    private PsiElement getStory() {
        ASTNode[] storyNodes = getNode().getChildren(TokenSet.create(STORY));

        if (storyNodes.length > 0) {
            return storyNodes[0].getPsi();
        }

        return null;
    }

    public Locale getLocale() {
        ASTNode localeNode = getNode().findChildByType(StoryTokenTypes.COMMENT_WITH_LOCALE);
        if (localeNode != null) {
            Locale localeFound = LocalizedStorySupport.checkForLanguageDefinition(localeNode.getText());
            if (localeFound != null) {
                return localeFound;
            }
        }
        return getDefaultLocale();
    }

    public static Locale getDefaultLocale() {
        return Locale.ENGLISH;
    }
}
