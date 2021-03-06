/*
 * JFlex specification for the lexical analyzer for a simple demo language.
 * Change this into the scanner for your implementation of MiniJ.
 */


package Scanner;

import java_cup.runtime.*;
import Parser.sym;

%%

%public
%final
%class scanner
%unicode
%cup
%line
%column

/* Code copied into the generated scanner class.  */
/* Can be referenced in scanner action code. */
%{
  // Return new symbol objects with line and column numbers in the symbol 
  // left and right fields. This abuses the original idea of having left 
  // and right be character positions, but is   // is more useful and 
  // follows an example in the JFlex documentation.
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  // Return a readable representation of symbol s (aka token)
  public String symbolToString(Symbol s) {
    String rep;
    switch (s.sym) {
      case sym.BECOMES: return "BECOMES";
      case sym.LPAREN: return "LPAREN";
      case sym.RPAREN: return "RPAREN";
      case sym.LSQUARE: return "LSQUARE";
      case sym.RSQUARE: return "RSQUARE";
      case sym.LCURLY: return "LCURLY";
      case sym.RCURLY: return "RCURLY";
      case sym.SEMICOLON: return "SEMICOLON";
      case sym.DOT: return "DOT";
      case sym.COMA: return "COMA";

      case sym.PLUS: return "PLUS";
      case sym.MINUS: return "MINUS";
      case sym.TIMES: return "TIMES";
      case sym.DIVIDE: return "DIVIDE";

      case sym.AND: return "AND";
      case sym.OR: return "OR";
      case sym.LESSTHAN: return "LESSTHAN";
      case sym.GREATERTHAN: return "GREATERTHAN";
      case sym.EQUAL: return "EQUAL";
      case sym.NOTEQUAL: return "NOTEQUAL";

      case sym.DISPLAY: return "DISPLAY";
      case sym.PUBLIC: return "PUBLIC";
      case sym.CLASS: return "CLASS";
      case sym.STATIC: return "STATIC";
      case sym.VOID: return "VOID";
      case sym.MAIN: return "MAIN";
      case sym.STRING: return "STRING";
      case sym.EXTENDS: return "EXTENDS";
      case sym.RETURN: return "RETURN";
      case sym.INT: return "INT";
      case sym.IF: return "IF";
      case sym.ELSE: return "ELSE";
      case sym.WHILE: return "WHILE";
      case sym.SYSOUT: return "SYSOUT";
      case sym.LENGTH: return "LENGTH";
      case sym.THIS: return "THIS";
      case sym.NEW: return "NEW";

      case sym.IDENTIFIER: return "ID(" + (String)s.value + ")";
      case sym.NUMBER: return "NUMBER(" + (String)s.value + ")";
      case sym.EOF: return "<EOF>";
      case sym.error: return "<ERROR>";
      default: return "<UNEXPECTED TOKEN " + s.toString() + ">";
    }
  }
%}

/* Helper definitions */
letter = [a-zA-Z]
digit = [0-9]
eol = [\r\n]
white = {eol}|[ \t]

%%

/* Token definitions */

/* reserved words */
/* (put here so that reserved words take precedence over identifiers) */
"display" { return symbol(sym.DISPLAY); }
"public" { return symbol(sym.PUBLIC); } 
"class" { return symbol(sym.CLASS); }
"static" { return symbol(sym.STATIC); }
"void" { return symbol(sym.VOID); }
"main" { return symbol(sym.MAIN); }
"String" { return symbol(sym.STRING); }
"extends" { return symbol(sym.EXTENDS); }
"return" { return symbol(sym.RETURN); }
"int" { return symbol(sym.INT); }
"if" { return symbol(sym.IF); }
"else" { return symbol(sym.ELSE); }
"while" { return symbol(sym.WHILE); }
"System.out.println" { return symbol(sym.SYSOUT); }
"length" { return symbol(sym.LENGTH); }
"this" { return symbol(sym.THIS); }
"new" { return symbol(sym.NEW); }

/* operators */
"+" { return symbol(sym.PLUS); }
"-" { return symbol(sym.MINUS); }
"*" { return symbol(sym.TIMES); }
"/" { return symbol(sym.DIVIDE); }
"=" { return symbol(sym.BECOMES); }
"&&" { return symbol(sym.AND); }
"||" { return symbol(sym.OR); }
"<" { return symbol(sym.LESSTHAN); }
">" { return symbol(sym.GREATERTHAN); }
"==" { return symbol(sym.EQUAL); }
"!=" { return symbol(sym.NOTEQUAL); }

/* ignore comments */
"//"[^\r\n]* {  }
"/*"[^]*"*/" {  }

/* delimiters */
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
";" { return symbol(sym.SEMICOLON); }
"[" { return symbol(sym.LSQUARE); }
"]" { return symbol(sym.RSQUARE); }
"{" { return symbol(sym.LCURLY); }
"}" { return symbol(sym.RCURLY); }
"." { return symbol(sym.DOT); }
"," { return symbol(sym.COMA); }

/* identifiers */
{letter} ({letter}|{digit}|_)* { return symbol(sym.IDENTIFIER, yytext()); }


/* whitespace */
{white}+ { /* ignore whitespace */ }

{digit}+ { return symbol(sym.NUMBER, yytext()); }

/* lexical errors (put last so other matches take precedence) */
. { System.err.println(
	"\nunexpected character in input: '" + yytext() + "' at line " +
	(yyline+1) + " column " + (yycolumn+1));
  }
