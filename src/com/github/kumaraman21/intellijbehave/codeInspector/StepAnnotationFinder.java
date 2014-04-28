package com.github.kumaraman21.intellijbehave.codeInspector;

import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionAnnotation;
import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionIterator;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnnotationMemberValue;
import com.intellij.psi.PsiElement;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepMatcher;
import org.jbehave.core.parsers.StepPatternParser;
import org.jbehave.core.steps.StepType;

public class StepAnnotationFinder extends StepDefinitionIterator {

    private String stepText;
    private StepDefinitionAnnotation annotation;
    private StepPatternParser stepPatternParser = new RegexPrefixCapturingPatternParser();

    public StepAnnotationFinder(StepType stepType, String stepText, PsiElement storyRef) {
        super(stepType, storyRef);
        this.stepText = stepText;
    }

    @Override
    public boolean processStepDefinition(StepDefinitionAnnotation stepDefinitionAnnotation) {
        StepMatcher stepMatcher = stepPatternParser.parseStep(getStepType(), stepDefinitionAnnotation.getAnnotationText());

        if (stepMatcher.matches(stepText)) {
            PsiAnnotation newAnnotation = stepDefinitionAnnotation.getAnnotation();

            final Integer newPriority = getPriority(newAnnotation);
            final Integer oldPriority = getPriority(annotation.getAnnotation());

            if (newPriority > oldPriority) {
                annotation = stepDefinitionAnnotation;
            }
        }
        return true;
    }

    public StepDefinitionAnnotation getMatchingAnnotation() {
        return annotation;
    }

    private Integer getPriority(PsiAnnotation psiAnnotation) {
        if (psiAnnotation == null) {
            return -1;
        }

        PsiAnnotationMemberValue priority = psiAnnotation.findAttributeValue("priority");

        if (priority == null) {
            return -1;
        }

        return Integer.valueOf(priority.getText());
    }
}
