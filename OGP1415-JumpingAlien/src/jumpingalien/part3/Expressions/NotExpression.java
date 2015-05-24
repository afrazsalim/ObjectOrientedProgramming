package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class NotExpression extends booleanExpression {

	public NotExpression(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}
	

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given arguments are null at " + this.getSourceLocation());
		return new BooleanType((boolean) this.getExpression().evaluate(program).getValueOfConstant(program));
	}

	@Override
	public Expression getValue(Program program) {
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new FalseLiteral(this.getSourceLocation());
        else if(!(boolean)type.getValueOfConstant(program))
    		return new TrueLiteral(this.getSourceLocation());
       return new TrueLiteral(this.getSourceLocation());
	}
}
