package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.DirectionType;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class assignment extends Statement {

	private Type type;
	private String name;
	private Expression value;

	public assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
        this.setVariableName(variableName);
        this.setVariableType(variableType);
        this.setExpression(value);
	}

	public String toString(){
		return this.getName() + " = " + this.getExpression().evaluate(getProgram()).getValueOfConstant(getProgram());
	}
	
	
	private void setExpression(Expression value) {
         this.value= value;		
	}
	
	public String getName(){
		return this.name;
	}
	
	
	public Type getType(){
		return this.type;
	}
	
	
	public Expression getExpression(){
		return this.value;
	}


	private void setVariableType(Type variableType) {
        this.type = variableType;		
	}

	private void setVariableName(String variableName) {
         this.name = variableName;		
	}

	@Override
	public void executeCycle(Program program) {
		if(program == null)
			throw new NullPointerException("Program is null " + this.getSourceLocation());
		this.setProgram(program);
       if(this.getType() instanceof DoubleType){
    	  DoubleType type = (DoubleType) this.getExpression().evaluate(this.getProgram());
    	  double value = type.getValueOfConstant(getProgram());
    	  type.setValue(value);
    	  type.setName(getName());
   	       DoubleType var = new DoubleType(value);
   	       program.addVariablesToMap(getName(), var);
       }
       else  if(this.getType() instanceof BooleanType){
     	  BooleanType type = (BooleanType) this.getExpression().evaluate(this.getProgram());
     	  boolean value = type.getValueOfConstant(getProgram());
     	  type.setValue(value);
     	  type.setName(getName());
     	 BooleanType var = new BooleanType(value);
     	 program.addVariablesToMap(getName(), var);
       }
       else if(this.getType() instanceof gameObjectType){
    	  gameObjectType type = (gameObjectType) this.getExpression().evaluate(this.getProgram());
      	  Object value = type.getValueOfConstant(program);
      	  type.setValue(value);
      	  type.setName(getName());
      	  gameObjectType var = new gameObjectType(value);
        	program.addVariablesToMap(getName(), var);
     	  }
       else if(this.getType() instanceof DirectionType){
    	   DirectionType type = (DirectionType) this.getExpression().evaluate(this.getProgram());
    	   Object value = type.getValueOfConstant(getProgram());
       	   type.setValue((Direction) value);
       	   type.setName(getName());
       	DirectionType var = new DirectionType((Direction) value);
       	  program.addVariablesToMap(getName(), var);
        }
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
