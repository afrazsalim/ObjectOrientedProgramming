package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class Addition extends BinaryExpression {

	public Addition(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right,sourceLocation);	
	}
	

	@Override
	public String toString() {
		return Double.toString((double) this.getValue(getProgram()).evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	
	
	

	@Override
	public Type evaluate(Program program) {
		if (!(isValidProgram(program)))
			throw new NullPointerException("Null found at "+ this.getSourceLocation());
		this.setProgram(program);
		if(!(isValidArguments(this.getLeftOperand(),this.getRightOperand())))
					throw new IllegalArgumentException(" not a valid argument ");
		Double left = (Double) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Double right = (Double)(getRightOperand().evaluate(program).getValueOfConstant(program));
		return new DoubleType(left+right);
	}

	




	@Override
	protected Expression getValue(Program program) {
        DoubleType value = (DoubleType) this.evaluate(program);
		return new DoubleLiteral(value.getValueOfConstant(program),this.getSourceLocation());
	}
	
}
