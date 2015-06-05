package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class stopRun extends Statement {

	private Expression direction;

	public stopRun(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpression(direction);
	}
	
	private void setExpression(Expression direction) {
		if(direction == null)
			throw new NullPointerException("direction is null at " + this.getSourceLocation());
        this.direction = direction;
	}

	private Expression getExpression(){
		return this.direction;
	}

	public Direction getDirection(Program program){
		return  (Direction) this.getExpression().evaluate(program).getValueOfConstant(program);
	}
	
	
	private void setDirection(Expression direction) {
       		this.direction = direction;
	}

	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		if(this.getDirection(program) == Direction.RIGHT){
	    	   program.getObject().setIsMovingRight(false);
	    	   program.getObject().setVelocityX(0.0);
		       program.getObject().setAccelerationX(0.0);
		}
	       else if(this.getDirection(program) == Direction.LEFT){
	    	   program.getObject().setIsMovingLeft(false);
	    	   program.getObject().setVelocityX(0.0);
		       program.getObject().setAccelerationX(0.0);
	       }
	       else if(program.getObject().isJumping() && this.getDirection(program) == Direction.UP || this.getDirection(program) == Direction.DOWN){
	    	   program.getObject().setIsJumping(false);
		       program.getObject().setVelocityY(0.0);
		       program.getObject().setAccelerationY(0.0);
	       }
	       else throw new IllegalStateException("can,t handle");

	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean hasActionStatements() {
		return true;
	}

}
