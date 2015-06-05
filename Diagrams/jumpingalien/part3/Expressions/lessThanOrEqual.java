package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class lessThanOrEqual extends ComparisonExpression {

	public lessThanOrEqual(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean)this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}



	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(), getRightOperand())))
			throw new IllegalArgumentException("given argument are not of same type at "+ this.getSourceLocation());
		Double left = (Double) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Double right = (Double)(getRightOperand().evaluate(program).getValueOfConstant(program));
		if((double) left <= right)
		return new BooleanType(true);
	 return new BooleanType(false);
	}

	@Override
	public Expression getValue(Program program) {
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}


}
