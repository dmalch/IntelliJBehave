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
package com.github.kumaraman21.intellijbehave.codeInspector;

import com.github.kumaraman21.intellijbehave.highlighter.StorySyntaxHighlighter;
import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;
import com.github.kumaraman21.intellijbehave.resolver.StepPsiReference;
import com.github.kumaraman21.intellijbehave.utility.ParametrizedString;
import com.intellij.codeInspection.*;
import com.intellij.codeInspection.ex.ProblemDescriptorImpl;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepsInspection.referencesContainValueOf;
import static com.intellij.psi.impl.PsiImplUtil.findAttributeValue;

public class UndefinedStepsInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new PsiElementVisitor() {

            @Override
            public void visitElement(PsiElement psiElement) {
                super.visitElement(psiElement);

                if (!(psiElement instanceof StepPsiElement)) {
                    return;
                }

                StepPsiElement stepPsiElement = (StepPsiElement) psiElement;

                if (referencesContainValueOf(stepPsiElement, StepPsiReference.class)) {
                    highlightParameters(stepPsiElement, holder);
                    return;
                }

                holder.registerProblem(stepPsiElement, "Step <code>#ref</code> is not defined");
            }
        };
    }


    private void highlightParameters(StepPsiElement stepPsiElement,
                                     ProblemsHolder holder) {
        String stepText = stepPsiElement.getStepText();
        String annotationText = getAnnotationTextFrom(stepPsiElement);
        ParametrizedString pString = new ParametrizedString(annotationText);

        int offset = stepPsiElement.getStepTextOffset();
        for (ParametrizedString.StringToken token : pString.tokenize(stepText)) {
            int length = token.getValue().length();
            if (token.isIdentifier()) {
                registerHiglighting(StorySyntaxHighlighter.TABLE_CELL,
                        stepPsiElement,
                        TextRange.from(offset, length),
                        holder);
            }
            offset += length;
        }
    }

    public static String getAnnotationTextFrom(StepPsiElement value) {
        if (value != null) {
            for (PsiReference reference : value.getReferences()) {
                if (PsiAnnotation.class.isInstance(reference)) {
                    final PsiAnnotationMemberValue valueAttribute = findAttributeValue((PsiAnnotation) reference, "value");
                    if (valueAttribute != null) {
                        return valueAttribute.getText();
                    }
                }
            }
        }
        return "";
    }

    private static void registerHiglighting(TextAttributesKey attributesKey,
                                            StepPsiElement step,
                                            TextRange range,
                                            ProblemsHolder holder) {
        final ProblemDescriptor descriptor = new ProblemDescriptorImpl(
                step, step, "", LocalQuickFix.EMPTY_ARRAY,
                ProblemHighlightType.INFORMATION, false, range, false, null,
                holder.isOnTheFly());
        descriptor.setTextAttributes(attributesKey);
        holder.registerProblem(descriptor);
    }
}

