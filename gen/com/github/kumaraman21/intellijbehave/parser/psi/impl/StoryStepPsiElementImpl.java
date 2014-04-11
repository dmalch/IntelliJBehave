// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.kumaraman21.intellijbehave.parser.StoryTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.kumaraman21.intellijbehave.parser.psi.*;

public class StoryStepPsiElementImpl extends ASTWrapperPsiElement implements StoryStepPsiElement {

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
