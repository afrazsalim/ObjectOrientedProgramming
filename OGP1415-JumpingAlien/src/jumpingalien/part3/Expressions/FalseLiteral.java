package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class FalseLiteral extends booleanExpression {
 
	
	
	public FalseLiteral(SourceLocation sourceLocation) {
        this(sourceLocation,new BooleanType(false));
	}

	protected FalseLiteral(SourceLocation sourceLocation, BooleanType bool) {
		super(sourceLocation,bool);
	}
	

	@Override
	public String toString() {
		return Boolean.toString((boolean) evaluate(this.getProgram()).getValueOfConstant(this.getProgram()));
	}
	
	
	protected boolean getvalue(){
		return this.getValue().getValueOfConstant(this.getProgram());
	}

	@Override
	protected Expression clone() {
		return new FalseLiteral(getSourceLocation());
	}


	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		return new BooleanType(false);
	}

	@Override
	protected Expression getValue(Program program) {
		this.setProgram(program);
		return this;
	}

	



}
