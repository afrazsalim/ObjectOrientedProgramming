package jumpingalien.model;

import java.util.Map;

import jumpingalien.part3.Statements.Statement;
import jumpingalien.part3.Type.Type;

public class Program {

	private Map<String, Type> globalVariables;
	private Statement statement;
	private WorldObjects object;

	public boolean isHasBreak() {
		return hasBreak;
	}


	public void setHasBreak(boolean hasBreak) {
		this.hasBreak = hasBreak;
	}


	public boolean isHasActionStatement() {
		return hasActionStatement;
	}


	public void setHasActionStatement(boolean hasActionStatement) {
		this.hasActionStatement = hasActionStatement;
	}


	private boolean hasBreak = false;
	private boolean hasActionStatement = false;

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
         this.statement = mainStatement;
         this.globalVariables = globalVariables;
	}
	
	
	public void addVariablesToMap(String name, Type type){
		this.globalVariables.put(name, type);
	}


	public void setObject(WorldObjects buzam) {
       this.object = buzam;		
	}
	
	public void executeCycle(){
		if (this.getStatement().requiresCycle(this)) {
			this.getStatement().executeCycle(this);
			this.getStatement().incrementCycle();
		}
	}


	public boolean isWellFormed() {
		this.getStatement().checkWellFormed(this);
		return !this.isHasBreak();
	}
	
	public Type getVariables(String name, Type type) {
		return this.globalVariables.get(name);
	}


	public WorldObjects getObject() {
       return this.object;
	}


	private boolean needBreak;
    private boolean skipStatement;
	
	public boolean needBreakProgram(){
		return this.needBreak;
	}
	
	
	private Statement getStatement() {
		return this.statement;
	}




	public void skipStatement(boolean skip) {
        this.skipStatement = skip;		
	}


	public boolean getSkipStatement() {	
		return this.skipStatement;
	}



	public void breakProgram() {
         this.breakProgram(true);	
	}

	public void handledBreakProgram() {
        this.breakProgram(false);	
	}

	private void breakProgram(boolean b) {
       this.needBreak = b;		
	}
}
