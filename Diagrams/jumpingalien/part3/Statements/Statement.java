package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Statement {

	private static SourceLocation sourceLocation;
	private WorldObjects object;
	private Program program;

	public abstract void executeCycle(Program program);
	
	public Statement(SourceLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
	}
	
	protected boolean isValidProgram(Program program){
		return (program != null);
	}
	
	
	public WorldObjects getObject(){
		return  this.object;
	}
	
	public void setObject(WorldObjects object2){
		this.object = object2;
	}
	
	protected static SourceLocation getSourceLocation(){
		return sourceLocation;
	}
	
	protected Program getProgram(){
		return this.program;
	}
	
	protected void setProgram(Program program){
		this.program = program;
	}
	
	private int currentCycle = 0;

	public int getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(int currentCycle) {
		this.currentCycle = currentCycle;
	}

	public boolean requiresCycle(Program program) {
		return (this.getCurrentCycle() == 0);
	}
	
	public void incrementCycle() {
		this.setCurrentCycle(this.getCurrentCycle() + 1);
	}
	
	public void reinitialize() {
		this.setCurrentCycle(0);
	}

	public void checkWellFormed(Program program) {
	}
	
	public abstract boolean isWellFormed();
	
    public abstract boolean hasActionStatements();
}
