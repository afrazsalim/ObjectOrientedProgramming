package jumpingalien.part3.Statements;

import jumpingalien.model.Buzam;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;

public class StartJumping extends Statement {

	public StartJumping(SourceLocation sourceLocation) {
		super(sourceLocation);
	}



	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("program is null at " + getSourceLocation());
		this.setProgram(program);
		program.getObject().jump();
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
