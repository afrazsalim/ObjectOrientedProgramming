package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class createIf extends Statement {

	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;

	public createIf(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
        this.setCondition(condition);
        this.setIfBody(ifBody);
        this.setElseBody(elseBody);
	}
	
	@Override
	public void executeCycle(Program program) {
		if (this.getCurrentCycle() == 0) {
			this.setResultCondition ((boolean) this.getCondition().evaluate(program).getValueOfConstant(program));
			if (this.getResultCondition())
				this.getIfBody().reinitialize();
			else if (this.getElseBody() != null)
				this.getElseBody().reinitialize();
		}
		else {
			if (this.getResultCondition()) {
				this.getIfBody().executeCycle(program);
				this.getIfBody().incrementCycle();
			}
			else if (this.getElseBody() != null) {
				this.getElseBody().executeCycle(program);
				this.getElseBody().incrementCycle();
			}
		}
	}
	
	@Override
	public boolean requiresCycle(Program program) {
		if (this.getCurrentCycle() == 0) {
			return true;
		}
		else {
			if (this.getCurrentCycle() > 0) {
				if (this.getResultCondition())
					return (this.getIfBody().requiresCycle(program));
				else if (this.getElseBody() != null)
					return (this.getElseBody().requiresCycle(program));
			}
			return false;
		}
	}
	
	private Expression getCondition(){
		return this.condition;
	}
	
	private Statement getIfBody(){
		return this.ifBody;
	}
	
	private Statement getElseBody(){
		return this.elseBody;
	}
	

	private void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}


	private void setIfBody(Statement ifBody) {
     this.ifBody = ifBody;		
	}

	private void setCondition(Expression condition) {
      this.condition = condition;		
	}
	
	public boolean getResultCondition() {
		return this.resultCondition;
	}
	
	private void setResultCondition(boolean result) {
		this.resultCondition = result;
	}

	private boolean resultCondition;

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
		this.getIfBody().checkWellFormed(program);
		if (this.getElseBody() != null)
			this.getElseBody().checkWellFormed(program);
	}
	
}
