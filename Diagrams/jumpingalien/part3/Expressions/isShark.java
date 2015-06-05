package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isShark extends booleanExpression {

	public isShark(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}
	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)
			return new BooleanType(true);
		return new BooleanType(false);
	}

	@Override
	public Expression getValue(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null ar " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at  "+ this.getSourceLocation());
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}
}
