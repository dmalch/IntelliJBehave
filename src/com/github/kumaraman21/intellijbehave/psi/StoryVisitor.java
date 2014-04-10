// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class StoryVisitor extends PsiElementVisitor {

  public void visitExample(@NotNull StoryExample o) {
    visitPsiElement(o);
  }

  public void visitGiven(@NotNull StoryGiven o) {
    visitPsiElement(o);
  }

  public void visitMetaInfo(@NotNull StoryMetaInfo o) {
    visitPsiElement(o);
  }

  public void visitNarrative(@NotNull StoryNarrative o) {
    visitPsiElement(o);
  }

  public void visitScenario(@NotNull StoryScenario o) {
    visitPsiElement(o);
  }

  public void visitScenarioHeader(@NotNull StoryScenarioHeader o) {
    visitPsiElement(o);
  }

  public void visitTable(@NotNull StoryTable o) {
    visitPsiElement(o);
  }

  public void visitThen(@NotNull StoryThen o) {
    visitPsiElement(o);
  }

  public void visitWhen(@NotNull StoryWhen o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
