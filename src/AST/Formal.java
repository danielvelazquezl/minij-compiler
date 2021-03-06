package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class Formal extends ASTNode{
  public Type t;
  public Identifier i;
 
  public Formal(Type at, Identifier ai, int ln) {
    super(ln);
    t=at; i=ai;
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
}
