package jumpingalien.part3.Type;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;

public abstract class Type implements Cloneable {

 	
	private Program program;

	public abstract String toString();
	
	public abstract Object getValueOfConstant(Program program);

	protected  Type clone(){
		try {
			return (Type) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	protected void setProgram(Program program){
		this.program = program;
	}
	
	protected Program getProgram(){
		return this.program;
	}
	
}
