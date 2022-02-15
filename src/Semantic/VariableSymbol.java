package Semantic;

import AST.Identifier;
import AST.Type;

public class VariableSymbol extends BasicSymbol {

  public VariableSymbol(Type t, Identifier id) {
    super(t, id);
  }
}
