package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class Equals extends ComparisonExpression {

	public Equals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	
	@Override
	public String toString() {
		return this.getLeftOperand().evaluate(this.getProgram()).getValueOfConstant(this.getProgram()) + " == " + this.getRightOperand().evaluate(this.getProgram()).getValueOfConstant(this.getProgram());
	}



	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at "+ this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(),this.getRightOperand())))
				throw new IllegalArgumentException("arguments should be of same type at " + this.getSourceLocation());
		Type left = (Type) (getLeftOperand().evaluate(program));
		Type right = (Type)(getRightOperand().evaluate(program));
		if(left.getClass() != right.getClass())
		 return new BooleanType(false);
		if(! left.getValueOfConstant(program).equals(right.getValueOfConstant(program)))
			return new BooleanType(false);
		return new BooleanType(true);
	}


	@Override
	protected Expression getValue(Program program) {
		this.setProgram(program);
		BooleanType type = (BooleanType) this.evaluate(program);
		if(type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
		return new FalseLiteral(this.getSourceLocation());
	}

}
