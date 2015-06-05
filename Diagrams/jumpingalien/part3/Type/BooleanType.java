package jumpingalien.part3.Type;

import jumpingalien.model.Program;

public class BooleanType extends Type {

	private boolean value;
	private String name;

	public BooleanType(boolean value) {
       this.value = value;
	}

	

	@Override
	public String toString() {
		return Boolean.toString(this.getValueOfConstant(this.getProgram()));
	}

	

	


	public void setName(String name) {
       this.name = name;		
	}


	@Override
	public Boolean getValueOfConstant(Program program) {
		this.setProgram(program);
		return this.value;
	}

    public boolean getValue(){
    	return this.value;
    }

	public void setValue(boolean val) {
       this.value = val;
	}
}
