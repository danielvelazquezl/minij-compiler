package AST.Visitor;

import AST.And;
import AST.ArrayAssign;
import AST.ArrayLength;
import AST.ArrayLookup;
import AST.Assign;
import AST.Block;
import AST.Call;
import AST.ClassDecl;
import AST.ClassDeclExtends;
import AST.ClassDeclList;
import AST.ClassDeclSimple;
import AST.Display;
import AST.Divide;
import AST.Equal;
import AST.Formal;
import AST.GreaterThan;
import AST.Identifier;
import AST.IdentifierExp;
import AST.IdentifierType;
import AST.If;
import AST.IntArrayType;
import AST.IntegerLiteral;
import AST.IntegerType;
import AST.LessThan;
import AST.MainClass;
import AST.MethodDecl;
import AST.Minus;
import AST.NewArray;
import AST.NewObject;
import AST.NotEqual;
import AST.Or;
import AST.Plus;
import AST.Print;
import AST.Program;
import AST.This;
import AST.Times;
import AST.VarDecl;
import AST.While;
import Semantic.ClassSymbol;
import Semantic.Error;
import Semantic.SymbolTable;
import java_cup.symbol;

public class SemanticVisitor implements Visitor {
  private SymbolTable symbolTable;

  public SemanticVisitor(SymbolTable st) {
    this.symbolTable = st;
  }

  public void visit(Display n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(Program program) {
    program.m.accept(this);
    for (int i = 0; i < program.cl.size(); i++) {
      program.cl.get(i).accept(this);
    }
  }

  public void visit(MainClass mc) {
    if (!this.symbolTable.addClass(new ClassSymbol(mc.i1.s, mc.getLine()))) {
      this.symbolTable.logError(new Error("Duplicated Main Class", mc.getLine()));
    }
  }

  public void visit(ClassDeclSimple cds) {
    if (!this.symbolTable.addClass(new ClassSymbol(cds.i.s, cds.getLine()))){
      this.symbolTable.logError(new Error("Duplicated class declaration", cds.getLine()));
    }
  }

  public void visit(ClassDeclExtends n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(VarDecl n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(MethodDecl n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(Formal n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(IntArrayType n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(IntegerType n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(IdentifierType n) {
    // TODO Auto-generated method stub
    
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
    // TODO Auto-generated method stub
    
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
    // TODO Auto-generated method stub
    
  }

  public void visit(IdentifierExp n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(This n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(NewArray n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(NewObject n) {
    // TODO Auto-generated method stub
    
  }

  public void visit(Identifier n) {
    // TODO Auto-generated method stub
    
  }

}
