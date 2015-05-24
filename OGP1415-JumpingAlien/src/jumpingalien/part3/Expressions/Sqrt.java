package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class Sqrt extends ConstantExpression {

	
	
	public Sqrt(Expression expr, SourceLocation sourceLocation) {
        super(sourceLocation);
        setExpression(expr);
	}

	@Override
	public String toString() {
		DoubleType t = (DoubleType) this.evaluate(this.getProgram());
		return Double.toString(t.getValueOfConstant(this.getProgram()));
	}

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given arguments are null at " + this.getSourceLocation());
		this.setProgram(program);
		return new DoubleType(Math.sqrt(((double) this.getExpression().evaluate(program).getValueOfConstant(program))));
	}

	@Override
	protected Expression clone() {
		return new Sqrt(getExpression(),getSourceLocation());
	}


	@Override
	public Expression getValue(Program program) {
		this.setProgram(program);
		DoubleType t = (DoubleType) this.evaluate(program);
		return new DoubleLiteral(t.getValueOfConstant(program),getSourceLocation());
	}

}
