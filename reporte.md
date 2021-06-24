## Reporte
Logre implementar toda la gramatica descrita en el archivo `BNF for minij.html` tanto para el archivo `minij.jflex` como tambien para el `mini.cup`.<hr>
### Casos de error
Dentro de mis pruebas encontre 1 caso en que los comentarios de bloque me generaban problemas. Mi expresion regular solo detectaba correctamente 1 comentario de bloque por archivo, si existian 2 o mas comentarios de bloque la expresion regular tomaba el primer simbolo de apertura y lo cerraba al final del ultimo comentario. Por ej
```
public class Factorial{
    public static void main(String[] a){
    	Fac f = new Fac();
    }
}
/* 
Tambien comentarios
*/
public class Fac {

		public int zero() {
			return 2-3;
		}
    /* otro comentario */
}
```
El scanner devolvia correctamente hasta la llave de cierre de la clase `Factorial` y continuaba con la llave de cierre de la clase `Fac` sin lo que hay en medio.
```
PUBLIC CLASS ID(Factorial) LCURLY PUBLIC STATIC VOID MAIN LPAREN STRING LSQUARE RSQUARE ID(a) RPAREN LCURLY ID(Fac) ID(f) BECOMES NEW ID(Fac) LPAREN RPAREN SEMICOLON SYSOUT LPAREN ID(f) DOT ID(ComputeFac) LPAREN NUMBER(10) RPAREN RPAREN SEMICOLON SYSOUT LPAREN NUMBER(10) RPAREN SEMICOLON RCURLY RCURLY RCURLY 

```
Por otro lado modifique la forma en que se agregan los `Statements` al `StatementList`. Mi implementacion hacia que los `VarDeclList` se impriman en orden inverso, y para solucionar ese problema modifique el nodo `StatementList` para que agregue siempre al comienzo de la lista.
```
public void add(Statement n) {
  list.add(0, n);
}
```
### Author: Federico Daniel Velazquez Lopez
