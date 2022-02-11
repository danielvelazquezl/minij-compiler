package Semantic;

public abstract class BasicSymbol {
  private int line;

  public BasicSymbol(int ln) {
    this.line = ln;
  }

  public int getLine() {
    return line;
  }
}
