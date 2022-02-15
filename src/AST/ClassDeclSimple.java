package AST;

import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class ClassDeclSimple extends ClassDecl {

  public Identifier i;
  public VarDeclList vl;
  public MethodDeclList ml;

  public ClassDeclSimple(
    Identifier ai,
    VarDeclList avl,
    MethodDeclList aml,
    int ln
  ) {
    super(ln);
    i = ai;
    vl = avl;
    ml = aml;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor tv) {
    return tv.visit(this);
  }

  public String accept(CodeVisitor cv) {
    return cv.visit(this);
  }

  public String getClassId() {
    return i.s;
  }
}
