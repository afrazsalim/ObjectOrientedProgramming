package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.programs.SourceLocation;

public class creatWait extends Statement {

	private Expression duration;


	public creatWait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDuration(duration);
	}

	private void setDuration(Expression duration) {
		 this.duration = duration;
	}

	public Expression getExpression(){
		return this.duration;
	}
	
//	private double getDuration(Program program) {
//		return (double) this.getExpression().evaluate(program).getValueOfConstant(program);
//	}
	
	private int getWaitCycles() {
		return this.waitCycles;
	}
	
	private void setWaitCycles(int cycles) {
		this.waitCycles = cycles;
	}
	
	private int waitCycles = 1;
			
	@Override
	public boolean requiresCycle(Program program) {
		return (this.getWaitCycles()>this.getCurrentCycle());
	}
	
	@Override
	public void executeCycle(Program program) {
		if (this.getCurrentCycle() == 0) {
			this.setWaitCycles((int)((double)this.getExpression().evaluate(program).getValueOfConstant(program)/0.001)); 
		}
	}

	@Override
	public void reinitialize() {
		super.reinitialize();
		this.setWaitCycles(1);
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
