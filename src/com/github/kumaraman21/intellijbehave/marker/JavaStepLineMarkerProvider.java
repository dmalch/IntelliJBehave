package com.github.kumaraman21.intellijbehave.marker;

import com.github.kumaraman21.intellijbehave.language.JBehaveIcons;
import com.github.kumaraman21.intellijbehave.resolver.StepAnnotationPsiReference;
import com.google.common.base.Function;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.github.kumaraman21.intellijbehave.codeInspector.UnusedStepDeclarationInspection.JBEHAVE_ANNOTATIONS;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.intellij.psi.impl.PsiImplUtil.findAttributeValue;

public class JavaStepLineMarkerProvider extends RelatedItemLineMarkerProvider {
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

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement psiElement, Collection<? super RelatedItemLineMarkerInfo> result) {
        if (!(psiElement instanceof PsiAnnotation)) {
            return;
        }

        PsiAnnotation psiAnnotation = (PsiAnnotation) psiElement;

        if (!JBEHAVE_ANNOTATIONS.contains(psiAnnotation.getQualifiedName())) {
            return;
        }

        final PsiAnnotationMemberValue value = findAttributeValue(psiAnnotation, "value");

        if (value != null && referencesContainValueOf(value, StepAnnotationPsiReference.class)) {
            List<PsiElement> properties = from(newArrayList(value.getReferences()))
                    .filter(instanceOf(StepAnnotationPsiReference.class))
                    .transform(toPsiElements()).toList();
            if (properties.size() > 0) {
                result.add(NavigationGutterIconBuilder
                        .create(JBehaveIcons.JB)
                        .setTargets(properties)
                        .setTooltipText("Navigate to steps")
                        .createLineMarkerInfo(psiElement));
            }
        }
    }

    private Function<PsiReference, PsiElement> toPsiElements() {
        return new Function<PsiReference, PsiElement>() {
            @Override
            public PsiElement apply(PsiReference psiReference) {
                return psiReference.getElement();
            }
        };
    }
}
