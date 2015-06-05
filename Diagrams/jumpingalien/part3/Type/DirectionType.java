package jumpingalien.part3.Type;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionType extends Type {

	private Direction item;
	private String name;

	public DirectionType(){

	}
	public DirectionType(Direction item){
		this.item = item;
	}
	
	
	@Override
	public String toString() {
		if(this.getValueOfConstant(this.getProgram()) == Direction.DOWN)
		return "DOWN";
		if(this.getValueOfConstant(this.getProgram()) == Direction.UP)
			return "UP";
		if(this.getValueOfConstant(this.getProgram()) == Direction.LEFT)
			return "LEFT";
		else 
			return "RIGHT";
	}

	
	public void setName(String name) {
        this.name = name;		
	}
	public void setValue(Direction val) {
        this.item = val;
	}
	@Override
	public Direction getValueOfConstant(Program program) {
		this.setProgram(program);
		return this.item;
	}

}
