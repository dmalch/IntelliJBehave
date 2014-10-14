package com.github.kumaraman21.intellijbehave.completion;

import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.github.kumaraman21.intellijbehave.parser.JBehaveStep;
import com.github.kumaraman21.intellijbehave.resolver.JBehaveStepReference;
import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionAnnotation;
import com.github.kumaraman21.intellijbehave.resolver.StepDefinitionIterator;
import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.github.kumaraman21.intellijbehave.utility.ParametrizedString;
import com.github.kumaraman21.intellijbehave.utility.ScanUtils;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.util.Consumer;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.steps.StepType;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import static java.util.Locale.ENGLISH;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryCompletionContributor extends CompletionContributor {
    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull final CompletionResultSet _result) {
        if (parameters.getCompletionType() == CompletionType.BASIC) {
            String prefix = CompletionUtil.findReferenceOrAlphanumericPrefix(parameters);
            CompletionResultSet result = _result.withPrefixMatcher(prefix);

            LocalizedKeywords keywords = lookupLocalizedKeywords(parameters);
            Consumer<LookupElement> consumer = newConsumer(_result);

            addAllKeywords(result.getPrefixMatcher(), consumer, keywords);
            addAllSteps(parameters, result.getPrefixMatcher(), consumer, keywords);
        }
    }

    private LocalizedKeywords lookupLocalizedKeywords(CompletionParameters parameters) {
        Locale locale = ENGLISH;
        ASTNode localeNode = parameters.getOriginalFile().getNode().findChildByType(StoryTokenTypes.COMMENT_WITH_LOCALE);
        if (localeNode != null) {
            Locale localeFound = LocalizedStorySupport.checkForLanguageDefinition(localeNode.getText());
            if (localeFound != null) {
                locale = localeFound;
            }
        }
        return new LocalizedStorySupport().getKeywords(locale);
    }

    private static Consumer<LookupElement> newConsumer(final CompletionResultSet result) {
        return new Consumer<LookupElement>() {
            @Override
            public void consume(LookupElement element) {
                result.addElement(element);
            }
        };
    }

    private static void addAllKeywords(PrefixMatcher prefixMatcher,
                                       Consumer<LookupElement> consumer,
                                       LocalizedKeywords keywords) {
        addIfMatches(consumer, prefixMatcher, keywords.narrative());
        addIfMatches(consumer, prefixMatcher, keywords.asA());
        addIfMatches(consumer, prefixMatcher, keywords.inOrderTo());
        addIfMatches(consumer, prefixMatcher, keywords.iWantTo());
        //
        addIfMatches(consumer, prefixMatcher, keywords.givenStories());
        addIfMatches(consumer, prefixMatcher, keywords.ignorable());
        addIfMatches(consumer, prefixMatcher, keywords.scenario());
        addIfMatches(consumer, prefixMatcher, keywords.examplesTable());
        //
        addIfMatches(consumer, prefixMatcher, keywords.given());
        addIfMatches(consumer, prefixMatcher, keywords.when());
        addIfMatches(consumer, prefixMatcher, keywords.then());
        addIfMatches(consumer, prefixMatcher, keywords.and());
    }

    private static void addIfMatches(Consumer<LookupElement> consumer, PrefixMatcher prefixMatchers, String input) {
        if (prefixMatchers.prefixMatches(input)) {
            consumer.consume(LookupElementBuilder.create(input));
        }
    }

    private static void addAllSteps(CompletionParameters parameters,
                                    PrefixMatcher prefixMatcher,
                                    Consumer<LookupElement> consumer,
                                    LocalizedKeywords keywords) {
        JBehaveStep step = getStepPsiElement(parameters);
        if (step == null) {
            return;
        }

        StepType stepType = step.getStepType();
        String actualStepPrefix = step.getActualStepPrefix();
        //
        String textBeforeCaret = CompletionUtil.findReferenceOrAlphanumericPrefix(parameters);

        // suggest only if at least the actualStepPrefix is complete
        if (isStepTypeComplete(keywords, textBeforeCaret)) {
            StepSuggester stepAnnotationFinder = new StepSuggester(prefixMatcher,
                    stepType,
                    actualStepPrefix,
                    textBeforeCaret,
                    consumer,
                    step.getProject());
            ScanUtils.iterateInContextOf(step, stepAnnotationFinder);
        }
    }

    private static boolean isStepTypeComplete(LocalizedKeywords keywords, String input) {
        return input.startsWith(keywords.given())
                || input.startsWith(keywords.when())
                || input.startsWith(keywords.then())
                || input.startsWith(keywords.and());
    }

    private static JBehaveStep getStepPsiElement(CompletionParameters parameters) {
        PsiElement position = parameters.getPosition();
        PsiElement positionParent = position.getParent();
        if (positionParent instanceof JBehaveStep) {
            return (JBehaveStep) positionParent;
        } else if (position instanceof JBehaveStepReference) {
            return ((JBehaveStepReference) position).getElement();
        } else if (position instanceof JBehaveStep) {
            return (JBehaveStep) position;
        } else {
            return null;
        }
    }

    private static class StepSuggester extends StepDefinitionIterator {

        private final PrefixMatcher prefixMatcher;
        private final String actualStepPrefix;
        private final String textBeforeCaret;
        private final Consumer<LookupElement> consumer;

        private StepSuggester(PrefixMatcher prefixMatcher,
                              StepType stepType,
                              String actualStepPrefix,
                              String textBeforeCaret,
                              Consumer<LookupElement> consumer,
                              Project project) {
            super(stepType, project);
            this.prefixMatcher = prefixMatcher;
            this.actualStepPrefix = actualStepPrefix;
            this.textBeforeCaret = textBeforeCaret;
            this.consumer = consumer;
        }

        @Override
        public boolean processStepDefinition(StepDefinitionAnnotation stepDefinitionAnnotation) {
            StepType annotationStepType = stepDefinitionAnnotation.getStepType();
            if (annotationStepType != getStepType()) {
                return true;
            }
            String annotationText = stepDefinitionAnnotation.getAnnotationText();
            String adjustedAnnotationText = actualStepPrefix + " " + annotationText;

            ParametrizedString pString = new ParametrizedString(adjustedAnnotationText);
            String complete = pString.complete(textBeforeCaret);
            if (StringUtil.isNotEmpty(complete)) {
                PsiAnnotation matchingAnnotation = stepDefinitionAnnotation.getAnnotation();
                consumer.consume(LookupElementBuilder.create(matchingAnnotation, textBeforeCaret + complete));
            } else if (prefixMatcher.prefixMatches(adjustedAnnotationText)) {
                PsiAnnotation matchingAnnotation = stepDefinitionAnnotation.getAnnotation();
                consumer.consume(LookupElementBuilder.create(matchingAnnotation, adjustedAnnotationText));
            }
            return true;
        }
    }
}
