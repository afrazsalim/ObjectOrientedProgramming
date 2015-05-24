package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.SourceLocation;

public class isWater extends booleanExpression {

	public isWater(Expression expr, SourceLocation sourceLocation) {
            super(expr, sourceLocation);
	  }

	@Override
	public String toString() {
           return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(this.getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("Null pointer exception at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given argument is null at " + this.getSourceLocation());
      if((Kind)this.getExpression().evaluate(program).getValueOfConstant(program) == Kind.ANY)
		return new BooleanType(true);
	return new BooleanType(false);
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
