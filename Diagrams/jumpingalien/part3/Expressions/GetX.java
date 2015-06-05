package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class GetX extends ConstantExpression {

	public GetX(Expression expr, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpression(expr);
	}

	
	@Override
	public String toString() {
		return Double.toString((double) this.evaluate(getProgram()).getValueOfConstant(this.getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at " + this.getSourceLocation());
        if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub)
         return new DoubleType((double) (((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).getPixelLeftX()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Plant)
        	return new DoubleType((double) (((Plant)this.getExpression().evaluate(program).getValueOfConstant(program)).getPixelLeftX()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Buzam)
        	return new DoubleType((double) (((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).getPixelLeftX()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Slime)
        	return new DoubleType((double) (((Slime)this.getExpression().evaluate(program).getValueOfConstant(program)).getPixelLeftX()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)
        	return new DoubleType((double) (((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).getPixelLeftX()));
        else 
        	throw new IndexOutOfBoundsException("don,t know which object,s co-ordinate should be returned " + this.getSourceLocation());
	}


	@Override
	protected Expression getValue(Program program) {
		return new DoubleLiteral((double) this.evaluate(program).getValueOfConstant(program),this.getSourceLocation());
	}

}
