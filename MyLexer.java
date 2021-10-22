import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.RecognitionException;

public class MyLexer extends minisysyLexer{
    public MyLexer(CharStream input) {
        super(input);
    }
    public void recover(LexerNoViableAltException e) throws LexerNoViableAltException{throw e; }
    public void recover(RecognitionException re) throws RecognitionException {throw re; }
}
