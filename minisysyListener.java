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
	 * Enter a parse tree produced by {@link minisysyParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(minisysyParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(minisysyParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(minisysyParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(minisysyParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#bType}.
	 * @param ctx the parse tree
	 */
	void enterBType(minisysyParser.BTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#bType}.
	 * @param ctx the parse tree
	 */
	void exitBType(minisysyParser.BTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#constDef}.
	 * @param ctx the parse tree
	 */
	void enterConstDef(minisysyParser.ConstDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#constDef}.
	 * @param ctx the parse tree
	 */
	void exitConstDef(minisysyParser.ConstDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void enterConstInitVal(minisysyParser.ConstInitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void exitConstInitVal(minisysyParser.ConstInitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#constExp}.
	 * @param ctx the parse tree
	 */
	void enterConstExp(minisysyParser.ConstExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#constExp}.
	 * @param ctx the parse tree
	 */
	void exitConstExp(minisysyParser.ConstExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(minisysyParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(minisysyParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(minisysyParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(minisysyParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#initVal}.
	 * @param ctx the parse tree
	 */
	void enterInitVal(minisysyParser.InitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#initVal}.
	 * @param ctx the parse tree
	 */
	void exitInitVal(minisysyParser.InitValContext ctx);
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
	 * Enter a parse tree produced by {@link minisysyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(minisysyParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(minisysyParser.BlockItemContext ctx);
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
	 * Enter a parse tree produced by {@link minisysyParser#lVal}.
	 * @param ctx the parse tree
	 */
	void enterLVal(minisysyParser.LValContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#lVal}.
	 * @param ctx the parse tree
	 */
	void exitLVal(minisysyParser.LValContext ctx);
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
	 * Enter a parse tree produced by {@link minisysyParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(minisysyParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(minisysyParser.CondContext ctx);
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
	 * Enter a parse tree produced by {@link minisysyParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncRParams(minisysyParser.FuncRParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncRParams(minisysyParser.FuncRParamsContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link minisysyParser#relExp}.
	 * @param ctx the parse tree
	 */
	void enterRelExp(minisysyParser.RelExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#relExp}.
	 * @param ctx the parse tree
	 */
	void exitRelExp(minisysyParser.RelExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#eqExp}.
	 * @param ctx the parse tree
	 */
	void enterEqExp(minisysyParser.EqExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#eqExp}.
	 * @param ctx the parse tree
	 */
	void exitEqExp(minisysyParser.EqExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#lAndExp}.
	 * @param ctx the parse tree
	 */
	void enterLAndExp(minisysyParser.LAndExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#lAndExp}.
	 * @param ctx the parse tree
	 */
	void exitLAndExp(minisysyParser.LAndExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link minisysyParser#lOrExp}.
	 * @param ctx the parse tree
	 */
	void enterLOrExp(minisysyParser.LOrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link minisysyParser#lOrExp}.
	 * @param ctx the parse tree
	 */
	void exitLOrExp(minisysyParser.LOrExpContext ctx);
}