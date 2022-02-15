package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class Display extends Statement {
  public Exp e;

  public Display(Exp re, int ln) {
    super(ln);
    e=re; 
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

