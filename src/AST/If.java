package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class If extends Statement {
  public Exp e;
  public Statement s1,s2;

  public If(Exp ae, Statement as1, Statement as2, int ln) {
    super(ln);
    e=ae; s1=as1; s2=as2;
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

