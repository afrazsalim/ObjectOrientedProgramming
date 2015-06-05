package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class orExpression extends ComparisonExpression {

	public orExpression(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public String toString() {
		return this.getLeftOperand().toString() + " || " + this.getRightOperand().toString();
	}



	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program  is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(), getRightOperand())))
			throw new NullPointerException("given arguments are not of same type at " + this.getSourceLocation());
		Boolean left = (Boolean) getLeftOperand().evaluate(program).getValueOfConstant(program);
		Boolean right = (Boolean)(getRightOperand().evaluate(program).getValueOfConstant(program));
		if((boolean) left || right)
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
