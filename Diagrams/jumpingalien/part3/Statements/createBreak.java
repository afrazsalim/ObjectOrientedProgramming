package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class createBreak extends Statement {

	public createBreak(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void executeCycle(Program program) {
		 if(!(this.isValidProgram(program)))
			 throw new NullPointerException("program is null at " + this.getSourceLocation());
         program.breakProgram();
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}
	
	public void checkWellFormed(Program program) {
		program.setHasBreak(true);
	}

	@Override
	public boolean hasActionStatements() {
		return false;
	}

}
