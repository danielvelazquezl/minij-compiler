package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class VarDecl extends ASTNode {
  public Type t;
  public Identifier i;
  public Exp e;
  
  public VarDecl(Type at, Identifier ai, Exp ae, int ln) {
    super(ln);
    t=at; i=ai; e=ae; 
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
