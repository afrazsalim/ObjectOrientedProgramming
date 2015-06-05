package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class NotEquals extends ComparisonExpression {

	public NotEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left,right,sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(this.getProgram()));
	}



	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if ((this.getLeftOperand() != null && this.getRightOperand() == null) || (this.getLeftOperand() == null && this.getRightOperand() != null))
			return new BooleanType(true);
		else if (this.getLeftOperand().evaluate(program).getValueOfConstant(program) == null && this.getRightOperand() == null)
			return new BooleanType(false);
		Type left = (Type) (getLeftOperand().evaluate(program));
		Type right = (Type)(getRightOperand().evaluate(program));
		if(left.getClass() != right.getClass())
		 return new BooleanType(true);
		if(right.getValueOfConstant(program) == null)
			return new BooleanType(false);
		if(! left.getValueOfConstant(program).equals(right.getValueOfConstant(program)))
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
