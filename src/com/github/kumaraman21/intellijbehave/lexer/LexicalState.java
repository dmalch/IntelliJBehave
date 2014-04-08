package com.github.kumaraman21.intellijbehave.lexer;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public enum LexicalState {
    YYINITIAL(StoryLexer.YYINITIAL),
    IN_DIRECTIVE(StoryLexer.IN_DIRECTIVE),
    IN_STORY(StoryLexer.IN_STORY),
    IN_SCENARIO(StoryLexer.IN_SCENARIO),
    IN_GIVEN(StoryLexer.IN_GIVEN),
    IN_WHEN(StoryLexer.IN_WHEN),
    IN_THEN(StoryLexer.IN_THEN),
    IN_META(StoryLexer.IN_META),
    IN_TABLE(StoryLexer.IN_TABLE),
    IN_EXAMPLES(StoryLexer.IN_EXAMPLES);

    private final int lexerId;

    LexicalState(int lexerId) {
        this.lexerId = lexerId;
    }

    public static LexicalState fromLexer(int lexerId) {
        for (LexicalState state : values()) {
            if (state.lexerId == lexerId) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unsupported lexer id: " + lexerId);
    }
}
