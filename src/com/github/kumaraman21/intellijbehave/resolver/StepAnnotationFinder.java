package com.github.kumaraman21.intellijbehave.resolver;

import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepMatcher;
import org.jbehave.core.parsers.StepPatternParser;

public class StepAnnotationFinder extends StepDefinitionIterator {

    private StepDefinitionAnnotation matchingAnnotation;
    private StepPatternParser stepPatternParser = new RegexPrefixCapturingPatternParser();

    public StepAnnotationFinder(StoryStepPsiElement storyStepPsiElement) {
        super(storyStepPsiElement.getStepTypePsiElement().getStepType(), storyStepPsiElement);
    }

    @Override
    public boolean processStepDefinition(StepDefinitionAnnotation stepDefinitionAnnotation) {
        StepMatcher stepMatcher = stepPatternParser.parseStep(getStepType(), stepDefinitionAnnotation.getAnnotationText());

        if (getStoryStepPsiElement().getStepTextPsiElement() != null
                && stepMatcher.matches(getStoryStepPsiElement().getStepTextPsiElement().getText())) {

            final Integer newPriority = getPriority(stepDefinitionAnnotation);
            final Integer oldPriority = getPriority(matchingAnnotation);

            if (newPriority > oldPriority) {
                matchingAnnotation = stepDefinitionAnnotation;
            }
        }
        return true;
    }

    private Integer getPriority(final StepDefinitionAnnotation stepDefinitionAnnotation) {
        if (stepDefinitionAnnotation == null) {
            return -1;
        }

        return Integer.valueOf(stepDefinitionAnnotation.getAnnotation().findAttributeValue("priority").getText());
    }

    public StepDefinitionAnnotation getMatchingAnnotation() {
        return matchingAnnotation;
    }
}
