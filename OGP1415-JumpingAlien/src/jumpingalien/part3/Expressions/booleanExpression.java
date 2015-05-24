package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.programs.SourceLocation;

public abstract class booleanExpression extends singleArgumentException {

	private BooleanType value;
	private SourceLocation sourceLocation;
	private Expression expression;
	private Program program;
	
	
	
	protected booleanExpression(SourceLocation sourceLocation, BooleanType bool){
		this.value = bool;
		this.setSourceLocation(sourceLocation);
	}
	
	
	protected void setProgram(Program program){
		this.program = program;
	}
	
	protected Program getProgram(){
		return this.program;
	}
	
    protected Expression getExpression(){
    	return this.expression;
    }
	
	protected booleanExpression(Expression expr, SourceLocation sourceLocation) {
		this.expression = expr;
		this.setSourceLocation(sourceLocation);
	}

	

	private boolean isValidExpression(Expression expr) {
	//	if(expr.getValueOfConstant().getValueOfConstant() != null)
			return true;
	//	return false;
	}


	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	@Override
	public abstract String toString();
	
	
	protected  BooleanType getValue(){
		return this.value;
	}
	

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
		
	
	@Override
	protected Expression clone() {
		// TODO Auto-generated method stub
		return null;
	}


}
