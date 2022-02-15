package AST;
import AST.Visitor.CodeVisitor;
import AST.Visitor.TypeVisitor;
import AST.Visitor.Visitor;

public abstract class Statement extends ASTNode {
    public Statement(int ln) {
        super(ln);
    }
    public abstract void accept(Visitor v);
    public abstract Type accept(TypeVisitor tv);
    public abstract String accept(CodeVisitor cv);
}
