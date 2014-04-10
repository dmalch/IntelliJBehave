package com.github.kumaraman21.intellijbehave.highlighter;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.github.kumaraman21.intellijbehave.utility.CharTree;
import com.github.kumaraman21.intellijbehave.utility.JBKeyword;
import com.github.kumaraman21.intellijbehave.utility.LocalizedStorySupport;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ArrayUtil;
import org.jbehave.core.i18n.LocalizedKeywords;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class StoryLocalizedLexer extends LexerBase {

    /**
     * lexical states
     */
    public enum State {
        YYINITIAL,
        IN_DISPATCH,
        IN_STORY,
        IN_SCENARIO,
        IN_STEP,
        IN_TABLE,
        IN_STEP_TABLE,
        IN_META,
        IN_EXAMPLES, IN_OTHER_TABLE
    }

    private final LocalizedStorySupport kwSupport;
    //
    private LocalizedKeywords keywords;
    private CharTree<JBKeyword> charTree;
    private CharSequence buffer = ArrayUtil.EMPTY_CHAR_SEQUENCE;
    //private int startOffset;
    private int endOffset;
    private State state = State.YYINITIAL;
    private int position;
    private IElementType tokenType;
    private int currentTokenStart;

    public StoryLocalizedLexer() {
        this(new LocalizedStorySupport());
    }

    public StoryLocalizedLexer(LocalizedStorySupport kwSupport) {
        this.kwSupport = kwSupport;
        changeLocale("en");
    }

    public void changeLocale(String locale) {
        keywords = kwSupport.getKeywords(locale);
        charTree = new CharTree<JBKeyword>('/', null);
        for (JBKeyword kw : JBKeyword.values()) {
            String asString = kw.asString(keywords);
            charTree.push(asString, kw);
        }
    }

    @Override
    public void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        //this.startOffset = startOffset;
        this.position = startOffset;
        this.endOffset = endOffset;
        this.state = State.values()[initialState];
        this.tokenType = null;
    }

    @Override
    public int getState() {
        advanceIfRequired();
        return state.ordinal();
    }

    @Override
    public IElementType getTokenType() {
        advanceIfRequired();
        return tokenType;
    }

    @Override
    public int getTokenStart() {
        advanceIfRequired();
        return currentTokenStart;
    }

    @Override
    public int getTokenEnd() {
        advanceIfRequired();
        return position;
    }

    @Override
    public CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return endOffset;
    }

    @Override
    public void advance() {
        advanceIfRequired();
        tokenType = null;
    }

    private void advanceIfRequired() {
        if (tokenType == null) {
            locateToken();
        }
    }

    public int getPosition() {
        return position;
    }

    private void locateToken() {
        if (tokenType != null || position >= endOffset) {
            return;
        }

        this.currentTokenStart = position;

        if (consume(keywords.ignorable())) {
            consume(INPUT_CHAR);
            if (state == State.YYINITIAL) {
                String locale = LocalizedStorySupport.checkForLanguageDefinition(tokenText());
                if (locale != null) {
                    changeLocale(locale);
//                    tokenType = StoryTypes.COMMENT_WITH_LOCALE;
                    tokenType = StoryTypes.COMMENT;
                    return;
                }
            }
            tokenType = StoryTypes.COMMENT;
            return;
        }

        switch (state) {
            case YYINITIAL:
            case IN_STORY: {
                CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                if (entry.hasValue()) {
                    tokenType = tokenType(entry.value);
                    position += entry.length;
                    return;
                } else if (consume(CRLF)) {
                    tokenType = TokenType.WHITE_SPACE;
                    return;
                } else {
                    consume(INPUT_CHAR);
                    tokenType = StoryTypes.STORY_DESCRIPTION;
                    return;
                }
            }
            case IN_DISPATCH: {
                if (consume(CRLF) || consume(SPACES)) {
                    tokenType = TokenType.WHITE_SPACE;
                    return;
                }
                CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                tokenType = tokenType(entry.value);
                position += entry.length;
                return;
            }
            case IN_SCENARIO: {
                if (consume(CRLF)) {
                    tokenType = TokenType.WHITE_SPACE;
                    //
                    CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                    if (entry.hasValue()) {
                        switch (entry.value) {
                            case Given:
                            case When:
                            case Then:
                            case And:
                            case Meta:
                            case ExamplesTable:
                            case Narrative:
                            case AsA:
                            case IWantTo:
                            case InOrderTo:
                            case Scenario:
                                state = State.IN_DISPATCH;
                                return;
                            case ExamplesTableHeaderSeparator:
                            case ExamplesTableValueSeparator:
                                state = State.IN_OTHER_TABLE;
                                return;
                        }
                    }
                    return;
                } else {
                    consume(INPUT_CHAR);
                    tokenType = StoryTypes.SCENARIO_TEXT;
                    return;
                }
            }
            case IN_META: {
                CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                if (entry.hasValue()) {
                    switch (entry.value) {
                        case MetaProperty:
                            position += entry.length;
                            consume(META_PROPERTY_CHARS);
                            tokenType = StoryTypes.META_KEY;
                            return;
                        default:
                            tokenType = tokenType(entry.value);
                            position += entry.length;
                            return;
                    }
                } else if (consume(SPACES)) {
                    tokenType = TokenType.WHITE_SPACE;
                    return;
                } else if (consume(CRLF)) {
                    tokenType = TokenType.WHITE_SPACE;

                    //
                    entry = charTree.lookup(buffer, position);
                    if (entry.hasValue()) {
                        switch (entry.value) {
                            case Given:
                            case When:
                            case Then:
                            case And:
                            case Meta:
                            case ExamplesTable:
                            case Narrative:
                            case AsA:
                            case IWantTo:
                            case InOrderTo:
                            case Scenario:
                                state = State.IN_DISPATCH;
                                return;
                            case ExamplesTableHeaderSeparator:
                            case ExamplesTableValueSeparator:
                                state = State.IN_OTHER_TABLE;
                                return;

                        }
                    }
                    return;
                } else {
                    consumeUntil(META_PROPERTY_CHARS, keywords.metaProperty());
                    tokenType = StoryTypes.META_TEXT;
                    return;
                }
            }
            case IN_STEP: {
                if (consume(CRLF)) {
                    tokenType = TokenType.WHITE_SPACE;

                    //
                    CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                    if (entry.hasValue()) {
                        switch (entry.value) {
                            case Given:
                            case When:
                            case Then:
                            case And:
                            case Meta:
                            case ExamplesTable:
                            case Narrative:
                            case AsA:
                            case IWantTo:
                            case InOrderTo:
                            case Scenario:
                                state = State.IN_DISPATCH;
                                return;
                            case ExamplesTableHeaderSeparator:
                            case ExamplesTableValueSeparator:
                                state = State.IN_STEP_TABLE;
                                return;
                        }
                    }
                    return;
                } else {
                    consume(INPUT_CHAR);
                    tokenType = StoryTypes.STEP_TEXT;
                    return;
                }
            }
            case IN_OTHER_TABLE:
            case IN_STEP_TABLE:
            case IN_TABLE: {
                if (consume(CRLF)) {
                    tokenType = TokenType.WHITE_SPACE;
                    //
                    CharTree.Entry<JBKeyword> entry = charTree.lookup(buffer, position);
                    if (entry.hasValue()) {
                        switch (entry.value) {
                            case Given:
                            case When:
                            case Then:
                            case And:
                            case Meta:
                            case ExamplesTable:
                            case Narrative:
                            case AsA:
                            case IWantTo:
                            case InOrderTo:
                            case Scenario:
                                state = State.IN_DISPATCH;
                                return;
                        }
                    }
                    return;
                } else if (consume(keywords.examplesTableHeaderSeparator())) {
                    tokenType = StoryTypes.TABLE_DELIM;
                    return;
                } else if (consume(keywords.examplesTableValueSeparator())) {
                    tokenType = StoryTypes.TABLE_DELIM;
                    return;
                } else if (consumeUntil(INPUT_CHAR, keywords.examplesTableHeaderSeparator(), keywords.examplesTableValueSeparator())) {
                    tokenType = StoryTypes.TABLE_CELL;
                    return;
                }
            }
            case IN_EXAMPLES:
            default:
                throw new UnsupportedOperationException("State: " + state.name());
        }
    }

    private CharSequence tokenText() {
        return buffer.subSequence(this.currentTokenStart, this.position);
    }

    private boolean matchesAhead(String text) {
        if (position + text.length() > endOffset) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != buffer.charAt(position + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean consume(String data) {
        if (matchesAhead(data)) {
            position += data.length();
            return true;
        }
        return false;
    }

    private boolean consumeUntil(CharFilter filter, String stopWord) {
        int previousPosition = position;
        while (position < endOffset && !matchesAhead(stopWord) && filter.accept(buffer.charAt(position))) {
            position++;
        }
        return position != previousPosition;
    }

    private boolean consumeUntil(CharFilter filter, String stopWord1, String stopWord2) {
        int previousPosition = position;
        while (position < endOffset && !(matchesAhead(stopWord1) || matchesAhead(stopWord2))
                && filter.accept(buffer.charAt(position))) {
            position++;
        }
        return position != previousPosition;
    }

    private boolean consume(CharFilter filter) {
        int previousPosition = position;
        while (position < endOffset && filter.accept(buffer.charAt(position))) {
            position++;
        }
        return position != previousPosition;
    }

    public State lexerState() {
        return state;
    }

    public interface CharFilter {
        boolean accept(char c);
    }

    private static CharFilter SPACES = new CharFilter() {
        @Override
        public boolean accept(char c) {
            return c == ' ' || c == '\t';
        }
    };

    private static CharFilter CRLF = new CharFilter() {
        @Override
        public boolean accept(char c) {
            return c == '\r' || c == '\n';
        }
    };

    private static CharFilter INPUT_CHAR = new CharFilter() {
        @Override
        public boolean accept(char c) {
            return c != '\r' && c != '\n';
        }
    };

    private static CharFilter META_PROPERTY_CHARS = new CharFilter() {
        @Override
        public boolean accept(char c) {
            return !SPACES.accept(c) && !CRLF.accept(c);
        }
    };

    private IElementType tokenType(JBKeyword value) {
        if (value == null) {
            return StoryTypes.BAD_CHARACTER;
        }
        switch (value) {
            case Given:
                state = State.IN_STEP;
                return StoryTypes.GIVEN_STEP;
            case When:
                state = State.IN_STEP;
                return StoryTypes.WHEN_STEP;
            case Then:
                state = State.IN_STEP;
                return StoryTypes.THEN_STEP;
            case Ignorable:
            case ExamplesTableIgnorableSeparator:
                return StoryTypes.COMMENT;
            case Narrative:
            case AsA:
            case InOrderTo:
            case ExamplesTable:
                return StoryTypes.EXAMPLE_TYPE;
            case ExamplesTableHeaderSeparator:
            case ExamplesTableValueSeparator:
                state = State.IN_TABLE;
                return StoryTypes.TABLE_DELIM;
            case Meta:
                state = State.IN_META;
                return StoryTypes.META_TYPE;
            case Scenario:
                state = State.IN_SCENARIO;
                return StoryTypes.SCENARIO_TYPE;

            case MetaProperty:
                break;
            case ExamplesTableRow:
                break;
        }
        return StoryTypes.BAD_CHARACTER;
    }


}
