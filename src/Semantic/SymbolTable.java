package Semantic;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
  private HashMap<String, ClassSymbol> classList;
  private ArrayList<Error> errorList;

  public SymbolTable() {
    this.classList = new HashMap<String,ClassSymbol>();
    this.errorList = new ArrayList<Error>();
  }

  public void logError(Error e) {
    this.errorList.add(e);
  }

  public void printErrors() {
    for (Error error : this.errorList) {
      System.out.println(error);
    }
  }

  public boolean addClass(ClassSymbol c) {
    if (!this.classList.containsKey(c.getName())) {
      this.classList.put(c.getName(), c);
      return true;
    }
    return false;
  }
}