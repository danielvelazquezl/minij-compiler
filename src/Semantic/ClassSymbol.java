package Semantic;

import java.util.HashMap;

public class ClassSymbol extends BasicSymbol {
  private String name;
  private HashMap<String, VariableSymbol> variableList;

  public ClassSymbol(String name, int ln) {
    super(ln);
    this.name = name;
    this.variableList = new HashMap<String, VariableSymbol>();
  }

  public String getName() {
    return name;
  }

  public HashMap<String, VariableSymbol> getVariableList() {
    return variableList;
  }

  public boolean addVariable(VariableSymbol v) {
    if (!this.variableList.containsKey(v.getName())) {
      this.variableList.put(v.getName(), v);
      return true;
    }
    return false;
  }
}