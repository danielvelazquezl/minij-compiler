package AST.Visitor;

import AST.*;
import semantic.ClassSymbol;
import semantic.Error;
import semantic.MethodSymbol;
import semantic.SymbolsTable;
import semantic.VariableSymbol;

public class SymbolsVisitor implements Visitor {

	private SymbolsTable symbolTable;

	public SymbolsVisitor(SymbolsTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public void visit(Display n) {
	}

	public void visit(Program program) {
		program.m.accept(this);
		for (int i = 0; i < program.cl.size(); i++) {
			program.cl.get(i).accept(this);
		}
	}

	public void visit(MainClass mainClass) {
		// add mainclass to the symbol table
		if (!this.symbolTable.addClass(new ClassSymbol(mainClass.i1.s, mainClass.getLineNumber()))) {
			this.symbolTable.logError(new Error("Duplicated class declaration", mainClass.getLineNumber()));
		}
		for (int i = 0; i < mainClass.vdl.size(); i++) {
			mainClass.vdl.get(i).accept(this, this.symbolTable.getClass(mainClass.i1.s));
		}
	}

	public void visit(ClassDeclSimple classSimple) {
		// add main class to the ClassList
		if (!this.symbolTable.addClass(new ClassSymbol(classSimple.i.s, classSimple.getLineNumber()))) {
			this.symbolTable.logError(new Error("Duplicated class declaration", classSimple.getLineNumber()));
		}
		for (int i = 0; i < classSimple.vl.size(); i++) {
			classSimple.vl.get(i).accept(this, this.symbolTable.getClass(classSimple.i.s));
		}
		for (int i = 0; i < classSimple.ml.size(); i++) {
			classSimple.ml.get(i).accept(this, this.symbolTable.getClass(classSimple.i.s));
		}
	}

	public void visit(ClassDeclExtends classExtends) {
		// class extended must be above
		if (this.symbolTable.getClassList().containsKey(classExtends.j.s)) {
			this.symbolTable.addClass(new ClassSymbol(classExtends.i.s, classExtends.getLineNumber()));
		} else {
			this.symbolTable.logError(new Error("Extended class does not exist", classExtends.getLineNumber()));
			// remove it from the tree?
		}

		for (int i = 0; i < classExtends.vl.size(); i++) {
			classExtends.vl.get(i).accept(this, this.symbolTable.getClass(classExtends.i.s));
		}
		for (int i = 0; i < classExtends.ml.size(); i++) {
			classExtends.ml.get(i).accept(this, this.symbolTable.getClass(classExtends.i.s));
		}
	}

	// if comes from class
	public void visit(VarDecl varDecl, ClassSymbol c) {
		if (c.getVariableList().containsKey(varDecl.i.s)) {
			c.addVariable(new VariableSymbol(varDecl.t.accept(this), varDecl.i.s, varDecl.getLineNumber()));
		} else {
			this.symbolTable.logError(new Error("Duplicated variable declaration", varDecl.getLineNumber()));
		}
	}

	// if comes from method
	public void visit(VarDecl varDecl, MethodSymbol m) {
		if (m.getVariableList().containsKey(varDecl.i.s)) {
			m.addVariable(new VariableSymbol(varDecl.t.accept(this), varDecl.i.s, varDecl.getLineNumber()));
		} else {
			this.symbolTable.logError(new Error("Duplicated variable declaration", varDecl.getLineNumber()));
		}
	}

	public void visit(MethodDecl method, ClassSymbol c) {
		if (c.getMethodList().containsKey(method.i.s)) {
			c.addMethod(new MethodSymbol(method.i.s, method.t.accept(this), method.getLineNumber()));
			for (int i = 0; i < method.vl.size(); i++) {
				method.vl.get(i).accept(this, c.getMethod(method.i.s));
			}
		} else {
			this.symbolTable.logError(new Error("Duplicated method definition", method.getLineNumber()));
		}
	}

	public void visit(Formal n) {
		// TODO Auto-generated method stub

	}

	public String visit(IntArrayType n) {
		return "IntArrayType";
	}

	public String visit(IntegerType n) {
		return "IntLiteralType";
	}

	public String visit(IdentifierType n) {
		return "IdentifierType";
	}

	public void visit(Block n) {
		// TODO Auto-generated method stub

	}

	public void visit(If n) {
		// TODO Auto-generated method stub

	}

	public void visit(While n) {
		// TODO Auto-generated method stub

	}

	public void visit(Print n) {
		// TODO Auto-generated method stub

	}

	public void visit(Assign n) {
		// TODO Auto-generated method stub

	}

	public void visit(ArrayAssign n) {
		// TODO Auto-generated method stub

	}

	public void visit(And n) {
		// TODO Auto-generated method stub

	}

	public void visit(Or n) {
		// TODO Auto-generated method stub

	}

	public void visit(LessThan n) {
		// TODO Auto-generated method stub

	}

	public void visit(GreaterThan n) {
		// TODO Auto-generated method stub

	}

	public void visit(Equal n) {
		// TODO Auto-generated method stub

	}

	public void visit(NotEqual n) {
		// TODO Auto-generated method stub

	}

	public void visit(Plus n) {
		// TODO Auto-generated method stub

	}

	public void visit(Minus n) {
		// TODO Auto-generated method stub

	}

	public void visit(Times n) {
		// TODO Auto-generated method stub

	}

	public void visit(Divide n) {
		// TODO Auto-generated method stub

	}

	public void visit(ArrayLookup n) {
		// TODO Auto-generated method stub

	}

	public void visit(ArrayLength n) {
		// TODO Auto-generated method stub

	}

	public void visit(Call n) {
		// TODO Auto-generated method stub

	}

	public void visit(IntegerLiteral n) {

	}

	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub

	}

	public void visit(This n) {
		// TODO Auto-generated method stub

	}

	public void visit(NewArray n) {
		// TODO Auto-generated method stub

	}

	public void visit(NewObject n) {
		// TODO Auto-generated method stub

	}

	public void visit(Identifier n) {
		// TODO Auto-generated method stub

	}

}