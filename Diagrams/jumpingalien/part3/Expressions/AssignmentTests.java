package jumpingalien.part3.Expressions;

import java.util.HashMap;

import org.junit.Test;

import jumpingalien.model.Program;
import jumpingalien.part3.Statements.assignment;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class AssignmentTests {

	@Test
	public void test() {
     Expression e = new DoubleLiteral(10,new SourceLocation(1,1));
   //  Expression e2 = new DoubleLiteral(20,new SourceLocation(1,1));
     Program p = new Program(null, new HashMap<String, Type>());
     Type t = new DoubleType((double) e.evaluate(p).getValueOfConstant(p));
     assignment a = new assignment("var",t,e,new SourceLocation(1,1));
     a.executeCycle(p);
     Expression k = new ReadVariable("var",t,new SourceLocation(2,2));
     System.out.println(k.evaluate(p));
	}
	
	

}
