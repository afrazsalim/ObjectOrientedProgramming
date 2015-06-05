package jumpingalien.part3.Expressions;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isMazub extends booleanExpression {

	public isMazub(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at " + this.getSourceLocation());
		return new BooleanType(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub);
	}

	
	@Override
	public Expression getValue(Program program) {
		this.setProgram(program);
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}

}
