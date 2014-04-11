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

import com.github.kumaraman21.intellijbehave.language.StoryFileType;
import com.github.kumaraman21.intellijbehave.language.StoryLanguage;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryScenarioBlockPsiElement;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.google.common.base.Function;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static java.util.Arrays.asList;

public class StoryFile extends PsiFileBase {

    public StoryFile(FileViewProvider fileViewProvider) {
        super(fileViewProvider, StoryLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return StoryFileType.INSTANCE;
    }

    @NotNull
    public List<StoryStepPsiElement> getSteps() {
        return from(getScenarios())
                .transformAndConcat(toSteps()).toList();
    }

    @NotNull
    private List<StoryScenarioBlockPsiElement> getScenarios() {
        return asList(findChildrenByClass(StoryScenarioBlockPsiElement.class));
    }

    private Function<StoryScenarioBlockPsiElement, Iterable<StoryStepPsiElement>> toSteps() {
        return new Function<StoryScenarioBlockPsiElement, Iterable<StoryStepPsiElement>>() {
            @Override
            public Iterable<StoryStepPsiElement> apply(StoryScenarioBlockPsiElement storyScenarioBlockPsiElement) {
                return storyScenarioBlockPsiElement.getStepPsiElementList();
            }
        };
    }
}
