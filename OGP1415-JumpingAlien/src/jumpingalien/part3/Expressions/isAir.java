package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.SourceLocation;

public class isAir extends booleanExpression {

	public isAir(Expression expr, SourceLocation sourceLocation) {
        super(expr,sourceLocation);
	}

	@Override
	public String toString() {
		return this.evaluate(getProgram()).toString();
	}

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program cannot be null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
		   throw new NullPointerException("given argument is null at " + this.getSourceLocation());
		if((double)this.getExpression().evaluate(program).getValueOfConstant(program) == 0)
			return new BooleanType(true);
		return new BooleanType(false);
	}

	@Override
	protected Expression getValue(Program program) {
		BooleanType type = (BooleanType) this.evaluate(program);
		if((boolean)type.getValueOfConstant(program))
			return new TrueLiteral(this.getSourceLocation());
		return new FalseLiteral(this.getSourceLocation());
	}

}
