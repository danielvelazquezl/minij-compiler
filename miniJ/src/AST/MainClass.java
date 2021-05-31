package AST;
import AST.Visitor.Visitor;

public class MainClass extends ASTNode{
  public Identifier i1,i2;
  public VarDeclList vdl;
  public StatementList sl;

  public MainClass(Identifier ai1, Identifier ai2, VarDeclList avdl, StatementList asl, int ln) {
    super(ln);
    i1=ai1; i2=ai2; vdl=avdl; sl=asl; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}

