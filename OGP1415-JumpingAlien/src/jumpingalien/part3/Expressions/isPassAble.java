package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class isPassAble extends booleanExpression {

	public isPassAble(Expression expr, SourceLocation sourceLocation) {
         super(expr, sourceLocation);
	}

	@Override
	public String toString() {
		return this.evaluate(this.getProgram()).toString();
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("given argument is null at" + this.getSourceLocation());
		 this.setProgram(program);
		 if(!(this.isValidArgument(getExpression())))
			 throw new NullPointerException("Given argument is null at " + this.getSourceLocation());
		 int tileSize = program.getObject().getWorld().getTileSize();
		 double tile = (double) this.getExpression().evaluate(program).getValueOfConstant(program);
		 int NbX = program.getObject().getWorld().getNbTilesX();
		 int NbY = program.getObject().getWorld().getNbTilesY();
		 int x = (int) (tile+1)/NbX;
		 int y = (int)(tile+1)%NbY;
	      if(program.getObject().getWorld().isImpassablePassAble(x*tileSize, y*tileSize))
	    	  return new BooleanType(false);
		return new BooleanType(true);
	}

	@Override
	public Expression getValue(Program program) {
		BooleanType type = (BooleanType) this.evaluate(program);
		if((boolean)type.getValueOfConstant(program))
			return new TrueLiteral(this.getSourceLocation());
		return new FalseLiteral(this.getSourceLocation());
	}

	

}
