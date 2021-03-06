import AST.Program;
import AST.Visitor.CodeGeneratorVisitor;
import AST.Visitor.TypeCheckingVisitor;
import AST.Visitor.VarCheckingVisitor;
import Parser.parser;
import Scanner.scanner;
import Semantic.SymbolTable;
import java_cup.runtime.Symbol;

public class TestCodeGenerator {

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
      program.accept(iVisitor);
      program.accept(tVisitor);
      CodeGeneratorVisitor cVisitor = new CodeGeneratorVisitor(sTable);
      if (sTable.errorsCount() > 0) {
        sTable.printErrors();
      } else {
        program.accept(cVisitor);
        cVisitor.generateFiles();
      }
    } catch (Exception e) {
      System.err.println("Unexpected internal compiler error: " + e.toString());
      e.printStackTrace();
    }
  }
}
