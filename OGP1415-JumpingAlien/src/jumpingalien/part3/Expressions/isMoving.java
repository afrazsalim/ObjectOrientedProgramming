package jumpingalien.part3.Expressions;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class isMoving extends booleanExpression {

	private Expression direction;

	public isMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
		this.setDirection(direction);
	}

	private void setDirection(Expression direction) {
       this.direction = direction;		
	}
	
	public Expression getDirection(){
		return this.direction;
	}

	@Override
	public String toString() {
		return Boolean.toString((boolean) this.evaluate(getProgram()).getValueOfConstant(getProgram()));
	}

	
	@Override
	public Type evaluate(Program program) {
		if(!(this.isValidProgram(program)))
		throw new NullPointerException("proram is null at "+ this.getSourceLocation());
		this.setProgram(program);
		if(!(this.isValidArgument(this.getExpression())))
			throw new NullPointerException("given argument is null at "+ this.getSourceLocation());
		if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Mazub)){
				if( ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingRight()
				&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.RIGHT)){
			  return new BooleanType(true);
	         }
				else if( ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingLeft()
						&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.LEFT)){
					  return new BooleanType(true);
		}
				else if( ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY()
						&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.UP)){
					  return new BooleanType(true);
		}
				else if( ((Mazub)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY()
						&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.DOWN)){
					  return new BooleanType(true);
		}
	 }
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Buzam)){
			if (((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingRight()
				&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.RIGHT)){
				return new BooleanType(true);
			}
			else if( ((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingLeft()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.LEFT)){
				  return new BooleanType(true);
			}
			else if( ((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.UP)){
				  return new BooleanType(true);
			}
			else if( ((Buzam)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingY()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.DOWN)){
				  return new BooleanType(true);
			}
		}
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Slime)){
			if (((Slime)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingRight()
				&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.RIGHT)){
				return new BooleanType(true);
			}
			else if( ((Slime)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingLeft()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.LEFT)){
				  return new BooleanType(true);
			}
			
		}
		else if((this.getExpression().evaluate(program).getValueOfConstant(program) instanceof Shark)){
			if (((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingRight()
				&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.RIGHT)){
				return new BooleanType(true);
			}
			else if( ((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).isMovingLeft()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.LEFT)){
				  return new BooleanType(true);
			}
			else if( ((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).isJumping()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.UP)){
				  return new BooleanType(true);
			}
			else if( ((Shark)this.getExpression().evaluate(program).getValueOfConstant(program)).isJumping()
					&&((Direction)this.getDirection().evaluate(program).getValueOfConstant(program) == Direction.DOWN)){
				  return new BooleanType(true);
			}
		}
		return new BooleanType(false);
	}
	

	@Override
	public Expression getValue(Program program) {
		this.setProgram(program);
		BooleanType type = (BooleanType) evaluate(program);
        if((boolean)type.getValueOfConstant(program))
		return new TrueLiteral(this.getSourceLocation());
       return new FalseLiteral(this.getSourceLocation());
	}

}
