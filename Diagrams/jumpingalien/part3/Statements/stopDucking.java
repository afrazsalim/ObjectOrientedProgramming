package jumpingalien.part3.Statements;

import jumpingalien.model.Buzam;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;

public class stopDucking extends Statement {


	public stopDucking(SourceLocation sourceLocation) {
       super(sourceLocation);
	}
	


	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + this.getSourceLocation());
		if(program.getObject() instanceof Buzam){
			if(canStopDucking(program)){
				((Buzam) program.getObject()).setIsDucking(false);
				((Buzam)program.getObject()).endDuck();
			}
		}
		
	}



	private boolean canStopDucking(Program program) {
		int width = program.getObject().getCurrentSprite().getWidth();
		int height = program.getObject().getCurrentSprite().getHeight();
		World world = program.getObject().getWorld();
		if ((world.isImpassablePassAble(program.getObject().getPixelLeftX() + (width - 2), program.getObject().getPixelBottomY()
				+ (height- 2))))
			return false;
		if ((world.isImpassablePassAble(program.getObject().getPixelLeftX() + 1, program.getObject().getPixelBottomY() + (height - 2))))
			return false;
		return true;
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
