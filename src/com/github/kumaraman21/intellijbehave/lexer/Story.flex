package com.github.kumaraman21.intellijbehave.lexer;

import com.github.kumaraman21.intellijbehave.parser.StoryTypes;
import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;

%%

%{
    private Stack<Integer> yystates = new Stack<Integer> () {{ push(YYINITIAL); }};
    private int currentStepStart = 0;
    public boolean trace = false;

    public void yystatePush(int yystate) {
        if(trace) System.out.println(">>>> PUSH: " + LexicalState.fromLexer(yystate) + " [" + reverseAndMap(yystates) + "]");
        yybegin(yystate);
        yystates.push(yystate);
    }

    private String reverseAndMap(Stack<Integer> yystates) {
        StringBuilder builder = new StringBuilder();
        for(int i=yystates.size()-1; i>=0; i--) {
            if(builder.length()>0)
                builder.append(", ");
            builder.append(LexicalState.fromLexer(yystates.get(i)));
        }
        return builder.toString();
    }

    public void yystatePopNPush(int yystate) {
        yystatePopNPush(1, yystate);
    }

    public void yystatePopNPush(int nb, int yystate) {
        if(trace) System.out.println(">>>> POP'n PUSH : #" + nb + ", " + LexicalState.fromLexer(yystate) + " [" + reverseAndMap(yystates) + "]");
        for (int i = 0; i < nb; i++) {
            yystatePop();
        }
        yystatePush(yystate);
    }

    public int yystatePop() {
        int popped = yystates.pop();
        if(trace) System.out.println(">>>> POP : " + LexicalState.fromLexer(popped) + " [" + reverseAndMap(yystates) + "]");
        if(!yystates.isEmpty()) {
            yybegin(yystates.peek());
        }// otherwise hopes a push will follow right after
        return popped;
    }

  public final int lastIndexOfCrLf(final CharSequence source) {
        final int length = source.length();
        boolean foundRfOrRn = false;

        for (int i = length - 1; i >= 0; i--) {
            final char c = source.charAt(i);
            if (c == '\r' || c == '\n') {
                foundRfOrRn = true;
            } else {
                if (foundRfOrRn) {
                    return i + 1;
                }
            }
        }

        if (foundRfOrRn) {
            return 0;
        } else {
            return -1;
        }
    }

    public void retrieveMultilineText() {
        yypushback(yytext().length() - lastIndexOfCrLf(yytext()));
        if(currentStepStart != 0) {
            zzStartRead = currentStepStart;
            currentStepStart = 0;
        }
    }

    public void setStepStart() {
        if(currentStepStart==0){
            currentStepStart = getTokenStart();
        }
    }

    public boolean checkAhead(char c) {

        if (zzMarkedPos >= zzBuffer.length()) {
            return false;
        }
        return zzBuffer.charAt(zzMarkedPos) == c;
    }
%}

%class StoryLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

CRLF           = \r|\n|\r\n
BlankChar      = [\ \t\f]
WhiteSpace     = {CRLF} | {BlankChar}
NonWhiteSpace  = [^\ \n\r\t\f]
InputChar      = [^\r\n]
TableCellChar  = [^\r\n\|]
MetaText       = [^@\r\n]
GivenWhenThen  = "Given" | "When" | "Then"
KeywordsSteps  = {GivenWhenThen} | "And"
KeywordsShort  = "Scenario:" | "Meta:" | "Examples:" | {GivenWhenThen} | "!--"
KeywordsWithTable  = {KeywordsShort} | "|"
Keywords       = {KeywordsWithTable} | "And"

%state IN_DIRECTIVE
%state IN_STORY
%state IN_SCENARIO
%state IN_GIVEN
%state IN_WHEN
%state IN_THEN
%state IN_META
%state IN_TABLE
%state IN_EXAMPLES

%eof{
    return;
%eof}

%%

<YYINITIAL> {
    {Keywords}{InputChar}+                           { yystatePush(IN_DIRECTIVE); yypushback(yytext().length());         }
    {InputChar}+                                     { return StoryTypes.STORY_DESCRIPTION;                              }
}

<IN_DIRECTIVE> {
    "Scenario:"                                      { yystatePopNPush(2, IN_SCENARIO); return StoryTypes.SCENARIO; }
    "Meta:"                                          { yystatePopNPush(2, IN_META);     return StoryTypes.META;     }
    "Examples:"                                      { yystatePopNPush(2, IN_EXAMPLES); return StoryTypes.EXAMPLE;  }
    "Given"                                          { yystatePopNPush(2, IN_GIVEN);    currentStepStart = 0; return StoryTypes.GIVEN;}
    "When"                                           { yystatePopNPush(2, IN_WHEN);     currentStepStart = 0; return StoryTypes.WHEN; }
    "Then"                                           { yystatePopNPush(2, IN_THEN);     currentStepStart = 0; return StoryTypes.THEN; }
    "!--" {InputChar}*                               { yystatePop();                    return StoryTypes.COMMENT;       }
    "|"                                              { yystatePopNPush(1, IN_TABLE);    return StoryTypes.TABLE_DELIM;   }
}

<IN_SCENARIO> {
    {Keywords}{InputChar}*                           { yystatePush(IN_DIRECTIVE); yypushback(yytext().length()); }
    {NonWhiteSpace}{InputChar}*                      { return StoryTypes.SCENARIO_TEXT; }
}

<IN_META> {
    "@"{NonWhiteSpace}*                              { return StoryTypes.META_KEY;      }
    {Keywords}{InputChar}*                           { yystatePush(IN_DIRECTIVE); yypushback(yytext().length()); }
    {MetaText}+                                      { return StoryTypes.META_TEXT;     }
}

<IN_GIVEN> {
    "And"{InputChar}+{CRLF}({KeywordsSteps} | {BlankChar}*{InputChar})
                                                     { yypushback(yytext().length() - 3); currentStepStart = 0; return StoryTypes.GIVEN;    }
}

<IN_WHEN> {
    "And"{InputChar}+{CRLF}({KeywordsSteps} | {BlankChar}*{InputChar})
                                                     { yypushback(yytext().length() - 3); currentStepStart = 0; return StoryTypes.WHEN;    }
}

<IN_THEN> {
    "And"{InputChar}+{CRLF}({KeywordsSteps} | {BlankChar}*{InputChar})
                                                     { yypushback(yytext().length() - 3); currentStepStart = 0; return StoryTypes.THEN;    }
}

<IN_GIVEN, IN_WHEN, IN_THEN> {
    {KeywordsWithTable}{InputChar}*({CRLF}{InputChar}*)?
                                                     { yystatePush(IN_DIRECTIVE); yypushback(yytext().length()); }
    {NonWhiteSpace}{InputChar}*{CRLF}({KeywordsSteps} | "| " | "")
                                                     { retrieveMultilineText(); return StoryTypes.STEP_TEXT; }
    {NonWhiteSpace}{InputChar}*{CRLF}{BlankChar}*{InputChar}     { setStepStart(); yypushback(1);}
    {NonWhiteSpace}{InputChar}*                      { return StoryTypes.STEP_TEXT; }
}

<IN_EXAMPLES> {
    {Keywords}                                       { yystatePush(IN_DIRECTIVE); yypushback(yytext().length()); }
}

<IN_TABLE> {
    {KeywordsShort}{InputChar}*        { yystatePush(IN_DIRECTIVE); yypushback(yytext().length()); }
    {TableCellChar}+                   { return StoryTypes.TABLE_CELL;  }
    "|"                                { return StoryTypes.TABLE_DELIM; }
    {CRLF}                             { yystatePop(); yypushback(1); }
}

{WhiteSpace}+                          { return TokenType.WHITE_SPACE;    }
.                                      { return StoryTypes.BAD_CHARACTER; }

