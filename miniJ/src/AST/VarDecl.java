package AST;
import AST.Visitor.Visitor;

public class VarDecl extends ASTNode {
  public Type t;
  public Identifier i;
  public Statement s;
  
  public VarDecl(Type at, Identifier ai, int ln) {
    super(ln);
    t=at; i=ai;
  }

  public VarDecl(Type at, Statement as, int ln) {
    super(ln);
    t=at; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
