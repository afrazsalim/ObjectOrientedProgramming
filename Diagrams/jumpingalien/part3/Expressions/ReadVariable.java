package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.DirectionType;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class ReadVariable extends ConstantExpression {

	private Type vairbleType;
	private String variableName;
	private double value;

	public ReadVariable(String variableName, Type variableType, SourceLocation sourceLocation) {
	          super(sourceLocation);
	          this.setType(variableType);
	          this.setName(variableName);
	          this.setValue(0.0);
  	}

	private void setValue(double d) {
         this.value = d;		
	}
	
	
	public double getValue(){
		return this.value;
	}

	
	private void setType(Type variableType) {
          this.vairbleType = variableType;		
	}

	private void setName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public String toString() {
		return this.getType() + " "+ this.getName();
	}

	private Type getType() {
           return this.vairbleType;
	}

	private String getName() {
		return this.variableName;
	}
	

	@Override
	public Type evaluate(Program program) {
        if(!(this.isValidProgram(program)))
        	throw new NullPointerException("given program is null at " + getSourceLocation());
        this.setProgram(program);
		if(this.getType()  instanceof DoubleType){
        	 DoubleType value = (DoubleType)program.getVariables(getName(), getType());
         return new DoubleType(value.getValueOfConstant(getProgram()));
        }
        else  if(this.getType() instanceof BooleanType){
        	BooleanType value = (BooleanType) program.getVariables(getName(), getType());
            return new BooleanType((boolean)(value.getValueOfConstant(program)));
        }
        else  if(this.getType() instanceof DirectionType){
        	Type value = (DirectionType)program.getVariables(getName(), getType());
        	return new DirectionType((Direction)(value.getValueOfConstant(program)));
        }
        else  if(this.getType() instanceof gameObjectType){
        	Type value = (gameObjectType)program.getVariables(getName(), getType());
        	return new gameObjectType((Object)(value.getValueOfConstant(program)));
        }
         throw new IllegalArgumentException("Index out of bound , no such elements exist");
	}

	
	@Override
	public Expression getValue(Program program) {
        Type type = (Type) this.evaluate(program);
        if(type instanceof DoubleType)
		    return new DoubleLiteral((double) type.getValueOfConstant(program),getSourceLocation());
        if(type instanceof gameObjectType)
    		return new selfDefined((Object) type.getValueOfConstant(program),getSourceLocation());
        if(type instanceof DirectionType)
        	return new createDirection((Direction)type.getValueOfConstant(program),getSourceLocation());
        else if(type instanceof BooleanType){
        	if((boolean) type.getValueOfConstant(program))
        	return new TrueLiteral(getSourceLocation());
        	return new FalseLiteral(getSourceLocation());
        }
   	throw new IndexOutOfBoundsException("given arguments are not recognized");
	}

}
