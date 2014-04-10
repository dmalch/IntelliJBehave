package com.github.kumaraman21.intellijbehave.highlighter;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

import static com.github.kumaraman21.intellijbehave.highlighter.StorySyntaxHighlighter.*;
import static com.intellij.icons.AllIcons.FileTypes.Text;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryColorsAndFontsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Story description", STORY_DESCRIPTION),
            new AttributesDescriptor("Scenario keyword", SCENARIO),
            new AttributesDescriptor("Scenario text", SCENARIO_TEXT),
            new AttributesDescriptor("Step keyword", STEP_TYPE),
            new AttributesDescriptor("Step text", STEP_TEXT),
            new AttributesDescriptor("Table delimiter", TABLE_DELIM),
            new AttributesDescriptor("Table cell", TABLE_CELL),
            new AttributesDescriptor("Meta keyword", META_TYPE),
            new AttributesDescriptor("Meta key", META_KEY),
            new AttributesDescriptor("Meta text", META_TEXT),
            new AttributesDescriptor("Line comment", LINE_COMMENT),
            new AttributesDescriptor("Bad Character", BAD_CHARACTER)
    };

    @Nullable
    public Icon getIcon() {
        return Text;
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new StorySyntaxHighlighter();
    }

    @NonNls
    @NotNull
    public String getDemoText() {
        return "Narrative: \n" + //
                "In order to play a game\n" + //
                "As a player\n" + //
                "I want to be able to create and manage my account\n" + //
                "\n" + //
                "Scenario: An unknown user cannot be logged\n" + //
                "\n" + //
                "Meta:\n" + //
                "@author mccallum\n" + //
                "@skip\n" + //
                "\n" + //
                "Given i am the user with nickname: \"weird\"\n" + //
                "When i try to login using the password \"soweird\"\n" + //
                "!-- TODO: Then i get an error message of type \"Wrong Credentials\"\n" + //
                "\n" + //
                "\n" + //
                "Scenario: A known user cannot be logged using a wrong password\n" + //
                "\n" + //
                "Given the following existing users:\n" + //
                "| nickname | password |\n" + //
                "|   Travis |   PacMan |\n" + //
                "Given i am the user with nickname: \"Travis\"\n" + //
                "When i try to login using the password \"McCallum\"\n" + //
                "Then i get an error message of type \"Wrong Credentials\"";
    }

    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    public String getDisplayName() {
        return "JBehave";
    }
}
