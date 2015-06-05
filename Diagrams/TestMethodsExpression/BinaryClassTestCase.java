package TestMethodsExpression;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Addition;
import jumpingalien.part3.Expressions.AndExpression;
import jumpingalien.part3.Expressions.Division;
import jumpingalien.part3.Expressions.DoubleLiteral;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Expressions.Multiplication;
import jumpingalien.part3.Expressions.RandomExpression;
import jumpingalien.part3.Expressions.Sqrt;
import jumpingalien.part3.Expressions.TrueLiteral;
import jumpingalien.part3.Expressions.subtraction;
import jumpingalien.part3.programs.SourceLocation;

public class BinaryClassTestCase {
	
	
	@Test
	public void test_AndExpression() {
		Expression e = new TrueLiteral(new SourceLocation(1,1));
		Expression e2 = new TrueLiteral(new SourceLocation(2,3));
		Program program = new Program(null,null);
		AndExpression t = new AndExpression(e,e2,new SourceLocation(2,2));
		System.out.println(t.evaluate(program));
	}
	
	@Test
    public void test_sqrt(){
		Expression e = new DoubleLiteral(25, new SourceLocation(1,1));
		Expression sqrt = new Sqrt(e, new SourceLocation(1,1));
		Program program = new Program(null,null);
		assertEquals(5.0,sqrt.evaluate(program).getValueOfConstant(program));
		
	}
	
	@Test
	public void test_Add() {
		Expression e = new DoubleLiteral(10, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		Program program = new Program(null,null);
		Addition t = new Addition(e,e2,new SourceLocation(2,2));
		System.out.println(t.evaluate(program));
	}
	

	@Test
	public void test() {
		Expression e = new DoubleLiteral(10, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		Program program = new Program(null,null);
		Addition t = new Addition(e,e2,new SourceLocation(2,2));
		System.out.println(t.evaluate(program));
	}
	
	
	@Test
    public void test_Division(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		Division t = new Division(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(null,null)));
		
	}
	
	@Test
    public void test_Subtraction(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		subtraction t = new subtraction(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(null,null)));
		
	}
	

	@Test
    public void test_Multipication(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		Multiplication t = new Multiplication(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(null,null)));
		
	}
	
	@Test
    public void test_Random(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		RandomExpression t = new RandomExpression(e,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(null,null)));
		
	}
}
