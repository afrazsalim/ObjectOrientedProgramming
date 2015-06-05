package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isDead extends booleanExpression {

	public isDead(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(this.getProgram()).getValueOfConstant(this.getProgram()));
	}

	
	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
		    throw new NullPointerException("program is null at "+ this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at " + this.getSourceLocation());
		if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub)
				&& ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).getHitPoints() <= 0)
		return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Plant) 
				&& ((Plant)this.getExpression().evaluate(program).getValueOfConstant(program)).getHitPoints() <= 0)
		return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Buzam)
				&&  ((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).getHitPoints() <= 0)
		return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Slime)
				&&  ((Slime)this.getExpression().evaluate(program).getValueOfConstant(program)).getHitPoints() <= 0) 
			return new BooleanType(true);
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)
				&&  ((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).getHitPoints() <= 0) 
		return new BooleanType(true);
		else 
			return new BooleanType(false);
	}

	@Override
	protected Expression getValue(Program program) {
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}

}
