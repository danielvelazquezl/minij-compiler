package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public class MethodDecl extends ASTNode {
  public Type t;
  public Identifier i;
  public FormalList fl;
  public VarDeclList vl;
  public StatementList sl;
  public Exp e;

  public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl, 
                    StatementList asl, Exp ae, int ln) {
    super(ln);
    t=at; i=ai; fl=afl; vl=avl; sl=asl; e=ae;
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
