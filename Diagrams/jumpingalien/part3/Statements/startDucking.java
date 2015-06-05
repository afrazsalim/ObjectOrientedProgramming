package jumpingalien.part3.Statements;

import jumpingalien.model.Buzam;
import jumpingalien.model.InvalidActionException;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.programs.SourceLocation;

public class startDucking extends Statement {

	public startDucking(SourceLocation sourceLocation) {
		super(sourceLocation);
	}


	@Override
	public void executeCycle(Program program) {
		if(program.getObject() instanceof Buzam){
		((Buzam) program.getObject()).setIsDucking(true);
		((Buzam)program.getObject()).startDuck();
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

