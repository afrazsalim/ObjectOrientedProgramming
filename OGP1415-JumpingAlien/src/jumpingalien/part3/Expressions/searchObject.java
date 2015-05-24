package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.model.WorldObjects;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.SourceLocation;

public class searchObject extends ConstantExpression{

	private Expression direction;

	public searchObject(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setDirection(direction);
	}

	private void setDirection(Expression direction) {
        this.direction = direction;		
	}

	@Override
	public String toString() {
		return "ss";
	}
	
	protected Expression getDirection(){
		return this.direction;
	}

	
	
	@Override
	public Type evaluate(Program program) {
		WorldObjects object = null;
			NearestObject nearestPlant = new NearestObject();
			object = nearestPlant.get(program, program.getObject());
			System.out.println("object class is called  " + object);
		return new gameObjectType(object);
	}

	@Override
	public Expression getValue(Program program) {
		 Type object = this.evaluate(program);
		return this;
	}

}
