package AST.Visitor;

import AST.*;
import Semantic.ClassSymbol;
import Semantic.SymbolTable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeGeneratorVisitor implements CodeVisitor {

  private SymbolTable symbolTable;
  private HashMap<String, String> codeList;

  public CodeGeneratorVisitor(SymbolTable st) {
    this.symbolTable = st;
    this.codeList = new HashMap<String, String>();
  }

  public void generateFiles() {
    BufferedWriter writter;
    try {
      System.out.println("Trying to create files");
      for (Map.Entry<String, String> entry : codeList.entrySet()) {
        System.out.println("filename: " + entry.getKey());
        writter = new BufferedWriter(new FileWriter(entry.getKey() + ".j"));
        writter.write(entry.getValue());
        writter.close();
      }
    } catch (IOException e) {
      System.out.println("An error occurred writting jasmin files.");
      e.printStackTrace();
    }
  }

  public String visit(Program p) {
    codeList.put(p.m.i1.s, p.m.accept(this));
    for (int i = 0; i < p.cl.size(); i++) {
      codeList.put(p.cl.get(i).getClassId(), p.cl.get(i).accept(this));
    }
    return "";
  }

  public String visit(MainClass mc) {
    StringBuilder jCode = new StringBuilder();
    ClassSymbol cs = (ClassSymbol) symbolTable.getSymbol(mc.i1.s);
    // class scope
    // symbolTable.enterScope(mc.i1.s);
    // main scope
    // symbolTable.enterScope("main");

    jCode.append(".class public " + mc.i1.s + "\n");
    jCode.append(".super java/lang/Object\n");
    jCode.append(".method public <init>()V\n");
    jCode.append(".limit locals " + cs.getVariableList().size() + "\n");
    jCode.append(".limit stack 100\n");
    jCode.append("aload_0\n");
    jCode.append("invokenonvirtual java/lang/Object/<init>()V\n");
    jCode.append("return\n");
    jCode.append(".end method\n");
    jCode.append(".method public static main([Ljava/lang/String;)V\n");
    jCode.append(".limit locals 10\n");
    jCode.append(".limit stack 10\n");

    for (int i = 0; i < mc.vdl.size(); i++) {
      jCode.append(mc.vdl.get(i).accept(this));
    }

    for (int i = 0; i < mc.sl.size(); i++) {
      jCode.append(mc.sl.get(i).accept(this));
    }
    jCode.append("return\n");
    jCode.append(".end method\n");

    // symbolTable.exitScope();
    // symbolTable.exitScope();
    return jCode.toString();
  }

  public String visit(ClassDeclSimple cs) {
    StringBuilder jCode = new StringBuilder();
    ClassSymbol ccs = (ClassSymbol) symbolTable.getSymbol(cs.i.s);
    // enter class scope
    // symbolTable.enterScope(cs.i.s);
    jCode.append(".class public " + cs.i.s + "\n");
    jCode.append(".super java/lang/Object\n");
    for (int i = 0; i < cs.vl.size(); i++) {
      jCode.append(cs.vl.get(i).accept(this));
    }
    jCode.append(".method public <init>()V\n");
    jCode.append(".limit locals " + ccs.getVariableList().size() + "\n");
    jCode.append(".limit stack 100\n");
    jCode.append("aload 0");
    jCode.append("invokenonvirtual java/lang/Object/<init>()V\n");
    jCode.append("return\n");
    jCode.append(".end method\n");

    // symbolTable.exitScope();
    return jCode.toString();
  }

  public String visit(ClassDeclExtends n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(VarDecl vd) {
    StringBuilder jCode = new StringBuilder();
    // symbolTable.getSymbol(vd.i.s).getParent ?
    if (vd.e != null) {
      jCode.append(vd.e.accept(this));
    }
    return jCode.toString();
  }

  public String visit(MethodDecl md) {
    symbolTable.enterScope(md.i.s);

    symbolTable.exitScope();
    return "";
  }

  public String visit(Formal n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(IntArrayType iat) {
    return "[I";
  }

  public String visit(IntegerType it) {
    return "I";
  }

  public String visit(IdentifierType idt) {
    return "L" + idt.s + ";";
  }

  public String visit(Block n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(If n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(While n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Print p) {
    StringBuilder jCode = new StringBuilder();
    jCode.append("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
    jCode.append(p.e.accept(this) + "\n");
    jCode.append("invokevirtual java/io/PrintStream/println(");
    // jCode.append(p.e.t.accept(this));
    jCode.append("I");
    jCode.append(")V\n");
    return jCode.toString();
  }

  public String visit(Assign n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(ArrayAssign n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(And n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Or n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(LessThan n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(GreaterThan n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Equal n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(NotEqual n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Plus p) {
    StringBuilder jCode = new StringBuilder();
    jCode.append(p.e1.accept(this) + "\n");
    jCode.append(p.e2.accept(this) + "\n");
    jCode.append("iadd\n");
    return jCode.toString();
  }

  public String visit(Minus m) {
    StringBuilder jCode = new StringBuilder();
    jCode.append(m.e1.accept(this) + "\n");
    jCode.append(m.e2.accept(this) + "\n");
    jCode.append("isub\n");
    return jCode.toString();
  }

  public String visit(Times t) {
    StringBuilder jCode = new StringBuilder();
    jCode.append(t.e1.accept(this) + "\n");
    jCode.append(t.e2.accept(this) + "\n");
    jCode.append("imul\n");
    return jCode.toString();
  }

  public String visit(Divide d) {
    StringBuilder jCode = new StringBuilder();
    jCode.append(d.e1.accept(this) + "\n");
    jCode.append(d.e2.accept(this) + "\n");
    jCode.append("idiv\n");
    return jCode.toString();
  }

  public String visit(ArrayLookup n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(ArrayLength n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Call n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(IntegerLiteral i) {
    return "ldc " + i.i + "\n";
  }

  public String visit(IdentifierExp n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(This n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(NewArray n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(NewObject n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Identifier n) {
    // TODO Auto-generated method stub
    return "";
  }

  public String visit(Display n) {
    // TODO Auto-generated method stub
    return "";
  }
}
