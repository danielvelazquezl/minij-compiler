package Semantic;

public class CustomError {
  private String message;
  private int line;

  public CustomError(String m, int l) {
    this.message = m;
    this.line = l;
  }

  public String getMessage() {
    return "Error: " + this.message + ". Line: " + this.line;
  }
}