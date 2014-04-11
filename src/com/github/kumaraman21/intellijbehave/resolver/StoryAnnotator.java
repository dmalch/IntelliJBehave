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
package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class StoryAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (!(psiElement instanceof StoryStepPsiElement)) {
            return;
        }

        /*StoryStepPsiElement storyStepPsiElement = (StoryStepPsiElement) psiElement;

        if (referencesContainValueOf(storyStepPsiElement, StepPsiReference.class)) {
            annotateParameters(storyStepPsiElement, annotationHolder);
            return;
        }

        annotationHolder.createErrorAnnotation(psiElement, "No definition found for the step");*/
    }

    /*private void annotateParameters(StepPsiElement stepPsiElement, AnnotationHolder annotationHolder) {
        String stepText = stepPsiElement.getStepText();
        String annotationText = getAnnotationTextFrom(stepPsiElement);
        ParametrizedString pString = new ParametrizedString(annotationText);

        int offset = stepPsiElement.getTextOffset();
        for (StringToken token : pString.tokenize(stepText)) {
            int length = token.getValue().length();
            if (token.isIdentifier()) {
                annotationHolder.createInfoAnnotation(
                        TextRange.from(offset, length), "Parameter");
            }
            offset += length;
        }
    }*/
}
