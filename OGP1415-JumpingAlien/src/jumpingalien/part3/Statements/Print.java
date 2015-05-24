package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.SourceLocation;

public class Print extends Statement {

	private Expression value;

	public Print(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setExpression(value);
	}

	
	private void setExpression(Expression value) {
      this.value = value;		
	}
	
	public Expression getValue(){
		return value;
	}

	@Override
	public void executeCycle(Program program) {
       if(this.getValue().evaluate(program).getValueOfConstant(program) == null)
    	   System.out.println("null");
    	   else 
    		   System.out.println(this.getValue().evaluate(program).getValueOfConstant(program));
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
