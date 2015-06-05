package TestMethodsExpression;

import org.junit.Test;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.AndExpression;
import jumpingalien.part3.Expressions.DoubleLiteral;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Expressions.FalseLiteral;
import jumpingalien.part3.Expressions.NotEquals;
import jumpingalien.part3.Expressions.NotExpression;
import jumpingalien.part3.Expressions.TrueLiteral;
import jumpingalien.part3.Expressions.lessThan;
import jumpingalien.part3.Expressions.lessThanOrEqual;
import jumpingalien.part3.Expressions.orExpression;
import jumpingalien.part3.programs.SourceLocation;

public class BooleanTestCase {

	@Test
	public void test() {
		Expression e = new TrueLiteral(new SourceLocation(1,1));
		Expression e2 = new TrueLiteral(new SourceLocation(1,1));
		AndExpression t = new AndExpression(e,e,new SourceLocation(2,2));
		System.out.println(t.evaluate(new Program(null,null)));
	}
	
	
	@Test
	public void test_Equals() {
		Expression e = new DoubleLiteral(10, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		jumpingalien.part3.Expressions.Equals t = new jumpingalien.part3.Expressions.Equals(e,e2,new SourceLocation(2,2));
		System.out.println(t.evaluate(new Program(null,null)));
	}


	@Test
	public void test_And() {
		Expression e = new FalseLiteral(new SourceLocation(1,1));
		Expression e2 = new FalseLiteral(new SourceLocation(2,3));
		AndExpression t = new AndExpression(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}

	
	@Test
	public void test_or() {
		Expression e = new TrueLiteral(new SourceLocation(1,1));
		Expression e2 = new FalseLiteral(new SourceLocation(2,3));
		orExpression t = new orExpression(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}
	
	@Test
	public void test_NotExp() {
		Expression e2 = new FalseLiteral(new SourceLocation(2,3));
		NotExpression t = new NotExpression(e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}
	
	@Test
	public void tesLessThan() {
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		lessThan t = new lessThan(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}
	

	@Test
	public void tesLessThanEqual() {
		Expression e = new DoubleLiteral(30, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		lessThanOrEqual t = new lessThanOrEqual(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}

	@Test
	public void greaterThan() {
		Expression e = new DoubleLiteral(30, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		jumpingalien.part3.Expressions.greaterThan t = new jumpingalien.part3.Expressions.greaterThan(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}
	

	/*
	@Test
	public void NotEquals_Object() {
		IFacadePart2 facade = new Facade();
		Mazub alien = facade.createMazub(0, 499, spriteArrayForSize(3, 3));
        gameObjectType type = new gameObjectType(alien);
		Expression e = new selfDefined(type,new SourceLocation(1,1));
		Expression e2 = new selfDefined(type,new SourceLocation(2,3));
		NotEquals t = new NotEquals(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.getValue(program));
	}
	¨*/
	
	@Test
	public void NotEquals_LiteralDouble() {
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		NotEquals t = new NotEquals(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}
	
	
	
	@Test
	public void Equals() {
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		jumpingalien.part3.Expressions.Equals t = new jumpingalien.part3.Expressions.Equals(e,e2,new SourceLocation(2,2));
		Program program = new Program(null,null);
		System.out.println(t.evaluate(program));
	}

}
