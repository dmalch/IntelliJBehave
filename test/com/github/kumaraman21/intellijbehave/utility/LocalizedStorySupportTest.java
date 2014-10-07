package com.github.kumaraman21.intellijbehave.utility;

import org.junit.Test;

import static java.util.Locale.FRENCH;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class LocalizedStorySupportTest {

    @Test
    public void checkForLanguageDefinition_validCases() {
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- language:fr")).isEqualTo(FRENCH);
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- language: fr")).isEqualTo(FRENCH);
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- language: fr ")).isEqualTo(FRENCH);
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- language:  fr ")).isEqualTo(FRENCH);
    }

    @Test
    public void checkForLanguageDefinition_invalidCases() {
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- languge:fr")).isNull();
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- Language: fr")).isNull();
        assertThat(LocalizedStorySupport.checkForLanguageDefinition(" !-- lang: fr ")).isNull();
    }
}
