package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.programs.SourceLocation;

public class Skip extends Statement {

	public Skip(SourceLocation sourceLocation) {
		super(sourceLocation);
	}



	@Override
	public void executeCycle(Program program) {
		program.skipStatement(true);
		
	}



	@Override
	public boolean isWellFormed() {
		return true;
	}



	@Override
	public boolean hasActionStatements() {
		return false;
	}


}
