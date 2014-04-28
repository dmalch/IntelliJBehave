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

import com.github.kumaraman21.intellijbehave.resolver.StepAnnotationPsiReference;
import com.google.common.collect.ImmutableList;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jbehave.core.annotations.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.psi.impl.PsiImplUtil.findAttributeValue;

public class UnusedStepDeclarationInspection extends BaseJavaLocalInspectionTool {

    public static final List<String> JBEHAVE_ANNOTATIONS = ImmutableList.of(
            Given.class.getName(),
            When.class.getName(),
            Then.class.getName(),
            Alias.class.getName(),
            Aliases.class.getName());

    public static boolean referencesContainValueOf(PsiElement value, Class ofClass) {
        if (value != null) {
            for (PsiReference reference : value.getReferences()) {
                if (ofClass.isInstance(reference)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    @Override
    public String getShortName() {
        return "UnusedStepDeclaration";
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {

            @Override
            public void visitAnnotation(PsiAnnotation annotation) {
                if (!JBEHAVE_ANNOTATIONS.contains(annotation.getQualifiedName())) {
                    return;
                }

                final PsiAnnotationMemberValue value = findAttributeValue(annotation, "value");

                if (referencesContainValueOf(value, StepAnnotationPsiReference.class)) {
                    return;
                }

                holder.registerProblem(annotation, "Step <code>#ref</code> is never used");
            }
        };
    }

}
