import AST.Program;
import AST.Visitor.TypeCheckingVisitor;
import AST.Visitor.VarCheckingVisitor;
import Parser.parser;
import Scanner.scanner;
import Semantic.SymbolTable;
import java_cup.runtime.Symbol;

public class TestSemantic {
  public static void main(String[] args) {
    try {
      scanner s = new scanner(System.in);
      parser p = new parser(s);
      Symbol root;
      root = p.parse();
      Program program = (Program) root.value;
      SymbolTable sTable = new SymbolTable();
      VarCheckingVisitor iVisitor = new VarCheckingVisitor(sTable);
      TypeCheckingVisitor tVisitor = new TypeCheckingVisitor(sTable);
      program.accept(tVisitor);
      program.accept(iVisitor);
      sTable.printErrors();
      System.out.println("Semantic analysis completed");
    } catch (Exception e) {
      System.err.println("Unexpected internal compiler error: " + e.toString());
      e.printStackTrace();
    }
  }
}