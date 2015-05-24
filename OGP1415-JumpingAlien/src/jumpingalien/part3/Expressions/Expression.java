package jumpingalien.part3.Expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.Type;

public abstract class Expression implements Cloneable {
	
	/**
	 * Returns a string representation of the class.
	 */
	public abstract String toString();
	
	
	/**
	 * 
	 * @param program
	 *        The given program which evaluates a specific value.
	 * @return
	 *        Evaluates the given object or value and return the type of the object
	 *        or value.
	 */
	public abstract Type evaluate(Program program);
	
	/**
	 * A getter to get the value in type as it is given.
	 * @param program
	 *        The given program which evaluates a specific value.
	 * @return
	 *         Returns the value or object as expression.
	 */
	protected abstract Expression getValue(Program program);
	
	
	/**
	 * A checker to check whether a program is valid or not.
	 * @param program
	 *        The given program to be checked.
	 * @return
	 *        Returns true if and only if the given program is not null.
	 *        |result = (program != null)
	 */
	protected boolean isValidProgram(Program program){
		return program != null;
	}
	

	
}
