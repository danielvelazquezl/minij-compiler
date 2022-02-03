import AST.Program;
import AST.Visitor.SymbolsVisitor;
import Parser.parser;
import Scanner.scanner;
import java_cup.runtime.Symbol;
import semantic.SymbolsTable;

public class TestSemantic {
  public static void main(String[] args) {
    try {
      scanner s = new scanner(System.in);
      parser p = new parser(s);
      Symbol root;
      root = p.parse();
      Program program = (Program) root.value;
      SymbolsTable sTable = new SymbolsTable();
      SymbolsVisitor sVisitor = new SymbolsVisitor(sTable);
      program.accept(sVisitor);
      sTable.printClasses();
      sTable.printErrors();
    } catch (Exception e) {
      System.err.println("Unexpected internal compiler error: " + e.toString());
      e.printStackTrace();
    }
  }
}
