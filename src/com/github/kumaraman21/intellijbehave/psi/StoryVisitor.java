// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class StoryVisitor extends PsiElementVisitor {

  public void visitExampleBlock(@NotNull StoryExampleBlock o) {
    visitPsiElement(o);
  }

  public void visitGivenStep(@NotNull StoryGivenStep o) {
    visitPsiElement(o);
  }

  public void visitMetaInfo(@NotNull StoryMetaInfo o) {
    visitPsiElement(o);
  }

  public void visitNarrativeBlock(@NotNull StoryNarrativeBlock o) {
    visitPsiElement(o);
  }

  public void visitScenarioBlock(@NotNull StoryScenarioBlock o) {
    visitPsiElement(o);
  }

  public void visitScenarioHeader(@NotNull StoryScenarioHeader o) {
    visitPsiElement(o);
  }

  public void visitTable(@NotNull StoryTable o) {
    visitPsiElement(o);
  }

  public void visitThenStep(@NotNull StoryThenStep o) {
    visitPsiElement(o);
  }

  public void visitWhenStep(@NotNull StoryWhenStep o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
