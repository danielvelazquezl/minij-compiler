import AST.Program;
import AST.Visitor.CodeGeneratorVisitor;
import AST.Visitor.TypeCheckingVisitor;
import AST.Visitor.VarCheckingVisitor;
import Parser.parser;
import Scanner.scanner;
import Semantic.SymbolTable;
import java_cup.runtime.Symbol;

public class TestCodeGeneratorLinux {

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
      CodeGeneratorVisitor cVisitor = new CodeGeneratorVisitor(sTable);
      if (sTable.errorsCount() > 0) {
        sTable.printErrors();
      } else {
        program.accept(cVisitor);
        cVisitor.generateFiles();
        System.out.println("Starting to compile and run...\n");
        RunProgram.run("java -jar jasmin.jar SimpleClass.j");
        RunProgram.run("java SimpleClass");
      }
    } catch (Exception e) {
      System.err.println("Unexpected internal compiler error: " + e.toString());
      e.printStackTrace();
    }
  }
}
