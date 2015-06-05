package jumpingalien.part3.Statements;

import java.util.List;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class Sequence extends Statement {

	private List<Statement> statements;


	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setStatement(statements);
	}
	
	private void setStatement(List<Statement> statements) {
        this.statements = statements;		
	}


	public List<Statement> getStatements() {
		return this.statements;
	}

	public void executeCycle(Program program) {
		if (!this.getStatements().get(this.getLine()).requiresCycle(program))
			this.setLine(this.getLine()+1);
		this.getStatements().get(this.getLine()).executeCycle(program);
		this.getStatements().get(this.getLine()).incrementCycle();
	}
	
	@Override
	public void reinitialize() {
		super.reinitialize();
		this.setLine(0);
		this.getStatements().forEach((state) -> state.reinitialize());
	}
	
	public boolean requiresCycle(Program program) {
//		if(program.getSkipStatement())
//			this.setLine(this.getLine()+1);
		if (!this.getStatements().get(this.getLine()).requiresCycle(program))
			return this.getLine()+1 < this.getStatements().size();
		return true;
	}
	
	private int getLine() {
		return this.line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}

	private int line = 0;


	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean hasActionStatements() {
		return false;
	}
	
	@Override
	public void checkWellFormed(Program program) {
		this.getStatements().forEach((state) -> state.checkWellFormed(program));
	}

}

