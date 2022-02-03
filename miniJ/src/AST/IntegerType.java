package AST;
import AST.Visitor.Visitor;

public class IntegerType extends Type {
  public IntegerType(int ln) {
    super(ln);
  }
  public String accept(Visitor v) {
    return v.visit(this);
  }
}
