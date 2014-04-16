package com.github.kumaraman21.intellijbehave.codeInspector;

import com.github.kumaraman21.intellijbehave.parser.StepPsiElement;
import com.github.kumaraman21.intellijbehave.parser.StoryFileImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class StepUsageFinder implements ContentIterator {
    private Project project;
    private Set<StepPsiElement> stepUsages = newHashSet();

    public StepUsageFinder(Project project) {
        this.project = project;
    }

    @Override
    public boolean processFile(VirtualFile virtualFile) {

        if (virtualFile.isDirectory() || !virtualFile.getFileType().getDefaultExtension().equals("story")) {
            return true;
        }

        PsiFile psiFile = PsiManager.getInstance(project).findFile(virtualFile);
        if (psiFile instanceof StoryFileImpl) {
            List<StepPsiElement> stepPsiElements = ((StoryFileImpl) psiFile).getSteps();
            stepUsages.addAll(stepPsiElements);
        }
        return true;
    }

    public Set<StepPsiElement> getStepUsages() {
        return stepUsages;
    }
}
