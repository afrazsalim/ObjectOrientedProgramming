package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.DoubleLiteral;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.programs.SourceLocation;

public class whileStatement extends Statement {

	private Expression condition;
	private Statement body;

	public whileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setCondition(condition);
		this.setBody(body);
	}

	
	private void setBody(Statement body) {
       this.body = body;		
	}

	private void setCondition(Expression condition) {		
		this.condition = condition;
	}
	
	@Override
	public void executeCycle(Program program) {
		if (this.getCurrentCycle() == 0) {
			this.setResultCondition ((boolean) this.getCondition().evaluate(program).getValueOfConstant(program));
			this.getBody().reinitialize();
		}
		else {
				this.getBody().executeCycle(program);
				this.getBody().incrementCycle();
				if (program.needBreakProgram()) {
					this.setResultCondition(false);
					this.setCurrentCycle(0);
					program.handledBreakProgram();
				}
		}
	}
	
	
	@Override
	public boolean requiresCycle(Program program) {
		if (this.getCurrentCycle() == 0) {
			return true;
		}
		else {
			if (this.getCurrentCycle() == 1) {
				return ( this.getResultCondition() && this.getBody().requiresCycle(program) );
			}
			else {
				if (!this.getBody().requiresCycle(program)) {
					this.reinitialize();
				}
				return true;
			}
		}
	}
	

	private Statement getBody() {
 		return this.body;
	}

	private Expression getCondition() {
		return this.condition;
	}
	
	public boolean getResultCondition() {
		return this.resultCondition;
	}
	
	private void setResultCondition(boolean result) {
		this.resultCondition = result;
	}

	private boolean resultCondition;

	@Override
	public void checkWellFormed(Program program) {
		boolean hasBreak = program.isHasBreak();
		this.getBody().checkWellFormed(program);
		program.setHasBreak(hasBreak);
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
