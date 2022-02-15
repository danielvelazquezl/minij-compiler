package Semantic;

import java.util.ArrayList;
import java.util.HashMap;

import AST.Identifier;
import AST.IdentifierType;

public class ClassSymbol extends BasicSymbol {
  private ArrayList<VariableSymbol> variableList;
  private ArrayList<MethodSymbol> methodList;

  public ClassSymbol(Identifier id) {
    super(new IdentifierType(id.s, id.getLine()), id);
    variableList = new ArrayList<VariableSymbol>();
    methodList = new ArrayList<MethodSymbol>();
  }

  public ArrayList<VariableSymbol> getVariableList() {
    return variableList;
  }

  public ArrayList<MethodSymbol> getMethodList() {
    return methodList;
  }

  public void addVariable(VariableSymbol v) {
    variableList.add(v);
  }

  public void addMethod(MethodSymbol m) {
    methodList.add(m);
  }

  
}