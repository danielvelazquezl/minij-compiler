package AST.Visitor;

import AST.*;

public interface CodeVisitor {
  public String visit(Display n);
  public String visit(Program n);
  public String visit(MainClass n);
  public String visit(ClassDeclSimple n);
  public String visit(ClassDeclExtends n);
  public String visit(VarDecl n);
  public String visit(MethodDecl n);
  public String visit(Formal n);
  public String visit(IntArrayType n);
  public String visit(IntegerType n);
  public String visit(IdentifierType n);
  public String visit(Block n);
  public String visit(If n);
  public String visit(While n);
  public String visit(Print n);
  public String visit(Assign n);
  public String visit(ArrayAssign n);
  public String visit(And n);
  public String visit(Or n);
  public String visit(LessThan n);
  public String visit(GreaterThan n);
  public String visit(Equal n);
  public String visit(NotEqual n);
  public String visit(Plus n);
  public String visit(Minus n);
  public String visit(Times n);
  public String visit(Divide n);
  public String visit(ArrayLookup n);
  public String visit(ArrayLength n);
  public String visit(Call n);
  public String visit(IntegerLiteral n);
  public String visit(IdentifierExp n);
  public String visit(This n);
  public String visit(NewArray n);
  public String visit(NewObject n);
  public String visit(Identifier n); 
}
