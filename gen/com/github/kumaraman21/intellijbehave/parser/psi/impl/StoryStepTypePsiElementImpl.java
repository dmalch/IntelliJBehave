// This is a generated file. Not intended for manual editing.
package com.github.kumaraman21.intellijbehave.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.kumaraman21.intellijbehave.parser.StoryTypes.*;
import com.github.kumaraman21.intellijbehave.parser.psi.*;

public class StoryStepTypePsiElementImpl extends JBehaveStepTypePsiElementImpl implements StoryStepTypePsiElement {

  public StoryStepTypePsiElementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StoryVisitor) ((StoryVisitor)visitor).visitStepTypePsiElement(this);
    else super.accept(visitor);
  }

}
