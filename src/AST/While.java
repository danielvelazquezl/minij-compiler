package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class While extends Statement {
  public Exp e;
  public Statement s;

  public While(Exp ae, Statement as, int ln) {
    super(ln);
    e=ae; s=as; 
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

