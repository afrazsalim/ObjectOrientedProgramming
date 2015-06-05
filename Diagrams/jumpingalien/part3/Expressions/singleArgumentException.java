package jumpingalien.part3.Expressions;

public abstract class singleArgumentException extends Expression {

	
	@Override
	public abstract String toString();
	

	protected abstract Expression clone();

	protected boolean isValidArgument(Expression expression) {
		return (expression != null);
	}
	

}
