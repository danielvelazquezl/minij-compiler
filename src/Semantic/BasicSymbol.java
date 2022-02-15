package Semantic;

import AST.Type;
import AST.Identifier;

public abstract class BasicSymbol {
  private Type type;
  private Identifier id;
  private int line;

  public BasicSymbol(Type t, Identifier i) {
    this.type = t;
    this.id = i;
    this.line = t.getLine();
  }

  public Type getType() {
    return type;
  }

  public String getId() {
    return id.s;
  }

  public int getLine() {
    return line;
  }
}
