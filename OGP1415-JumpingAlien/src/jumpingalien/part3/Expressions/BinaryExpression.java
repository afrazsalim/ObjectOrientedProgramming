package jumpingalien.part3.Expressions;

import jumpingalien.part3.programs.SourceLocation;

public abstract class BinaryExpression extends ComposedExpression {

	
	
	protected BinaryExpression(Expression left, Expression right, SourceLocation sourceLocation)throws IllegalArgumentException {
		super(left,right,sourceLocation);
	}




}
