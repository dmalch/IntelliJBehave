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

public class StoryScenarioBlockImpl extends ASTWrapperPsiElement implements StoryScenarioBlock {

  public StoryScenarioBlockImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitScenarioBlock(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StoryExampleBlock> getExampleBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryExampleBlock.class);
  }

  @Override
  @NotNull
  public List<StoryGivenStep> getGivenStepList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryGivenStep.class);
  }

  @Override
  @NotNull
  public List<StoryMetaInfo> getMetaInfoList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryMetaInfo.class);
  }

  @Override
  @NotNull
  public StoryScenarioHeader getScenarioHeader() {
    return findNotNullChildByClass(StoryScenarioHeader.class);
  }

  @Override
  @NotNull
  public List<StoryTable> getTableList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryTable.class);
  }

  @Override
  @NotNull
  public List<StoryThenStep> getThenStepList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryThenStep.class);
  }

  @Override
  @NotNull
  public List<StoryWhenStep> getWhenStepList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryWhenStep.class);
  }

}
