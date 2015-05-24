package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class AndExpression extends ComparisonExpression {

	public AndExpression(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.getValue(getProgram()).evaluate(getProgram()).getValueOfConstant(getProgram()));
	}



	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("Program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(this.getLeftOperand(), this.getRightOperand())))
			throw new IllegalArgumentException("Arguments should be instance of same class. ERROR at " + this.getSourceLocation());
		Boolean left = (Boolean) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Boolean right = (Boolean)(getRightOperand().evaluate(program).getValueOfConstant(program));
		if((boolean) left && right)
		return new BooleanType(true);
	 return new BooleanType(false);
	}

	@Override
	protected Expression getValue(Program program) {
		BooleanType type = (BooleanType)evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}

}
