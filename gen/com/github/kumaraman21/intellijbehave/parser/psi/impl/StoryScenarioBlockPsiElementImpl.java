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

public class StoryScenarioBlockPsiElementImpl extends ASTWrapperPsiElement implements StoryScenarioBlockPsiElement {

  public StoryScenarioBlockPsiElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitScenarioBlockPsiElement(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StoryExampleBlockPsiElement> getExampleBlockPsiElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryExampleBlockPsiElement.class);
  }

  @Override
  @NotNull
  public List<StoryMetaInfoPsiElement> getMetaInfoPsiElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryMetaInfoPsiElement.class);
  }

  @Override
  @NotNull
  public StoryScenarioHeaderPsiElement getScenarioHeaderPsiElement() {
    return findNotNullChildByClass(StoryScenarioHeaderPsiElement.class);
  }

  @Override
  @NotNull
  public List<StoryStepPsiElement> getStepPsiElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryStepPsiElement.class);
  }

  @Override
  @NotNull
  public List<StoryTablePsiElement> getTablePsiElementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryTablePsiElement.class);
  }

}
