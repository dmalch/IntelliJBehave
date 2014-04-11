// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StoryScenarioBlockPsiElement extends PsiElement {

  @NotNull
  List<StoryExampleBlockPsiElement> getExampleBlockPsiElementList();

  @NotNull
  List<StoryMetaInfoPsiElement> getMetaInfoPsiElementList();

  @NotNull
  StoryScenarioHeaderPsiElement getScenarioHeaderPsiElement();

  @NotNull
  List<StoryStepPsiElement> getStepPsiElementList();

  @NotNull
  List<StoryTablePsiElement> getTablePsiElementList();

}
