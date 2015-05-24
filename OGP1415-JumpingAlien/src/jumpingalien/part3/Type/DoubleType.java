package jumpingalien.part3.Type;

import jumpingalien.model.Program;

public class DoubleType extends Type {

	private double value;
	private String name;
	
	public DoubleType(){
		this.value = 0;
	}

	public DoubleType(double value) {
       this.setValue(value);
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setValue(double value) {
       this.value = value;		
	}


	@Override
	public String toString() {
		return Double.toString(getValueOfConstant(this.getProgram()));
	}


	@Override
	public Double getValueOfConstant(Program program) {
		this.setProgram(program);
		return this.value;
	}
	
}
