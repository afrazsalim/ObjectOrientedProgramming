package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.SourceLocation;

public class NullLiteral extends ConstantExpression {

	private Expression value;

	

	public NullLiteral(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	
	public Expression getvalue(){
		return this.value;
	}
	
	
	@Override
	public String toString() {
		return "null";
	}

	

	@Override
	protected Expression clone() {
		return null;
	}



	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + getSourceLocation());
		this.setProgram(program);
		return new gameObjectType(null);
	}


	@Override
	public Expression getValue(Program program) {
		return this;
	}





}
