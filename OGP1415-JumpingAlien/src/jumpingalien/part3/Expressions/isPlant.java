package jumpingalien.part3.Expressions;

import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isPlant extends booleanExpression {

	public isPlant(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}
	

	@Override
	public Type evaluate(Program program) {
		if((boolean) this.getExpression().evaluate(getProgram()).getValueOfConstant(getProgram()))
			return new BooleanType(true);
		return new BooleanType(false);
	}

	@Override
	public Expression getValue(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
		throw new NullPointerException("given argument is null at " + this.getSourceLocation());
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}
}
