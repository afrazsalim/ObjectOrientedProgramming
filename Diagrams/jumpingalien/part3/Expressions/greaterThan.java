package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class greaterThan extends ComparisonExpression {

	public greaterThan(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}



	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(), getRightOperand())))
			throw new IllegalArgumentException("argument must be of same type. Error at "+ this.getSourceLocation());
		Double left = (Double) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Double right = (Double)(getRightOperand().evaluate(program).getValueOfConstant(program));
		if((double) left > right)
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
