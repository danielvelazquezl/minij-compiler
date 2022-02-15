package AST.Visitor;

import AST.*;
import Parser.sym;
import Semantic.*;

public class VarCheckingVisitor implements Visitor {
  private SymbolTable symbolTable;

  public VarCheckingVisitor(SymbolTable st) {
    this.symbolTable = st;
  }

  public void visit(Display n) {
  }

  public void visit(Program program) {
    program.m.accept(this);
    for (int i = 0; i < program.cl.size(); i++) {
      program.cl.get(i).accept(this);
    }
  }

  public void visit(MainClass mc) {
    ClassSymbol cs = new ClassSymbol(mc.i1);
    symbolTable.addSymbol(cs);
    symbolTable.enterScope(mc.i1.s);
    Identifier main = new Identifier("main", mc.i2.getLine());
    MethodSymbol ms = new MethodSymbol(new IdentifierType("void", mc.i2.getLine()), main);
    symbolTable.addSymbol(ms);
    symbolTable.enterScope(main.s);
    
    mc.i1.accept(this);

    ms.addParam(mc.i2.s, new VariableSymbol(new IdentifierType("String []", mc.i2.getLine()), mc.i2));
    mc.i2.accept(this);

    // var declarations
    for (int i = 0; i < mc.vdl.size(); i++) {
      VarDecl var = mc.vdl.get(i);
      cs.addVariable(new VariableSymbol(var.t, var.i));
      var.accept(this);
    }

    for (int i = 0; i < mc.sl.size(); i++) {
      mc.sl.get(i).accept(this);
    }

    symbolTable.exitScope();
    symbolTable.exitScope();
  }

  public void visit(ClassDeclSimple cds) { 
    ClassSymbol cs = new ClassSymbol(cds.i);
    symbolTable.addSymbol(cs);
    symbolTable.enterScope(cds.i.s);
    cds.i.accept(this);    
    
    for (int i = 0; i < cds.vl.size(); i++) {
      VarDecl v = cds.vl.get(i);
      cs.addVariable(new VariableSymbol(v.t, v.i));
      v.accept(this);
    }

    for (int i = 0; i < cds.ml.size(); i++) {
      MethodSymbol ms = new MethodSymbol(cds.ml.get(i).t, cds.ml.get(i).i);
      cs.addMethod(ms);
      FormalList fl = cds.ml.get(i).fl;
      for (int j = 0; j < fl.size(); j++) {
        ms.addParam(fl.get(j).i.s, new VariableSymbol(fl.get(j).t, fl.get(j).i));
      }
      cds.ml.get(i).accept(this);
    }
    symbolTable.exitScope();
  }

  public void visit(ClassDeclExtends n) {
    // TODO Auto-generated method stub
  }

  public void visit(VarDecl vd) {
    vd.t.accept(this);
    vd.i.accept(this);
    VariableSymbol var = new VariableSymbol(vd.t, vd.i);
    if (symbolTable.isDefined(vd.i.s)) {
      symbolTable.logError("Duplicated variable declaration", vd.i.getLine());
    }
    if (vd.e != null) {
      vd.e.accept(this);
    }
    symbolTable.addSymbol(var);
  }

  public void visit(MethodDecl md) {
    md.i.accept(this);
    md.t.accept(this);
    
    symbolTable.enterScope(md.i.s);
    MethodSymbol ms = new MethodSymbol(md.t, md.i);
    symbolTable.addSymbol(ms);

    for (int i = 0; i < md.fl.size(); i++) {
      md.fl.get(i).accept(this);
      VariableSymbol formalVar = new VariableSymbol(md.fl.get(i).t, md.fl.get(i).i);
      ms.addParam(formalVar.getId(), formalVar);
    }

    for (int i = 0; i < md.vl.size(); i++) {
      md.vl.get(i).accept(this);
    }

    for (int i = 0; i < md.sl.size(); i++) {
      md.sl.get(i).accept(this);
    }
    md.e.accept(this);
    symbolTable.exitScope();
  }

  public void visit(Formal f) {
    VariableSymbol v = new VariableSymbol(f.t, f.i);
    if (symbolTable.isDefined(v.getId())) {
      symbolTable.logError("Duplicated variable declaration", f.i.getLine());
    }
    symbolTable.addSymbol(v);
    f.t.accept(this);
    f.i.accept(this);
  }

  public void visit(IntArrayType intType) {
    // System.out.println("int []");
  }

  public void visit(IntegerType n) {
    // System.out.println("int ");
  }

  public void visit(IdentifierType n) {
    // TODO Auto-generated method stub
    // System.out.println("classType");
  }

  public void visit(Block n) {
    // TODO Auto-generated method stub

  }

  public void visit(If n) {
    // TODO Auto-generated method stub

  }

  public void visit(While n) {
    // TODO Auto-generated method stub

  }

  public void visit(Print n) {
    // TODO Auto-generated method stub

  }

  public void visit(Assign n) {
    // System.out.println("assign");
  }

  public void visit(ArrayAssign n) {
    // TODO Auto-generated method stub

  }

  public void visit(And n) {
    // TODO Auto-generated method stub

  }

  public void visit(Or n) {
    // TODO Auto-generated method stub

  }

  public void visit(LessThan n) {
    // TODO Auto-generated method stub

  }

  public void visit(GreaterThan n) {
    // TODO Auto-generated method stub

  }

  public void visit(Equal n) {
    // TODO Auto-generated method stub

  }

  public void visit(NotEqual n) {
    // TODO Auto-generated method stub

  }

  public void visit(Plus n) {
    // TODO Auto-generated method stub

  }

  public void visit(Minus n) {
    // TODO Auto-generated method stub

  }

  public void visit(Times n) {
    // TODO Auto-generated method stub

  }

  public void visit(Divide n) {
    // TODO Auto-generated method stub

  }

  public void visit(ArrayLookup n) {
    // TODO Auto-generated method stub

  }

  public void visit(ArrayLength n) {
    // TODO Auto-generated method stub

  }

  public void visit(Call n) {
    // TODO Auto-generated method stub

  }

  public void visit(IntegerLiteral n) {
    // System.out.println(n.i);
  }

  public void visit(IdentifierExp n) {
    // System.out.println("identifier exp");
  }

  public void visit(This n) {
    // System.out.println("this");
  }

  public void visit(NewArray n) {
    // System.out.println("new array");
  }

  public void visit(NewObject n) {
    // System.out.println("new object");
  }

  public void visit(Identifier n) {
    // System.out.println("identifier visitor");
  }

}
