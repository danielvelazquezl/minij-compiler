package semantic;

import java.util.HashMap;

public class ClassSymbol extends CustomSymbol {
  private String name;
  private HashMap<String, MethodSymbol> methodList;
  private HashMap<String, VariableSymbol> variableList;

  /**
   * Class object
   * 
   * @param name Name
   * @param ln   Line
   */
  public ClassSymbol(String name, int ln) {
    super(ln);
    this.name = name;
    this.methodList = new HashMap<String, MethodSymbol>();
  }

  /**
   * Add new unique method
   * 
   * @param m MethodSymbol
   * @return True if the variable does not exist in the list otherwise, false
   */
  public boolean addMethod(MethodSymbol m) {
    if (!this.methodList.containsKey(m.getName())) {
      this.methodList.put(m.getName(), m);
      return true;
    }
    return false;
  }

  /**
   * Add new unique variable
   * 
   * @param v VariableSymbol
   * @return True if the variable does not exist in the list otherwise, false
   */
  public boolean addVariable(VariableSymbol v) {
    if (!this.variableList.containsKey(v.getName())) {
      this.variableList.put(v.getName(), v);
      return true;
    }
    return false;
  }

  public HashMap<String, MethodSymbol> getMethodList() {
    return methodList;
  }

  public HashMap<String, VariableSymbol> getVariableList() {
    return variableList;
  }

  public VariableSymbol getVariable(String key) {
    return this.variableList.get(key);
  }

  public MethodSymbol getMethod(String key) {
    return this.methodList.get(key);
  }

  /**
   * Return the name of the class
   */
  public String getName() {
    return name;
  }
}
