package jumpingalien.part3.Expressions;

import jumpingalien.part3.programs.SourceLocation;

public abstract class ComparisonExpression extends ComposedExpression {

	protected ComparisonExpression(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}


}
