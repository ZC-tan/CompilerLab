import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Visitor extends minisysyBaseVisitor<Void>{
    String toPrint;

    @Override
    public Void visitCompUnit(minisysyParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitFuncDef(minisysyParser.FuncDefContext ctx) {
        toPrint = "define ";
        if(ctx.funcType().getText().equals("int")){
            toPrint += "dso_local i32 ";
        }
        toPrint += "@" + ctx.ident().getText()+"(){";
        System.out.println(toPrint);
        toPrint = "";
        return super.visitFuncDef(ctx);
    }

    @Override
    public Void visitFuncType(minisysyParser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Void visitIdent(minisysyParser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public Void visitBlock(minisysyParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Void visitStmt(minisysyParser.StmtContext ctx) {
        toPrint = "    ret i32 ";
        String exp = ctx.exp().getText();
        int expRes = new Calculator(exp).calc();
        toPrint += "" +expRes;
        System.out.println(toPrint);
        System.out.print("}");
        toPrint="";

        return super.visitStmt(ctx);
    }

    @Override
    public Void visitExp(minisysyParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitAddExp(minisysyParser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Void visitMulExp(minisysyParser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Void visitUnaryExp(minisysyParser.UnaryExpContext ctx) {
        return super.visitUnaryExp(ctx);
    }

    @Override
    public Void visitPrimaryExp(minisysyParser.PrimaryExpContext ctx) {
        return super.visitPrimaryExp(ctx);
    }

    @Override
    public Void visitUnaryOp(minisysyParser.UnaryOpContext ctx) {
        return super.visitUnaryOp(ctx);
    }
}
