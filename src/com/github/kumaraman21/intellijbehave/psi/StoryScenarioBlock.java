// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StoryScenarioBlock extends PsiElement {

  @NotNull
  List<StoryExampleBlock> getExampleBlockList();

  @NotNull
  List<StoryGivenStep> getGivenStepList();

  @NotNull
  List<StoryMetaInfo> getMetaInfoList();

  @NotNull
  StoryScenarioHeader getScenarioHeader();

  @NotNull
  List<StoryTable> getTableList();

  @NotNull
  List<StoryThenStep> getThenStepList();

  @NotNull
  List<StoryWhenStep> getWhenStepList();

}
