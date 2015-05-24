package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
  /**
   * 
   * @author Afraz Salim and Niels van Belle
   * version 2.0
   *
   * @invar hasProperSlimes() 
   */
public class School implements Cloneable {

	/**
	 * A constructor to create the school.
	 * @post new.getSlimesOutOfSchool() == {}
	 */
	public School() {
		school = new ArrayList<Slime>();
		setNumberOfschools(getNumberOfschools() + 1);
	}

	/**
	 * 
	 * @param numberOfSchools
	 * 			The new number of schools
	 * @post new.getNumberOfSchools() == numberOfSchools
	 */
	public static void setNumberOfschools(int numberOfSchools){
		School.numberOfschools= numberOfSchools;
	}
	
	/**
	 * 
	 * Return the current number of schools
	 */
	@Basic
	public static int getNumberOfschools(){
		return School.numberOfschools;
	}
	
	/**
	 * Variable registering the current number of schools 
	 */
	private static int numberOfschools;
	
	/**
	 * 
	 * @param slime
	 * 			The slime that need to be add to this school
	 * @post slime.getSchool() == this
	 * @effect school.add(slime)
	 */
	public void addToSchool(Slime slime) {
		assert this.canHaveAsSlime(slime);
		slime.setSchool(this);
		this.school.add(slime);
	}
	
	/**
	 * 
	 * @param slime
	 * 			The slime that need to be removed from this school
	 * @effect school.remove(slime)
	 */
	public void removeFromSchool(Slime slime) {
		this.school.remove(slime);
		if (this.sizeOfSchool() == 0) {
			this.terminate();
			School.setNumberOfschools(School.getNumberOfschools() - 1);
		}
	}
	
	/**
	 * 
	 * @return result == (this.school.size())
	 */
	public int sizeOfSchool(){
		return this.school.size();
	}
	
	/**
	 * Return a clone of this School.
	 * 
	 * @return The resulting School is the same as this School
	 *         if and only if this School is immutable.
	 *       | (result == this) == (! this.isMutable())
	 */
	@Override
	public School clone() {
		try {
				return (School) super.clone();
		} catch (CloneNotSupportedException exc) {
			assert false;
			return null;
		}
	}
	
	
	/**
	 * 
	 * @return result ==  (this.school.clone())
	 */
	public ArrayList<Slime> getSlimesOutOfSchool(){
		return (ArrayList<Slime>) this.school.clone();
	}
	
	/**
	 * Variable registering an array of slimes in this school
	 */
	@Model
	private ArrayList<Slime> school;

	/**
	 * 
	 * @param slime
	 * 		The slime that need to be tested
	 * @return if (this.isTerminated())
	 * 				then result ==
	 * 						slime == null
	 * 			else
	 * 				then result ==
	 * 						slime != null && !slime.isDeath()
	 */
	@Model
	private boolean canHaveAsSlime(Slime slime) {
		if (this.isTerminated())
			return slime == null;
		return slime != null && !slime.isDeath(); 
	}
	
	/**
	 * 
	 * @return for each slime in this.getSlimesOuOfSchool
	 * 				do if (!(this.canHaveAsSlime(slime) && (slime == null || slime.getSchool() == this)))
	 * 					then result == false
	 * 			result == true
	 * 			
	 */
	private boolean hasProperSlimes() {
		for (Slime slime: this.getSlimesOutOfSchool()) {
			if (!(this.canHaveAsSlime(slime) && (slime == null || slime.getSchool() == this)))
				return false;
		}
		return true;
	}
	
	/**
	 * @ effect if (!this.isTerminated())
	 * 			then new.isTerminated() == true
	 * 				for each slime in this.getSlimesOutOfSchool()
	 * 					do slime == null
	 */
	private void terminate() {
		if (!this.isTerminated()) {
			this.setIsTerminated(true);
			for (Slime slime: this.getSlimesOutOfSchool())
				slime = null;
		}
	}
	
	/**
	 * 
	 * Return the current termination of the school
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * 
	 * @param terminated
	 * 			Boolean storing the new termination of the school
	 * @post new.isTerminated == terminated
	 */
	private void setIsTerminated(boolean terminated) {
		this.isTerminated = terminated;
	}
	
	/**
	 * Variable to store the current termination of the school
	 */
	private boolean isTerminated;
}
