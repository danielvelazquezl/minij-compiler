package AST;
import AST.Visitor.Visitor;
import semantic.ClassSymbol;

public class VarDecl extends ASTNode {
  public Type t;
  public Identifier i;
  public Exp e;
  
  public VarDecl(Type at, Identifier ai, Exp ae, int ln) {
    super(ln);
    t=at; i=ai; e=ae; 
  }

  public <E> void accept(Visitor v, E cm) {
    v.visit(this, cm);
  }
}
