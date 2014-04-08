// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi.impl;

import com.github.kumaraman21.intellijbehave.psi.StoryGiven;
import com.github.kumaraman21.intellijbehave.psi.StoryGivenStep;
import com.github.kumaraman21.intellijbehave.psi.StoryVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class StoryGivenImpl extends ASTWrapperPsiElement implements StoryGiven {

  public StoryGivenImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitGiven(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public StoryGivenStep getGivenStep() {
    return findNotNullChildByClass(StoryGivenStep.class);
  }

}
