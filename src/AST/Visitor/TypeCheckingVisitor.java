package AST.Visitor;

import AST.*;
import Semantic.SymbolTable;

public class TypeCheckingVisitor implements TypeVisitor {

  private SymbolTable symbolTable;

  public TypeCheckingVisitor(SymbolTable st) {
    this.symbolTable = st;
  }

  private boolean equalTo(Type t1, Type t2) {
    if (t1 instanceof IntegerType && t2 instanceof IntegerType) {
      return true;
    } else if (t1 instanceof IdentifierType && t2 instanceof IdentifierType) {
      // needs class declaration extends support
      return true;
    }
    return false;
  }

  public Type visit(Program p) {
    p.m.accept(this);
    for (int i = 0; i < p.cl.size(); i++) {
      p.cl.get(i).accept(this);
    }
    return null;
  }

  public Type visit(MainClass m) {
    symbolTable.enterScope(m.i1.s);
    symbolTable.enterScope("main");
    m.i1.accept(this);
    m.i2.accept(this);
    for (int i = 0; i < m.vdl.size(); i++) {
      m.vdl.get(i).accept(this);
    }
    for (int i = 0; i < m.sl.size(); i++) {
      m.sl.get(i).accept(this);
    }
    symbolTable.exitScope();
    symbolTable.exitScope();
    return null;
  }

  public Type visit(ClassDeclSimple cs) {
    symbolTable.enterScope(cs.i.s);
    cs.i.accept(this);
    for (int i = 0; i < cs.vl.size(); i++) {
      cs.vl.get(i).accept(this);
    }
    for (int i = 0; i < cs.ml.size(); i++) {
      cs.ml.get(i).accept(this);
    }
    symbolTable.exitScope();
    return null;
  }

  public Type visit(ClassDeclExtends n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(VarDecl v) {
    v.t.accept(this);
    v.i.accept(this);
    if (v.e != null) {
      if (!equalTo(v.i.accept(this), v.e.accept(this))) {
        symbolTable.logError("Incompatible variable type declaration", v.t.getLine());
      }
    }
    return null;
  }

  public Type visit(MethodDecl m) {
    symbolTable.enterScope(m.i.s);

    m.i.accept(this);

    for (int i = 0; i < m.fl.size(); i++) {
      m.fl.get(i).accept(this);
    }
    for (int i = 0; i < m.vl.size(); i++) {
      m.vl.get(i).accept(this);
    }
    for (int i = 0; i < m.sl.size(); i++) {
      m.sl.get(i).accept(this);
    }

    if (m.e != null) {
      if (!equalTo(m.t.accept(this), m.e.accept(this))) {
        symbolTable.logError("Incompatible return type", m.t.getLine());
      }
    }

    symbolTable.exitScope();
    return null;
  }

  public Type visit(Formal f) {
    f.i.accept(this);
    f.t.accept(this);
    return f.t;
  }

  public Type visit(IntArrayType ia) {
    return ia;
  }

  public Type visit(IntegerType i) {
    return i;
  }

  public Type visit(IdentifierType id) {
    return id;
  }

  public Type visit(Block n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(If n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(While n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Print p) {
    Type t = p.e.accept(this);
    if (!(t instanceof IntegerType)) {
      symbolTable.logError(
        "Println method only takes Integer arguments",
        p.getLine()
      );
    }
    return null;
  }

  public Type visit(Assign a) {
    if (!symbolTable.isDefined(a.i.s)) {
      symbolTable.logError("Undefined identifier definition", a.i.getLine());
    }

    if (!equalTo(a.i.accept(this), a.e.accept(this))) {
      symbolTable.logError("Incompatible type assignment", a.i.getLine());
    }
    return null;
  }

  public Type visit(ArrayAssign n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(And n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(LessThan n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Plus n) {
    if (
      !(n.e1.accept(this) instanceof IntegerType) ||
      !(n.e2.accept(this) instanceof IntegerType)
    ) {
      symbolTable.logError(
        "Incompatible types to perform operation",
        n.getLine()
      );
    }
    return new IntegerType(n.getLine());
  }

  public Type visit(Minus n) {
    if (
      !(n.e1.accept(this) instanceof IntegerType) ||
      !(n.e2.accept(this) instanceof IntegerType)
    ) {
      symbolTable.logError(
        "Incompatible types to perform operation",
        n.getLine()
      );
    }
    return new IntegerType(n.getLine());
  }

  public Type visit(Times n) {
    if (
      !(n.e1.accept(this) instanceof IntegerType) ||
      !(n.e2.accept(this) instanceof IntegerType)
    ) {
      symbolTable.logError(
        "Incompatible types to perform operation",
        n.getLine()
      );
    }
    return new IntegerType(n.getLine());
  }

  public Type visit(Divide n) {
    if (
      !(n.e1.accept(this) instanceof IntegerType) ||
      !(n.e2.accept(this) instanceof IntegerType)
    ) {
      symbolTable.logError(
        "Incompatible types to perform operation",
        n.getLine()
      );
    }
    return new IntegerType(n.getLine());
  }

  public Type visit(ArrayLookup n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(ArrayLength n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Call n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(IntegerLiteral il) {
    return new IntegerType(il.getLine());
  }

  public Type visit(IdentifierExp n) {
    // System.out.println("identifier exp: " + n.s);
    if (!symbolTable.isDefined(n.s)) {
      // symbolTable.logError(
      //   "Undefined identifier class definition",
      //   n.getLine()
      // );
    }
    return symbolTable.getType(n.s);
  }

  public Type visit(This n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(NewArray n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(NewObject n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Identifier n) {
    return symbolTable.getType(n.s);
  }

  public Type visit(Or n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(GreaterThan n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Equal n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(NotEqual n) {
    // TODO Auto-generated method stub
    return null;
  }

  public Type visit(Display n) {
    // TODO Auto-generated method stub
    return null;
  }
}
