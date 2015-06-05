package jumpingalien.part3.Expressions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class AdditionTestCase {
	
	
	@Test
	public void test_AndExpression() {
		Expression e = new TrueLiteral(new SourceLocation(1,1));
		Expression e2 = new TrueLiteral(new SourceLocation(2,3));
		AndExpression t = new AndExpression(e,e2,new SourceLocation(2,2));
		System.out.println(t);
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
		Addition t = new Addition(e,e2,new SourceLocation(2,2));
		System.out.println(t);
	}
	

	/*@Test
	public void test() {
		Expression e = new DoubleLiteral(10, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(20, new SourceLocation(2,3));
		Addition t = new Addition(e,e2,new SourceLocation(2,2));
		System.out.println(t);
	}
	
	/*
	@Test
    public void test_Division(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		Division t = new Division(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(new Break(new SourceLocation(1,1)),null)));
		
	}
	
	@Test
    public void test_Subtraction(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		subtraction t = new subtraction(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(new Break(new SourceLocation(1,1)),null)));
		
	}
	

	@Test
    public void test_Multipication(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		Multiplication t = new Multiplication(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(new Break(new SourceLocation(1,1)),null)));
		
	}
	
	@Test
    public void test_Random(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		RandomExpression t = new RandomExpression(e,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(new Break(new SourceLocation(1,1)),null)));
		
	}
	@Test
    public void test_sqrt(){
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new Sqrt(e, new SourceLocation(1,1));
		Program program = new Program(null,null);
		System.out.println(e2.getValue(program));
		
	}*/
}
