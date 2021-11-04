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
	 * Visit a parse tree produced by {@link minisysyParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(minisysyParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(minisysyParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(minisysyParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link minisysyParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(minisysyParser.ExpContext ctx);
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
}