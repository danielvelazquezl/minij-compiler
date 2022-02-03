package semantic;

import java.util.HashSet;

public class MethodSymbol extends CustomSymbol {
  private String name;
  private String returnType;
  private HashSet<VariableSymbol> paramList;
  private HashSet<VariableSymbol> variableList;

  /**
   * Method Object
   * 
   * @param n  Name
   * @param rt Return type
   * @param ln Line number
   */
  public MethodSymbol(String n, String rt, int ln) {
    super(ln);
    this.name = n;
    this.returnType = rt;
    this.paramList = new HashSet<VariableSymbol>();
    this.variableList = new HashSet<VariableSymbol>();
  }

  /**
   * Add new unique variable
   * 
   * @param v Variable Object
   * @return True if the variable does not exist in the list otherwise, false
   */
  public boolean addVariable(VariableSymbol v) {
    return this.variableList.add(v);
  }

  /**
   * Add new unique param
   * 
   * @param v Variable object
   * @return True if the variable does not exist in the list otherwise, false
   */
  public boolean addParam(VariableSymbol v) {
    return this.paramList.add(v);
  }

  public HashSet<VariableSymbol> getVariableList() {
    return variableList;
  }

  /**
   * Return the name of the method
   */
  public String getName() {
    return name;
  }

  /**
   * Return the type of the method
   */
  public String getReturnType() {
    return returnType;
  }
}
