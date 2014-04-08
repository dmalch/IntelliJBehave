// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.kumaraman21.intellijbehave.parser.StoryTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.kumaraman21.intellijbehave.psi.*;

public class StoryScenarioImpl extends ASTWrapperPsiElement implements StoryScenario {

  public StoryScenarioImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitScenario(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StoryExample> getExampleList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryExample.class);
  }

  @Override
  @NotNull
  public List<StoryGiven> getGivenList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryGiven.class);
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
  public List<StoryThen> getThenList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryThen.class);
  }

  @Override
  @NotNull
  public List<StoryWhen> getWhenList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StoryWhen.class);
  }

}
