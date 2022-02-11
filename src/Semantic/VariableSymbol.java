package Semantic;

enum Type {
  INT, ARRAY, CLASS
}

public class VariableSymbol extends BasicSymbol {
  private Type type;
  private String name;

  public VariableSymbol(Type t, String n, int ln) {
    super(ln);
    this.type = t;
    this.name = n;
  }

  public Type getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
