import AST.Program;
import AST.Visitor.SemanticVisitor;
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
      SemanticVisitor sVisitor = new SemanticVisitor(sTable);
      program.accept(sVisitor);
      sTable.printErrors();
      System.out.println("Semantic analysis completed");
    } catch (Exception e) {
      System.err.println("Unexpected internal compiler error: " + e.toString());
      e.printStackTrace();
    }
  }
}