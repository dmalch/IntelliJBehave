package com.github.kumaraman21.intellijbehave.resolver;

import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepMatcher;
import org.jbehave.core.parsers.StepPatternParser;

import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;

public class StepAnnotationFinder extends StepDefinitionIterator {

	private StepDefinitionAnnotation matchingAnnotation;
	private StepPatternParser stepPatternParser = new RegexPrefixCapturingPatternParser();

	public StepAnnotationFinder(StepPsiElement storyRef) {
		super(storyRef.getStepType(), storyRef);
	}

	@Override
	public boolean processStepDefinition(StepDefinitionAnnotation stepDefinitionAnnotation) {
		StepMatcher stepMatcher = stepPatternParser.parseStep(getStepType(), stepDefinitionAnnotation.getAnnotationText());

		if (stepMatcher.matches(getStoryRef().getStepText())) {

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
