// Generated from minisysy.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link minisysyParser}.
 */
public interface minisysyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link minisysyParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompUnit(minisysyParser.CompUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompUnit(minisysyParser.CompUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(minisysyParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(minisysyParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(minisysyParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(minisysyParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(minisysyParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(minisysyParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(minisysyParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(minisysyParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(minisysyParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(minisysyParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(minisysyParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(minisysyParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#addExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(minisysyParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#addExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(minisysyParser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(minisysyParser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(minisysyParser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(minisysyParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(minisysyParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(minisysyParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(minisysyParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(minisysyParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(minisysyParser.UnaryOpContext ctx);
}