package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class DoubleLiteral extends ConstantExpression {

	
	private DoubleType value;

	public DoubleLiteral(double value, SourceLocation sourceLocation) {
		 super(sourceLocation);
         this.value = new DoubleType(value);
	}


	public DoubleType getValue(){
		return this.value;
	}
	
	
	protected double getDoubleValue(){
		return this.getValue().getValueOfConstant(this.getProgram());
	}
	
	@Override
	public String toString() {
		return Double.toString(getValue().getValueOfConstant(this.getProgram()));
	}
	

	@Override
	protected Expression clone() {
		return new DoubleLiteral(getValue().getValueOfConstant(this.getProgram()),getSourceLocation());
	}



	

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at "+ this.getSourceLocation());
		this.setProgram(program);
		return this.value;
	}



	@Override
	public Expression getValue(Program program) {
		return this;
	}



}
