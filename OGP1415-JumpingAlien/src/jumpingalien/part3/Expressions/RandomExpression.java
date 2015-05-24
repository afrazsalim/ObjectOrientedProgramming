package jumpingalien.part3.Expressions;

import java.util.Random;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class RandomExpression extends ConstantExpression {

	public RandomExpression(Expression maxValue, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpression(maxValue);
	}

	public String toString() {	
		return Double.toString((double) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	

	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given arguments are null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(getExpression())))
			throw new NullPointerException("given arguments are null at " + this.getSourceLocation());
		Random r = new Random();
		return new DoubleType(0 + ((Double)this.getExpression().evaluate(program).getValueOfConstant(program) - 0) * r.nextDouble());
	}

	@Override
	public Expression getValue(Program program) {
		this.setProgram(program);
        DoubleType t = (DoubleType) this.evaluate(program);
		return new DoubleLiteral(t.getValueOfConstant(program),this.getSourceLocation());
	}

	

}
