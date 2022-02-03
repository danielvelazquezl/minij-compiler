package semantic;

abstract public class CustomSymbol {
  private int line;

  /**
   * Base symbol that only store the line
   * @param ln Line
   */
  public CustomSymbol(int ln) {
    this.line = ln;
  }

  /**
   * Return the line
   */
  public int getLine() {
    return line;
  }
}
