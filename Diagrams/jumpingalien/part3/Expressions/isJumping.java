package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isJumping extends booleanExpression {

	public isJumping(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("Program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at " + this.getSourceLocation());
		if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub)
				&& ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY())
		return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Buzam)
				&&  ((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY())
		return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)
				&&  ((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).isJumping())
		return new BooleanType(true);
		else 
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
