package Semantic;

import AST.Identifier;
import AST.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

  private class Tree {

    private HashMap<String, BasicSymbol> symbolList;
    private HashMap<String, Tree> children;
    private String parentId;
    private Tree parentTree;

    public Tree() {
      this.symbolList = new HashMap<String, BasicSymbol>();
      this.children = new HashMap<String, Tree>();
    }

    public boolean isDefined(String id) {
      return symbolList.containsKey(id);
    }

    public void printSymbolList() {
      for (Map.Entry<String, BasicSymbol> s : symbolList.entrySet()) {
        System.out.println("Symbol: " + s.getKey());
      }
    }

    public Type getType(String id) {
      return symbolList.get(id).getType();
    }

    public void addSymbol(BasicSymbol s) {
      symbolList.put(s.getId(), s);
    }

    public BasicSymbol getSymbol(String id) {
      return symbolList.get(id);
    }

    public void addChild(String i, Tree t) {
      children.put(i, t);
      t.parentId = i;
      t.parentTree = this;
    }

    public Tree getChild(String id) {
      return children.get(id);
    }

    public String getParentId() {
      return parentId;
    }

    public Tree getParentTree() {
      return parentTree;
    }
  }

  private ArrayList<CustomError> errorList;
  private String rootId = "root";
  private Tree rootTree = new Tree();

  private String currentId = rootId;
  private Tree currentTree = rootTree;

  public SymbolTable() {
    this.errorList = new ArrayList<CustomError>();
  }

  public void logError(String m, int l) {
    errorList.add(new CustomError(m, l));
  }

  public void printErrors() {
    for (CustomError error : errorList) {
      System.out.println(error.getMessage());
    }
  }

  public int errorsCount() {
    return errorList.size();
  }

  public String getScope() {
    return currentId;
  }

  public void enterScope(String id) {
    Tree temp = currentTree.getChild(id);
    if (temp == null) {
      // System.out.println("scope: NULL. Entered: " + id);
      Tree newT = new Tree();
      currentTree.addChild(id, newT);
      currentId = id;
      currentTree = newT;
    } else {
      // System.out.println("scope: NOT NULL. Entered: " + id);
      currentTree = temp;
      currentId = id;
    }
  }

  public void exitScope() {
    // System.out.println("exiting from: " + currentTree.getParentId());
    currentId = currentTree.getParentId();
    currentTree = currentTree.getParentTree();
  }

  public boolean isDefined(String id) {
    Tree actual = currentTree;
    while (actual != null) {
      if (actual.isDefined(id)) return true;
      actual = actual.getParentTree();
    }
    return false;
  }

  public void addSymbol(BasicSymbol s) {
    currentTree.addSymbol(s);
  }

  public Type getType(String id) {
    Tree actual = currentTree;
    while (actual != null) {
      if (actual.isDefined(id)) return actual.getType(id);
      actual = actual.getParentTree();
    }
    return null;
  }

  public BasicSymbol getSymbol(String id) {
    Tree actual = currentTree;
    while (actual != null) {
      if (actual.isDefined(id)) return actual.getSymbol(id);
      actual = actual.getParentTree();
    }
    return null;
  }
}
