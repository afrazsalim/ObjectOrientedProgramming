package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class Multiplication extends BinaryExpression {
	

	public Multiplication(Expression left, Expression right, SourceLocation sourceLocation)
			throws IllegalArgumentException {
		super(left, right, sourceLocation);
	}

	@Override
	public String toString() {
		return Double.toString((double) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(), getRightOperand())))
			throw new IllegalArgumentException("given arguments are not of same type at " + this.getSourceLocation());
		Double left = (Double) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Double right = (Double)(getRightOperand().evaluate(program).getValueOfConstant(program));
		return new DoubleType(left*right);
	}

	

	@Override
	public Expression getValue(Program program) {
        DoubleType value = (DoubleType) this.evaluate(program);
		return new DoubleLiteral(value.getValueOfConstant(program),this.getSourceLocation());
	}
	

}
