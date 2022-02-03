package AST;
import AST.Visitor.Visitor;

public class IntArrayType extends Type {
  public IntArrayType(int ln) {
    super(ln); 
  }
  public String accept(Visitor v) {
    return v.visit(this);
  }
}
