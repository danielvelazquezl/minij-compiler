package Semantic;

import java.util.ArrayList;
import java.util.HashMap;

import AST.Identifier;
import AST.Type;

public class MethodSymbol extends BasicSymbol {
  private HashMap<String, VariableSymbol> params;
  private HashMap<String, VariableSymbol> variables;
  
  public MethodSymbol(Type t, Identifier id) {
    super(t, id);
    params = new HashMap<String, VariableSymbol>();
    variables = new HashMap<String, VariableSymbol>();
  }

  private boolean containsVar(String key) {
    return params.containsKey(key) && variables.containsKey(key);
  }

  public boolean addParam(String key, VariableSymbol v) {
    if (!containsVar(key)) {
      params.put(key, v);
      return true;
    }
    return false;
  }

  public boolean addVar(String key, VariableSymbol v) {
    if (!containsVar(key)) {
      variables.put(key, v);
      return true;
    }
    return false;
  }

  public VariableSymbol getVar(String key) {
    return variables.get(key);
  }

  public VariableSymbol getParam(String key) {
    return params.get(key);
  }
  
}