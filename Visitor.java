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
//        System.out.println("declare i32 @getint()");
//        System.out.println("declare void @putint(i32)");
//        System.out.println("declare i32 @getch()");
//        System.out.println("declare void @putch(i32)");
//        System.out.print("define dso_local");
        IR.toPrint.add("declare i32 @getint()");
        IR.toPrint.add("declare void @putint(i32)");
        IR.toPrint.add("declare i32 @getch()");
        IR.toPrint.add("declare void @putch(i32)");
        IR.toPrint.add("define dso_local");
        visit(ctx.funcType());
//        System.out.println("@"+ctx.IDENT().getText()+"(){");
        IR.appendLast("@"+ctx.IDENT().getText()+"(){");
        visit(ctx.block());
        return null;
    }

    @Override
    public Void visitFuncType(minisysyParser.FuncTypeContext ctx) {
        IR.appendLast(" i32 ");
//        System.out.print(" i32 ");
        return null;
    }

    @Override
    public Void visitBlock(minisysyParser.BlockContext ctx) {
//        System.out.println("{");
        for(int i=0;i<ctx.blockItem().size();i++){
            visit(ctx.blockItem(i));
        }
//        System.out.println("}");
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
            IR.toPrint.add("}");
//            System.out.println("}");
        }
        else if(ctx.lVal()!=null){
            String varName = ctx.lVal().IDENT().getText();
            Expression assignExp = new Expression("");
            visitExp(ctx.exp(),assignExp);
            Var.assignVar(varName,assignExp.getInfixExp());
        }
        else if(ctx.cond()!=null){
            if(ctx.getChildCount()==5){// if E then M S1
                //calculate E(cond)
                Cond cond = new Cond();
                Expression condExp = new Expression("");
                visitCond(ctx.cond(),condExp);
                String condRes = condExp.expCalc(true);
                //E->true and E->false
                cond.true_list.add(IR.nextQuad()+"");
                cond.false_list.add(IR.nextQuad()+"");
                IR.toPrint.add("br i1 "+condRes+","+"label "+"@TC" +", label "+"@FC");
                //meet M (Mlabel= Mquad in bilibili)
                String MLabel = Register.newBlock();
                //backpatch(E.truelist,M.quad)
                cond.backpatch(MLabel,true);
                //true:start doing S1 and jump to end
                IR.toPrint.add(MLabel.substring(1)+":");
                visitStmt(ctx.stmt(0));
                String NLabel = Register.newBlock();
                IR.toPrint.add("br label "+NLabel);
                //false: do the rest
                IR.toPrint.add(NLabel.substring(1)+":");
                cond.backpatch(NLabel,false);
            }
            else{//if E then M1 S1 N else M2 S2
                ArrayList<String> nextList = new ArrayList<>();
                //calculate E(cond)
                Cond cond = new Cond();
                Expression condExp = new Expression("");
                visitCond(ctx.cond(),condExp);

                String condRes = condExp.expCalc(true);
                //E->true and E->false
                cond.true_list.add(IR.nextQuad()+"");
                cond.false_list.add(IR.nextQuad()+"");
                IR.toPrint.add("br i1 "+condRes+","+"label "+"@TC" +", label "+"@FC");
                //meet M (Mlabel= Mquad in bilibili)
                String MLabel1 = Register.newBlock();
                //backpatch(E.truelist,M.quad)
                cond.backpatch(MLabel1,true);
                //true:start doing S1 and jump to end
                IR.toPrint.add(MLabel1.substring(1)+":");
                visitStmt(ctx.stmt(0));
                //finished (IF E then STMT) need to br(but not sure where cuz can have a lot of else's)
                nextList.add(IR.nextQuad()+"");
                IR.toPrint.add("br label "+"@C");
                //start else
                String MLabel2 = Register.newBlock();
                IR.toPrint.add(MLabel2.substring(1)+":");
                cond.backpatch(MLabel2,false);
                visitStmt(ctx.stmt(1));
                nextList.add(IR.nextQuad()+"");
                IR.toPrint.add("br label "+"@C");
                String endLabel = Register.newBlock();
                IR.toPrint.add(endLabel.substring(1)+":");
                Cond.backpatch(nextList,endLabel);
            }

//            String true_exit=Register.newBlock();
//            String false_exit=Register.newBlock();
//
//            System.out.println(true_exit+":");
//            visitStmt(ctx.stmt(0));
//            if(ctx.stmt().size()>1){ //have else statement
//                System.out.println(false_exit+":");
//                visitStmt(ctx.stmt(1));
//                System.out.printf("br label ");//TODO
//            }
//            String end_label = Register.newBlock();
//            System.out.println("br label "+end_label);
//            System.out.println(end_label+"");
        }
        else if(ctx.block()!=null){
            visitBlock(ctx.block());
        }
        else{
            Expression exp = new Expression("");
            visitExp(ctx.exp(),exp);
            if(exp.getInfixExp().length()>0){
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
                    //params should be result of EXP(a Register or a Number)
                    visitFuncRParams(ctx.funcRParams(),params);
//                    for(String x:params) System.out.println(x);
                }
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
            else if(ctx.unaryOp().getText().equals("!")){
                e.appendInfix("!");
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
                String res = e.expCalc();
                params.add(res);
            }
        }

//        return super.visitFuncRParams(ctx);
        return null;
    }

    @Override
    public Void visitCond(minisysyParser.CondContext ctx) {
        return super.visitCond(ctx);
    }
    public Void visitCond(minisysyParser.CondContext ctx,Expression e) {
        for(int i=0;i<ctx.getChildCount();i++){
            visitLOrExp(ctx.lOrExp(),e);
        }
        return null;
    }

    @Override
    public Void visitRelExp(minisysyParser.RelExpContext ctx) {
        return super.visitRelExp(ctx);
    }
    public Void visitRelExp(minisysyParser.RelExpContext ctx,Expression e) {
        if(ctx.getChildCount()==1){
            visitAddExp(ctx.addExp(),e);
        }
        else{
            visitRelExp(ctx.relExp(),e);
            if(ctx.getChild(1).getText().equals("<")) e.appendInfix("<");
            if(ctx.getChild(1).getText().equals("<=")) e.appendInfix("<=");
            if(ctx.getChild(1).getText().equals(">")) e.appendInfix(">");
            if(ctx.getChild(1).getText().equals(">=")) e.appendInfix(">=");
            visitAddExp(ctx.addExp(),e);
        }

        return null;
    }

    @Override
    public Void visitEqExp(minisysyParser.EqExpContext ctx) {
        return super.visitEqExp(ctx);
    }
    public Void visitEqExp(minisysyParser.EqExpContext ctx,Expression e) {
        if(ctx.getChildCount()==1){
            visitRelExp(ctx.relExp(),e);
        }
        else{
            visitEqExp(ctx.eqExp(),e);
            if(ctx.getChild(1).getText().equals("==")) e.appendInfix("==");
            if(ctx.getChild(1).getText().equals("!=")) e.appendInfix("!=");
            visitRelExp(ctx.relExp(),e);
        }

        return null;
    }

    @Override
    public Void visitLAndExp(minisysyParser.LAndExpContext ctx) {
        return super.visitLAndExp(ctx);
    }
    public Void visitLAndExp(minisysyParser.LAndExpContext ctx,Expression e) {
        if(ctx.getChildCount()==1){
            visitEqExp(ctx.eqExp(),e);
        }
        else{
            visitLAndExp(ctx.lAndExp(),e);
            e.appendInfix("&&");
            visitEqExp(ctx.eqExp(),e);
        }
        return null;
    }

    @Override
    public Void visitLOrExp(minisysyParser.LOrExpContext ctx) {
        return super.visitLOrExp(ctx);
    }
    public Void visitLOrExp(minisysyParser.LOrExpContext ctx,Expression e) {
        if(ctx.getChildCount()==1){
            visitLAndExp(ctx.lAndExp(),e);
        }
        else{
            visitLOrExp(ctx.lOrExp(),e);
            e.appendInfix("||");
            visitLAndExp(ctx.lAndExp(),e);
        }
        return null;
    }
}
