package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class getheight extends ConstantExpression {

	public getheight(Expression expr, SourceLocation sourceLocation) {
		super(sourceLocation);
		setExpression(expr);
		
	}

	@Override
	public String toString() {
		DoubleType type = (DoubleType) this.evaluate(getProgram());
		return Double.toString(type.getValueOfConstant(getProgram()));
	}
	

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program cannot be null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(this.getExpression())))
			throw new NullPointerException("expression is null at" + this.getSourceLocation());
		if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub)
        	return new DoubleType((double) (((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).getCurrentSprite().getHeight()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Plant)
        	return new DoubleType((double) (((Plant)this.getExpression().evaluate(program).getValueOfConstant(program)).getCurrentSprite().getHeight()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Buzam)
        	return new DoubleType((double) (((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).getCurrentSprite().getHeight()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Slime)
        	return new DoubleType((double) (((Slime)this.getExpression().evaluate(program).getValueOfConstant(program)).getCurrentSprite().getHeight()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)
        	return new DoubleType((double) (((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).getCurrentSprite().getHeight()));
        else if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof World)
        	return new DoubleType((double) (((World)this.getExpression().evaluate(program).getValueOfConstant(program)).getVisibleWindowHeight()));
        else throw new IndexOutOfBoundsException("Given expression is not instance of any of these objects");
	}



	@Override
	protected Expression getValue(Program program) {
		this.setProgram(program);
       DoubleType type = (DoubleType) this.evaluate(program);
		return new DoubleLiteral(type.getValueOfConstant(program), this.getSourceLocation());
	}

	
}
