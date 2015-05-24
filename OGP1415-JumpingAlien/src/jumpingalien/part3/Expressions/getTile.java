package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class getTile extends BinaryExpression {



	public getTile(Expression left, Expression right, SourceLocation sourceLocation)
			throws IllegalArgumentException {
		super(left,right,sourceLocation);
	}

	@Override
	public String toString() {
		return Double.toString((double) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArguments(getLeftOperand(), getRightOperand())))
			throw new IllegalArgumentException("Argument should be of same type. ERROR at "+ this.getSourceLocation());
		int NumberOfTilesOnXco = program.getObject().getWorld().getNbTilesX();
		int NumberOfTilesOnYco = program.getObject().getWorld().getNbTilesY();
		int [][] array = new int[NumberOfTilesOnXco][NumberOfTilesOnYco];
		for(int i = 0; i < NumberOfTilesOnXco;i++){
			for(int j = 0; j < NumberOfTilesOnYco;j++){
				array[i][j] = i*NumberOfTilesOnXco+j;
			}
		}
	    int tileSize = program.getObject().getWorld().getTileSize();
		DoubleType type  = (DoubleType) this.getLeftOperand().evaluate(program);
		DoubleType right = (DoubleType) this.getRightOperand().evaluate(program);
		
	    int tileX =  (int) ((type.getValueOfConstant(program))/tileSize);
	    int tileY = (int) (right.getValueOfConstant(getProgram())/tileSize);
	    int tile = tileX*NumberOfTilesOnXco+tileY;
 		return new DoubleType((double)tile);
	}

	
	@Override
	protected Expression getValue(Program program) {
		return new DoubleLiteral((double) this.evaluate(program).getValueOfConstant(program),this.getSourceLocation());
	}
	
}
