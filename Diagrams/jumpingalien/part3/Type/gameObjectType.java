package jumpingalien.part3.Type;

import jumpingalien.model.Program;

public class gameObjectType extends Type {


	private Object item;
	private String name;

	
	public gameObjectType(){

	}
	public gameObjectType(Object item){
		this.item = item;
	}
	
	
	@Override
	public String toString() {
		return this.name;
	}

	
	public void setName(String name) {
        this.name = name;		
	}
	public void setValue(Object value) {
      this.item = 	value;	
	}
	
	public Object getObject(){
		return this.item;
	}
	
	@Override
	public Object getValueOfConstant(Program program) {
		this.setProgram(program);
		return this.item;
	}

}
