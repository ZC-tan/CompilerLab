import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class MyErrorListener extends BaseErrorListener {
    public static final MyErrorListener INSTANCE = new MyErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) throws ParseCancellationException {
//        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        System.exit(1);
    }
}

//exit 1: syntax error
//exit 2: change const
//exit 3: ret null(Func.java)
//exit 10: same varname declared in same block
//exit 11: global init is not constant
