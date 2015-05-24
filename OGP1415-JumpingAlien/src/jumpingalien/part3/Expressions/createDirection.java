package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DirectionType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class createDirection extends ConstantExpression {

	private Direction value;

	
	public createDirection(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDirection(value);
	}

	
	protected void setDirection(Direction value) {
		if(value == null)
			throw new NullPointerException("Direction cannot be null. ERROR at " + getSourceLocation());
       this.value = value;		
	}

	
	@Override
	public String toString() {
		if(this.evaluate(getProgram()).getValueOfConstant(this.getProgram()) == Direction.RIGHT)
		return "RIGHT";
		else if(this.evaluate(getProgram()).getValueOfConstant(this.getProgram()) == Direction.UP)
			return "UP";
		else if(this.evaluate(getProgram()).getValueOfConstant(this.getProgram()) == Direction.DOWN)
			return "DOWN";
		else 
			return "LEFT";
	}
	

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at given point "+ getSourceLocation());
		this.setProgram(program);
		return new DirectionType(value);
	}


	@Override
	protected Expression getValue(Program program) {
		return this;
	}

}
