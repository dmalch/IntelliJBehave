// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StoryScenario extends PsiElement {

  @NotNull
  List<StoryExample> getExampleList();

  @NotNull
  List<StoryGiven> getGivenList();

  @NotNull
  List<StoryMetaInfo> getMetaInfoList();

  @NotNull
  StoryScenarioHeader getScenarioHeader();

  @NotNull
  List<StoryTable> getTableList();

  @NotNull
  List<StoryThen> getThenList();

  @NotNull
  List<StoryWhen> getWhenList();

}
