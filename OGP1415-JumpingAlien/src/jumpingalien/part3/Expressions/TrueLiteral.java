package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class TrueLiteral extends booleanExpression {

	
	
	public TrueLiteral(SourceLocation sourceLocation) {
         this(sourceLocation,new BooleanType(true));
	}
	
	
	public TrueLiteral(SourceLocation sourceLocation, BooleanType bool ) {
		super(sourceLocation,bool);
	}
	
	@Override
	public String toString() {
		return Boolean.toString((boolean) evaluate(this.getProgram()).getValueOfConstant(this.getProgram()));
	}
	
	@Override
	protected Expression clone() {
		return new TrueLiteral(getSourceLocation(),new BooleanType((boolean) evaluate(this.getProgram()).getValueOfConstant(this.getProgram())));
	}



	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given arguments are null at " + this.getSourceLocation());
		this.setProgram(program);
		return new BooleanType(true);
	}


	@Override
	public Expression getValue(Program program) {
		return this;
	}
	
	


}
