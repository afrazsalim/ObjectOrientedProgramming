package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.SourceLocation;

public class selfDefined extends ConstantExpression {

	private Object object;
	private static Program prog;

	public selfDefined(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	public selfDefined(Object alien, SourceLocation sourceLocation) {
		 this(sourceLocation);
          this.setObject(alien);
	}

	private void setObject(Object alien) {
        this.object = alien;		
	}

	
	@Override
	public String toString() {
		if(this.evaluate(getProgram()).getValueOfConstant(getProgram()) instanceof Mazub)
			return "Mazub";
		else if (this.evaluate(getProgram()).getValueOfConstant(getProgram()) instanceof Buzam)
			return "Buzam";
		else if(this.evaluate(getProgram()).getValueOfConstant(getProgram()) instanceof Slime)
			return "Slime";
		else if(this.evaluate(getProgram()).getValueOfConstant(getProgram()) instanceof Shark)
			return "Shark";
		else if(this.evaluate(getProgram()).getValueOfConstant(getProgram()) instanceof Plant)
			return "Plant";
		return null;
	}

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		return new gameObjectType(program.getObject());
	}

	private Object getObject() {
		return this.object;
	}

	@Override
	public Expression getValue(Program program) {
		return this;
	}
}
