package jumpingalien.part3.Expressions;

import org.junit.Test;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class DivisionTestCase {

	@Test
	public void test() {
		Expression e = new DoubleLiteral(20, new SourceLocation(1,1));
		Expression e2 = new DoubleLiteral(10, new SourceLocation(2,3));
		Division t = new Division(e,e2,new SourceLocation(2,2));
		System.out.println(t.getValue(new Program(null,null)));
	}

}
