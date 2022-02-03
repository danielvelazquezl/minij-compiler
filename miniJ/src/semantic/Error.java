package semantic;

public class Error {

  private String message;
  private int line;

  public Error(String m, int l) {
    this.message = m;
    this.line = l;
  }

  public void print() {
    System.out.println("Error: " + this.message + ". Line: " + this.line);
  }
}
