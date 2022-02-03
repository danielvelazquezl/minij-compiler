package semantic;

public class VariableSymbol extends CustomSymbol {

  private String type;
  private String name;
  // private Object value?

  /**
   * Variable Object
   * @param t Type
   * @param n Name
   * @param ln Line number
   */
  public VariableSymbol(String t, String n, int ln) {
    super(ln);
    this.type = t;
    this.name = n;
  }

  /**
   * Return the type of the variable
   */
  public String getType() {
    return type;
  }

  /**
   * Return the name of the variable
   */
  public String getName() {
    return name;
  }

  /**
   * Set the type of the variable
   * @param type
   */
  public void setType(String type) {
    this.type = type;
  }
  
}
