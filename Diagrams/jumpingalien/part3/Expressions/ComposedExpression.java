package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
public abstract class ComposedExpression extends Expression {
	
	
	private Expression rightOperand;
	private Expression leftOperand;
	private SourceLocation sourceLocation;
	private Program program;

	
	protected ComposedExpression(Expression left, Expression right, SourceLocation sourceLocation) {
		  this.setLeftOperand(left);
	     this.setRightOperand(right);
	     this.setSourceLocation(sourceLocation);
	}

	protected void setSourceLocation(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;		
	}

	protected SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	protected void setLeftOperand(Expression left) {
       this.leftOperand = left;		
	}
	
	
	protected void setProgram(Program program){
		this.program = program;
	}
	
	
	protected Program getProgram(){
		return this.program;
	}
	
	protected int getNumberofOperand() {
		return 2;
	}

	protected void setRightOperand(Expression right) {
		this.rightOperand = right;
	}
	
	
	protected boolean isValidArguments(Expression leftOperand, Expression rightOperand) {
		return (leftOperand.evaluate(getProgram()).getValueOfConstant(getProgram()).getClass() == rightOperand.evaluate(getProgram()).getValueOfConstant(getProgram()).getClass());
	}


	
	@Override
	public abstract String toString();
	

	protected int getNumberOfOperand(){
		return 2;
	}

	

	protected Expression getLeftOperand(){
		return this.leftOperand;
	}
	
	protected Expression getRightOperand(){
		return this.rightOperand;
	}

	
}
