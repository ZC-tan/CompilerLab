import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Visitor extends minisysyBaseVisitor<Void>{
    String expBuilder;

    @Override
    public Void visitCompUnit(minisysyParser.CompUnitContext ctx) {
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitDecl(minisysyParser.DeclContext ctx) {
        return super.visitDecl(ctx);
    }

    @Override
    public Void visitConstDecl(minisysyParser.ConstDeclContext ctx) {
        //每条declare语句只会定义一次 类型
        String vartype = ctx.bType().getText();
        //每条declare可有声明多个变量，为每个创建const变量并存入数组，每个变量打印 alloca
        for(minisysyParser.ConstDefContext variable:ctx.constDef()){
            String varname = variable.IDENT().getText();
            Var var = new Var(varname,vartype,true);
            var.printVarDeclIR();
        }

        //visit every constDef to assign value to each IDENTs
        for(int i=0;i<ctx.constDef().size();i++){
            visit(ctx.constDef(i));
        }
        return null;
    }

    @Override
    public Void visitBType(minisysyParser.BTypeContext ctx) {
        return super.visitBType(ctx);
    }

    @Override
    public Void visitConstDef(minisysyParser.ConstDefContext ctx) {
        Expression initExp = new Expression("");
        visitConstInitVal(ctx.constInitVal(),initExp);
//        System.out.println(initExp.getInfixExp());
        Var.printInitValIR(ctx.IDENT().getText(), initExp.getInfixExp());
        return null;
    }

    @Override
    public Void visitConstInitVal(minisysyParser.ConstInitValContext ctx) {
        return super.visitConstInitVal(ctx);
    }
    public Void visitConstInitVal(minisysyParser.ConstInitValContext ctx,Expression e) {
        visitConstExp(ctx.constExp(),e);
        return null;
    }

    @Override
    public Void visitConstExp(minisysyParser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }
    public Void visitConstExp(minisysyParser.ConstExpContext ctx,Expression e) {
        visitAddExp(ctx.addExp(),e);
        return null;
    }

    @Override
    public Void visitVarDecl(minisysyParser.VarDeclContext ctx) {
        String vartype = ctx.bType().getText();
        for(minisysyParser.VarDefContext variable:ctx.varDef()){
            String varname = variable.IDENT().getText();
            Var var = new Var(varname,vartype);
            var.varDecl();
        }

        return super.visitVarDecl(ctx);
    }

    @Override
    public Void visitVarDef(minisysyParser.VarDefContext ctx) {
//        System.out.println(ctx.initVal().getText());
        if(ctx.initVal()!=null){
            Expression initExp = new Expression("");
            visitInitVal(ctx.initVal(),initExp);
            System.out.println(initExp.getInfixExp());
            System.out.println(initExp.getInfixExp());
            System.out.println(initExp.getInfixExp());
            Var.printInitValIR(ctx.IDENT().getText(), initExp.getInfixExp());
        }
//        System.out.println(initExp.getInfixExp());
        return null;
    }

    @Override
    public Void visitInitVal(minisysyParser.InitValContext ctx) {
        return super.visitInitVal(ctx);
    }
    public Void visitInitVal(minisysyParser.InitValContext ctx,Expression e) {
        visitExp(ctx.exp(),e);
        return null;
    }

    @Override
    public Void visitFuncDef(minisysyParser.FuncDefContext ctx) {
        System.out.println("declare i32 @getint()");
        System.out.println("declare void @putint(i32)");
        System.out.println("declare i32 @getch()");
        System.out.println("declare void @putch(i32)");
        System.out.print("define dso_local");
        visit(ctx.funcType());
        System.out.print("@"+ctx.IDENT().getText()+"()");
        visit(ctx.block());
        return null;
    }

    @Override
    public Void visitFuncType(minisysyParser.FuncTypeContext ctx) {
        System.out.print(" i32 ");
        return null;
    }

    @Override
    public Void visitBlock(minisysyParser.BlockContext ctx) {
        System.out.println("{");
        for(int i=0;i<ctx.blockItem().size();i++){
            visit(ctx.blockItem(i));
        }
        System.out.println("}");
        return null;
    }

    @Override
    public Void visitBlockItem(minisysyParser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Void visitStmt(minisysyParser.StmtContext ctx) {
        if(ctx.getText().startsWith("return")){
            Expression retExp = new Expression("");
            visitExp(ctx.exp(),retExp);
            Func.printMainFuncRetIR(retExp.getInfixExp());
        }
        else if(ctx.lVal()!=null){
            String varName = ctx.lVal().IDENT().getText();
            Expression assignExp = new Expression("");
            visitExp(ctx.exp(),assignExp);
            Var.assignVar(varName,assignExp.getInfixExp());
        }
        else{
            Expression exp = new Expression("");
            visitExp(ctx.exp(),exp);
            if(exp.getInfixExp().length()>0){
                exp.toSuffix();
                exp.expCalc();
            }
        }
        return null;
    }

    @Override
    public Void visitLVal(minisysyParser.LValContext ctx) {
        return super.visitLVal(ctx);
    }
    public Void visitLVal(minisysyParser.LValContext ctx,Expression e) {
        e.appendInfix(ctx.IDENT().getText());
        return null;
    }

    @Override
    public Void visitExp(minisysyParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }
    public Void visitExp(minisysyParser.ExpContext ctx,Expression e) {
        visitAddExp(ctx.addExp(),e);
        return null;
    }

    @Override
    public Void visitAddExp(minisysyParser.AddExpContext ctx) {
        for(int i=0;i<ctx.getChildCount();i++){
            if(ctx.getChild(i).getText().equals("+")){
                expBuilder+="+";
            }
            else if(ctx.getChild(i).getText().equals("-")){
                expBuilder+="-";
            }
            else{//this child is going to be a mulExp
                visit(ctx.getChild(i));
            }
        }

        return null;
    }

    public Void visitAddExp(minisysyParser.AddExpContext ctx , Expression e) {
        int j=0;
        for(int i=0;i<ctx.getChildCount();i++){
            if(ctx.getChild(i).getText().equals("+")){
                e.appendInfix("+");
            }
            else if(ctx.getChild(i).getText().equals("-")){
                e.appendInfix("-");
            }
            else{//this child is going to be a mulExp
                visitMulExp(ctx.mulExp(j),e);
                j++;
            }
        }

        return null;
    }

    @Override
    public Void visitMulExp(minisysyParser.MulExpContext ctx) {
        for(int i=0;i<ctx.getChildCount();i++){
            if(ctx.getChild(i).getText().equals("*")){
                expBuilder+="*";
            }
            else if(ctx.getChild(i).getText().equals("/")){
                expBuilder+="/";
            }
            else if(ctx.getChild(i).getText().equals("%")){
                expBuilder+="%";
            }
            else{//this child is going to be a unaryExp
                visit(ctx.getChild(i));
            }
        }
        return null;
    }

    public Void visitMulExp(minisysyParser.MulExpContext ctx, Expression e) {
        int j=0;
        for(int i=0;i<ctx.getChildCount();i++){
            if(ctx.getChild(i).getText().equals("*")){
                e.appendInfix("*");
            }
            else if(ctx.getChild(i).getText().equals("/")){
                e.appendInfix("/");
            }
            else if(ctx.getChild(i).getText().equals("%")){
                e.appendInfix("%");
            }
            else{//this child is going to be a unaryExp
                visitUnaryExp(ctx.unaryExp(j),e);
                j++;
            }
        }
        return null;
    }

    @Override
    public Void visitUnaryExp(minisysyParser.UnaryExpContext ctx) {
        if(ctx.primaryExp()!=null){
            visit(ctx.primaryExp());
        }
        else if(ctx.IDENT()!=null){
            String funcName = ctx.IDENT().getText();
            if(Func.isRecognizedFunc(funcName)){
                if(ctx.funcRParams()!=null){
                    visit(ctx.funcRParams());
                }
//                Func.callFunc();
            }
            else{
                System.exit(4);
            }
        }
        else if(ctx.unaryOp()!=null){
            if(ctx.unaryOp().getText().equals("+")){
                expBuilder+="+";
            }
            else if(ctx.unaryOp().getText().equals("-")){
                expBuilder+="-";
            }
            visit(ctx.unaryExp());
        }
        return null;
    }

    public Void visitUnaryExp(minisysyParser.UnaryExpContext ctx, Expression e) {
        if(ctx.primaryExp()!=null){
            visitPrimaryExp(ctx.primaryExp(),e);
        }
        else if(ctx.IDENT()!=null){
            String funcName = ctx.IDENT().getText();
            ArrayList<String> params = new ArrayList<>();
            if(Func.isRecognizedFunc(funcName)){
                if(ctx.funcRParams()!=null){
                    //TODO visit funcRParams to get params in String[] form
                    //params should be result of EXP(a Register or a Number)
                    visitFuncRParams(ctx.funcRParams(),params);
//                    for(String x:params) System.out.println(x);
                }
//                Func.callFunc(funcName,params);//TODO this return register
                //create a tempvar for this?
//                e.appendInfix(Var.newTempVar("int",Func.callFunc(funcName,params)).varname);
                String ret = Func.callFunc(funcName,params);
                if(ret != null) e.appendInfix("\\"+ret);
//                System.out.println(e.getInfixExp());
            }
            else{
                System.exit(4);
            }
        }
        else if(ctx.unaryOp()!=null){
            if(ctx.unaryOp().getText().equals("+")){
                e.appendInfix("+");
            }
            else if(ctx.unaryOp().getText().equals("-")){
                e.appendInfix("-");
            }
            visitUnaryExp(ctx.unaryExp(),e);
        }
        return null;
    }

    @Override
    public Void visitPrimaryExp(minisysyParser.PrimaryExpContext ctx) {
        return super.visitPrimaryExp(ctx);
    }
    public Void visitPrimaryExp(minisysyParser.PrimaryExpContext ctx,Expression e) {
        if(ctx.lVal()!=null){
            visitLVal(ctx.lVal(),e);
        }
        else if(ctx.NUMBER()!=null){

            e.appendInfix(ctx.NUMBER().getText());
        }
        else{
            e.appendInfix("(");
            visitExp(ctx.exp(),e);
            e.appendInfix(")");
        }
        return null;
    }

    @Override
    public Void visitUnaryOp(minisysyParser.UnaryOpContext ctx) {
        return super.visitUnaryOp(ctx);
    }

    @Override
    public Void visitFuncRParams(minisysyParser.FuncRParamsContext ctx) {
        return super.visitFuncRParams(ctx);
    }
    public Void visitFuncRParams(minisysyParser.FuncRParamsContext ctx,ArrayList<String> params) {
        int j=0;
        for(int i=0;i<ctx.exp().size();i++){
            Expression e =new Expression("");
            visitExp(ctx.exp(i),e);
            if(e.getInfixExp().length()>0){
                e.toSuffix();
                String res = e.expCalc();
                params.add(res);
            }
        }

//        return super.visitFuncRParams(ctx);
        return null;
    }
}
