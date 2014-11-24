package com.github.kumaraman21.intellijbehave.completion;

import com.github.kumaraman21.intellijbehave.highlighter.StoryTokenTypes;
import com.github.kumaraman21.intellijbehave.parser.StoryElementTypes;
import com.github.kumaraman21.intellijbehave.parser.StoryFile;
import com.github.kumaraman21.intellijbehave.psi.StoryKeywordProvider;
import com.github.kumaraman21.intellijbehave.psi.StoryKeywordTable;
import com.github.kumaraman21.intellijbehave.psi.StoryLanguageService;
import com.github.kumaraman21.intellijbehave.service.JBehaveStepsIndex;
import com.github.kumaraman21.intellijbehave.service.JavaStepDefinition;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.TailTypeDecorator;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PsiElementPattern.Capture;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class StoryCompletionContributor2 extends CompletionContributor {

    public StoryCompletionContributor2() {
        Capture<PsiElement> beforeNarrative = psiElement()
                .beforeLeaf(psiElement().withElementType(StoryTokenTypes.NARRATIVE_TYPE));
        Capture<PsiElement> beforeDescription = psiElement()
                .beforeLeaf(psiElement().withElementType(StoryTokenTypes.STORY_DESCRIPTION));


        Capture<PsiElement> afterNarrative = psiElement()
                .afterLeaf(psiElement().withElementType(StoryTokenTypes.NARRATIVE_TYPE));
        Capture<PsiElement> afterDescription = psiElement()
                .afterLeaf(psiElement().withElementType(StoryTokenTypes.STORY_DESCRIPTION));
        Capture<PsiElement> inScenario = psiElement()
                .inside(psiElement().withElementType(StoryElementTypes.SCENARIO));
        Capture<PsiElement> inStep = psiElement().withElementType(StoryTokenTypes.STEP_TEXT);
        Capture<PsiElement> inComment = psiElement().withElementType(StoryTokenTypes.COMMENTS);

        Capture<PsiElement> a = psiElement()
                .inside(psiElement().withElementType(StoryElementTypes.STORY))
                .andNot(inScenario)
                .andNot(afterNarrative)
                .andNot(afterDescription);

        extend(CompletionType.BASIC, a, beforeNarrativeProvider());
        extend(CompletionType.BASIC, beforeDescription, beforeNarrativeProvider());
        extend(CompletionType.BASIC, beforeNarrative, beforeNarrativeProvider());
        extend(CompletionType.BASIC, afterNarrative.andNot(inComment), afterNarrativeProvider());
        extend(CompletionType.BASIC, afterDescription.andNot(inComment), afterNarrativeProvider());
        extend(CompletionType.BASIC, inScenario.andNot(inStep), inScenarioProvider());
        extend(CompletionType.BASIC, inStep, inStepProvider());
    }

    private CompletionProvider<CompletionParameters> beforeNarrativeProvider() {
        return new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiFile psiFile = parameters.getOriginalFile();
                if (psiFile instanceof StoryFile) {
                    StoryKeywordTable table = getKeywordsTable(psiFile, psiFile.getProject());

                    addKeywordsToResult(table.getNarrativeKeywords(), result, 80);
                    addKeywordsToResult(table.getScenarioKeyword(), result, 70);
                }
            }
        };
    }

    private CompletionProvider<CompletionParameters> afterNarrativeProvider() {
        return new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiFile psiFile = parameters.getOriginalFile();
                if (psiFile instanceof StoryFile) {
                    StoryKeywordTable table = getKeywordsTable(psiFile, psiFile.getProject());

                    addKeywordsToResult(table.getScenarioKeyword(), result, 70);
                }
            }
        };
    }

    private CompletionProvider<CompletionParameters> inScenarioProvider() {
        return new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiFile file = parameters.getOriginalFile();
                if (file instanceof StoryFile) {
                    StoryFile storyFile = (StoryFile) file;
                    Collection<String> stepKeywords = getStepKeywords(storyFile);

                    addKeywordsToResult(stepKeywords, result, 0);
                }
            }
        };
    }

    private CompletionProvider<CompletionParameters> inStepProvider() {
        return new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
                PsiFile file = parameters.getOriginalFile();
                String initialPrefix = result.getPrefixMatcher().getPrefix();
                String flattenedPrefix = initialPrefix.replaceAll("\\s+", " ");

                result = result.withPrefixMatcher(new PlainPrefixMatcher(flattenedPrefix));

                List<JavaStepDefinition> definitions = JBehaveStepsIndex.getInstance(file.getProject()).getAllStepDefinitions(file);

                for (JavaStepDefinition definition : definitions) {
                    Set<String> texts = definition.getAnnotationTexts();
                    for (String text : texts) {
                        text = text.replaceAll("\\$", "").replaceAll("\\s+", " ");

                        PsiMethod method = definition.getAnnotatedMethod();
                        LookupElementBuilder lookup = method != null ? LookupElementBuilder.create(method, text).bold() : LookupElementBuilder.create(text);

                        result.addElement(lookup.withInsertHandler(new StepInsertHandler(initialPrefix.length() - flattenedPrefix.length())));
                    }
                }
            }
        };
    }

    @NotNull
    public static StoryKeywordTable getKeywordsTable(PsiFile originalFile, Project project) {
        StoryKeywordProvider provider = StoryLanguageService.getInstance(project).getKeywordProvider();
        Locale locale = getStoryLocale(originalFile);
        return provider.getKeywordsTable(locale);
    }

    @NotNull
    private static Locale getStoryLocale(PsiFile originalFile) {
        return originalFile instanceof StoryFile ? ((StoryFile) originalFile).getLocale() : StoryFile.getDefaultLocale();
    }

    private Collection<String> getStepKeywords(StoryFile file) {
        List<String> result = new ArrayList<String>();
        StoryKeywordTable table = getKeywordsTable(file, file.getProject());
        result.addAll(table.getStepKeywords());
        return result;
    }

    private static void addKeywordsToResult(Collection<String> keywords, CompletionResultSet result, int priority) {
        for (String keyword : keywords) {
            LookupElement element = createKeywordLookupElement(keyword);
            result.addElement(PrioritizedLookupElement.withPriority(element, (double) priority));
        }
    }

    private static LookupElement createKeywordLookupElement(String keyword) {
        LookupElement result = LookupElementBuilder.create(keyword);

        result = TailTypeDecorator.withTail(result, TailType.SPACE);

        return result;
    }

    private static class StepInsertHandler implements InsertHandler<LookupElement> {
        private int charsToRemove;

        public StepInsertHandler(int charsToRemove) {
            this.charsToRemove = charsToRemove;
        }

        public void handleInsert(InsertionContext context, LookupElement item) {
            if (charsToRemove > 0) {
                final Editor editor = context.getEditor();
                final Document document = editor.getDocument();
                context.commitDocument();

                document.deleteString(context.getStartOffset(), context.getStartOffset() + charsToRemove);
            }
        }
    }
}
