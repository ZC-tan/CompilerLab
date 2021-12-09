// Generated from minisysy.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link minisysyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface minisysyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link minisysyParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(minisysyParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(minisysyParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(minisysyParser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(minisysyParser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(minisysyParser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(minisysyParser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(minisysyParser.ConstExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(minisysyParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(minisysyParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(minisysyParser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(minisysyParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(minisysyParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(minisysyParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(minisysyParser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(minisysyParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(minisysyParser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(minisysyParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(minisysyParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(minisysyParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(minisysyParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(minisysyParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#funcRParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRParams(minisysyParser.FuncRParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(minisysyParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(minisysyParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(minisysyParser.RelExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(minisysyParser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(minisysyParser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(minisysyParser.LOrExpContext ctx);
}