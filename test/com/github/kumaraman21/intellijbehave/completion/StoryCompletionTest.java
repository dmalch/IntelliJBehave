package com.github.kumaraman21.intellijbehave.completion;

import com.github.kumaraman21.intellijbehave.language.StoryFileType;
import com.intellij.testFramework.fixtures.CompletionTester;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

public class StoryCompletionTest extends LightPlatformCodeInsightFixtureTestCase {
    private CompletionTester myCompletionTester;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        myFixture.setTestDataPath(getClass().getResource("/completion/").getPath());
        myCompletionTester = new CompletionTester(myFixture);
    }

    public void testEmptyStory() throws Throwable {
        doTest();
    }

    public void testStoryWithNarrative() throws Throwable {
        doTest();
    }

    public void testEmptyScenario() throws Throwable {
        doTest();
    }

    public void testEmptyGiven() throws Throwable {
        doTest();
    }

    public void testComment() throws Throwable {
        doTest();
    }

    private void doTest() throws Throwable {
        String fileName = getTestName(true) + ".story";
        myCompletionTester.doTestVariantsInner(fileName, StoryFileType.STORY_FILE_TYPE);
    }
}
