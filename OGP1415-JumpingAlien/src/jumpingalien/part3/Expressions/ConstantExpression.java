package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public abstract class ConstantExpression extends singleArgumentException {

	protected static SourceLocation sourceLocation;
	private Expression exp;
	private Program program;
	
	protected ConstantExpression(SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
	}
	
	protected void setExpression(Expression expr){
		this.exp = expr;
	}
	
	
	protected Expression getExpression(){
		return this.exp;
	}

	protected void setSourceLocation(SourceLocation sourceLocation) {
		sourceLocation = sourceLocation;
	}
	

	
	protected static SourceLocation getSourceLocation(){
		return sourceLocation;
	}
	
	protected void setProgram(Program program){
		this.program = program;
	}
	
	
	public Program getProgram(){
      return this.program;
	}
	@Override
	public abstract String toString();
	
	@Override
	protected Expression clone() {
		return null;
	}
	
}
