package jumpingalien.part3.Statements;

import java.util.Random;

import jumpingalien.model.Buzam;
import jumpingalien.model.Program;
import jumpingalien.model.Slime;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class startRun extends Statement {

	private Object object;
	private Expression direction;

	public startRun(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDirection(direction);
	}
	
	private void setDirection(Expression direction) {
       this.direction = direction;		
	}
	

	public Direction getDirection(Program program) {
		return (Direction)direction.evaluate(program).getValueOfConstant(program);
	}
	

	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("null reference program " + getSourceLocation());
		if (this.getDirection(program) == Direction.RIGHT) {
//			program.getObject().setDirection((int) Math.cos(0));
//			program.getObject().setIsMovingLeft(false);
			program.getObject().startMoveRight();
		}
		else if (this.getDirection(program) == Direction.LEFT){
//			program.getObject().setDirection((int) Math.cos(180));
//			program.getObject().setIsMovingLeft(true);
			program.getObject().startMoveLeft();
		}
		else if (this.getDirection(program) == Direction.UP || (this.getDirection(program) == Direction.DOWN)){
			Random r = new Random();
//			program.getObject().setDirection(r.nextInt(2-0)+0);
//			program.getObject().setIsJumping(true);
			program.getObject().jump();
		}
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
