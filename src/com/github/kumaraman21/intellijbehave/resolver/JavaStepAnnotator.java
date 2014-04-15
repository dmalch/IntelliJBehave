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

import com.github.kumaraman21.intellijbehave.highlighter.StorySyntaxHighlighter;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepsInspection.JBEHAVE_ANNOTATIONS;
import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepsInspection.referencesContainValueOf;
import static com.intellij.psi.impl.PsiImplUtil.findAttributeValue;

public class JavaStepAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (!(psiElement instanceof PsiAnnotation)) {
            return;
        }

        PsiAnnotation psiAnnotation = (PsiAnnotation) psiElement;

        if (!JBEHAVE_ANNOTATIONS.contains(psiAnnotation.getQualifiedName())) {
            return;
        }

        final PsiAnnotationMemberValue value = findAttributeValue(psiAnnotation, "value");

        if (referencesContainValueOf(value, StepAnnotationPsiReference.class)) {
            Annotation annotation = annotationHolder.createInfoAnnotation(value, "Step");
            annotation.setTextAttributes(StorySyntaxHighlighter.STEP_TEXT);
            return;
        }

        annotationHolder.createErrorAnnotation(value, "No usages found for the step");
    }
}
