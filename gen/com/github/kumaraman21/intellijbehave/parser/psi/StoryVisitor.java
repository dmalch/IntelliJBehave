// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class StoryVisitor extends PsiElementVisitor {

  public void visitExampleBlockPsiElement(@NotNull StoryExampleBlockPsiElement o) {
    visitPsiElement(o);
  }

  public void visitMetaInfoPsiElement(@NotNull StoryMetaInfoPsiElement o) {
    visitPsiElement(o);
  }

  public void visitNarrativeBlockPsiElement(@NotNull StoryNarrativeBlockPsiElement o) {
    visitPsiElement(o);
  }

  public void visitScenarioBlockPsiElement(@NotNull StoryScenarioBlockPsiElement o) {
    visitPsiElement(o);
  }

  public void visitScenarioHeaderPsiElement(@NotNull StoryScenarioHeaderPsiElement o) {
    visitPsiElement(o);
  }

  public void visitStepPsiElement(@NotNull StoryStepPsiElement o) {
    visitJBehaveStepPsiElement(o);
  }

  public void visitStepTextPsiElement(@NotNull StoryStepTextPsiElement o) {
    visitPsiElement(o);
  }

  public void visitStepTypePsiElement(@NotNull StoryStepTypePsiElement o) {
    visitJBehaveStepTypePsiElement(o);
  }

  public void visitTablePsiElement(@NotNull StoryTablePsiElement o) {
    visitPsiElement(o);
  }

  public void visitJBehaveStepPsiElement(@NotNull JBehaveStepPsiElement o) {
    visitElement(o);
  }

  public void visitJBehaveStepTypePsiElement(@NotNull JBehaveStepTypePsiElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
