package com.github.kumaraman21.intellijbehave.codeInspector;

import com.github.kumaraman21.intellijbehave.parser.StoryFile;
import com.github.kumaraman21.intellijbehave.parser.psi.StoryStepPsiElement;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.List;
import java.util.Set;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.concurrent.TimeUnit.SECONDS;

public class StepUsageFinder implements ContentIterator {
	private final Project project;
	private final Set<StoryStepPsiElement> stepUsages = newHashSet();

	private static final LoadingCache<CacheKey, List<StoryStepPsiElement>> cache = newBuilder()
			.expireAfterWrite(10, SECONDS)
			.build(new CacheLoader<CacheKey, List<StoryStepPsiElement>>() {
				@Override
				public List<StoryStepPsiElement> load(final CacheKey key) throws Exception {
					return key.getPsiFile().getSteps();
				}
			});

	public StepUsageFinder(Project project) {
		this.project = project;
	}

	@Override
	public boolean processFile(VirtualFile virtualFile) {
		PsiFile psiFile = PsiManager.getInstance(project).findFile(virtualFile);
		if (psiFile instanceof StoryFile) {
			stepUsages.addAll(getSteps((StoryFile) psiFile));
		}
		return true;
	}

	private List<StoryStepPsiElement> getSteps(final StoryFile psiFile) {
		try {
			return cache.getUnchecked(key(psiFile));
		} catch (final Exception e) {
			return newArrayList();
		}
	}

	private CacheKey key(final StoryFile psiFile) {
		return new CacheKey(psiFile);
	}

	public Set<StoryStepPsiElement> getStepUsages() {
		return stepUsages;
	}

	private class CacheKey {
		private final StoryFile psiFile;

		public CacheKey(final StoryFile psiFile) {
			this.psiFile = psiFile;
		}

		public StoryFile getPsiFile() {
			return psiFile;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
                return true;
            }
			if (o == null || getClass() != o.getClass()) {
                return false;
            }

			final CacheKey cacheKey = (CacheKey) o;

			if (!psiFile.getName().equals(cacheKey.psiFile.getName())) {
                return false;
            }

			return true;
		}

		@Override
		public int hashCode() {
			return psiFile.getName().hashCode();
		}
	}
}
