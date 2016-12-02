
package net.sourceforge.pmd.lang.vm.ast;

/* Generated By:JavaCC: Do not edit this line. TokenMgrError.java Version 3.0 */

public class TokenMgrError extends RuntimeException {
    /*
     * Ordinals for various reasons why an Error of this type can be thrown.
     */

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Lexical error occured.
     */
    static final int LEXICAL_ERROR = 0;

    /**
     * An attempt wass made to create a second instance of a static token
     * manager.
     */
    static final int STATIC_LEXER_ERROR = 1;

    /**
     * Tried to change to an invalid lexical state.
     */
    static final int INVALID_LEXICAL_STATE = 2;

    /**
     * Detected (and bailed out of) an infinite loop in the token manager.
     */
    static final int LOOP_DETECTED = 3;

    /**
     * Indicates the reason why the exception is thrown. It will have one of the
     * above 4 values.
     */
    int errorCode;

    /*
     * Constructors of various flavors follow.
     */

    public TokenMgrError() {
    }

    public TokenMgrError(final String message, final int reason) {
        super(message);
        errorCode = reason;
    }

    public TokenMgrError(final boolean EOFSeen, final int lexState, final int errorLine, final int errorColumn,
            final String errorAfter, final char curChar, final int reason) {
        this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }

    /**
     * Replaces unprintable characters by their espaced (or unicode escaped)
     * equivalents in the given string
     */
    protected static final String addEscapes(final String str) {
        final StringBuffer retval = new StringBuffer();
        char ch;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
            case 0:
                break;
            case '\b':
                retval.append("\\b");
                break;
            case '\t':
                retval.append("\\t");
                break;
            case '\n':
                retval.append("\\n");
                break;
            case '\f':
                retval.append("\\f");
                break;
            case '\r':
                retval.append("\\r");
                break;
            case '\"':
                retval.append("\\\"");
                break;
            case '\'':
                retval.append("\\\'");
                break;
            case '\\':
                retval.append("\\\\");
                break;
            default:
                ch = str.charAt(i);
                if (ch < 0x20 || ch > 0x7e) {
                    final String s = "0000" + Integer.toString(ch, 16);
                    retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                } else {
                    retval.append(ch);
                }
                break;
            }
        }
        return retval.toString();
    }

    /**
     * Returns a detailed message for the Error when it is thrown by the token
     * manager to indicate a lexical error. Parameters : EOFSeen : indicates if
     * EOF caused the lexicl error curLexState : lexical state in which this
     * error occured errorLine : line number when the error occured errorColumn
     * : column number when the error occured errorAfter : prefix that was seen
     * before this error occured curchar : the offending character Note: You can
     * customize the lexical error message by modifying this method.
     */
    protected static String LexicalError(final boolean EOFSeen, final int lexState, final int errorLine,
            final int errorColumn, final String errorAfter, final char curChar) {
        return ("Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: "
                + (EOFSeen ? "<EOF> "
                        : ("\"" + addEscapes(String.valueOf(curChar)) + "\"") + " (" + (int) curChar + "), ")
                + "after : \"" + addEscapes(errorAfter) + "\"");
    }

}
