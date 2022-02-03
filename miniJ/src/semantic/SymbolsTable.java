package semantic;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolsTable {

  private HashMap<String, ClassSymbol> classList;
  private ArrayList<Error> errorList;

  public SymbolsTable() {
    this.classList = new HashMap<String, ClassSymbol>();
    this.errorList = new ArrayList<Error>();
  }

  public void logError(Error e) {
    this.errorList.add(e);
  }

  public void printErrors() {
    for (Error error : this.errorList) {
      error.print();
    }
  }

  /**
   * Add a class symbol to the list
   * @param c
   * @return 
   */
  public boolean addClass(ClassSymbol c) {
    if (!this.classList.containsKey(c.getName())) {
      this.classList.put(c.getName(), c);
      return true;
    }
    return false;
  }

  /**
   * Get one class object
   * @param key
   * @return ClassObject
   */
  public ClassSymbol getClass(String key) {
    return this.classList.get(key);
  }

  /**
   * Return the list of classes
   */
  public HashMap<String, ClassSymbol> getClassList() {
    return classList;
  }

  public void printClasses() {
    for (String n : this.classList.keySet()) {
      System.out
          .println("Classname: " + this.classList.get(n).getName() + " in line: " + this.classList.get(n).getLine());
    }
  }
}
