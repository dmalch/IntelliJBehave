// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepTextPsiElement;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepTypePsiElement;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryVisitor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoryStepPsiElementImpl extends JBehaveStepPsiElementImpl implements StoryStepPsiElement {

  public StoryStepPsiElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitStepPsiElement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StoryStepTextPsiElement getStepTextPsiElement() {
    return findChildByClass(StoryStepTextPsiElement.class);
  }

  @Override
  @NotNull
  public StoryStepTypePsiElement getStepTypePsiElement() {
    return findNotNullChildByClass(StoryStepTypePsiElement.class);
  }

}
