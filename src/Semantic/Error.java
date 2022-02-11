package Semantic;

public class Error {
  private String message;
  private int line;

  public Error(String m, int l) {
    this.message = m;
    this.line = l;
  }

  @Override
  public String toString() {
    return "Error: " + this.message + ". Line: " + this.line;
  }
}