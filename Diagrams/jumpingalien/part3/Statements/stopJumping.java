package jumpingalien.part3.Statements;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.programs.SourceLocation;

public class stopJumping extends Statement {




	public stopJumping(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	

	public stopJumping(WorldObjects object){
		this(getSourceLocation());
		this.setObject(object);
		
	}


	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("Given program is not valid");
		if(program.getObject() instanceof Buzam)
			if(((Buzam) program.getObject()).wasJumping())
			((Buzam) program.getObject()).endJump();
		else 
			if(program.getObject().isJumping())
		      program.getObject().stopJumping();
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
