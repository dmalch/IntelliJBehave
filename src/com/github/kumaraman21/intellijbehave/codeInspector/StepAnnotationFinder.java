package com.github.kumaraman21.intellijbehave.codeInspector;

import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionAnnotation;
import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionIterator;
import com.intellij.psi.PsiElement;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepMatcher;
import org.jbehave.core.parsers.StepPatternParser;
import org.jbehave.core.steps.StepType;

public class StepAnnotationFinder extends StepDefinitionIterator {

    private StepType stepType;
    private String stepText;
    private StepDefinitionAnnotation matchingAnnotation;
    private StepPatternParser stepPatternParser = new RegexPrefixCapturingPatternParser();

    public StepAnnotationFinder(StepType stepType, String stepText, PsiElement storyRef) {
        super(stepType, storyRef);
        this.stepType = stepType;
        this.stepText = stepText;
    }

    @Override
    public boolean processStepDefinition(StepDefinitionAnnotation stepDefinitionAnnotation) {
        StepMatcher stepMatcher = stepPatternParser.parseStep(stepType, stepDefinitionAnnotation.getAnnotationText());

        if (stepMatcher.matches(stepText)) {
            matchingAnnotation = stepDefinitionAnnotation;

            return false;
        }
        return true;
    }

    public StepDefinitionAnnotation getMatchingAnnotation() {
        return matchingAnnotation;
    }
}
