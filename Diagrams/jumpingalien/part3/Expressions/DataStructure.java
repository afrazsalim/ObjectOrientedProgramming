package jumpingalien.part3.Expressions;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.DirectionType;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DataStructure {
	
	private static Map<String, Type> globalVariables = new HashMap<String,Type>();
	private static Program program;
	public DataStructure(){
		//globalVariables = new HashMap<String,Type>();
	}
	
	
	
	public static void addToMap(String name,Type type){
		globalVariables.put(name, type);
	}
	
	static Program getProgram(){
		return program;
	}
	
	
	public  static void setProgram(Program program){
		DataStructure.program = program;
	}
	
	public static  Type getFromMap(String name, Type type){
		System.out.println(globalVariables + "dfff");
	     if(type instanceof DoubleType){
		return (globalVariables.get(name));
	     }
	     else if(type instanceof BooleanType)
	    	 return (globalVariables.get(name));
	     else if(type instanceof DirectionType)
	    	 return (globalVariables.get(name));
	     else if(type instanceof gameObjectType)
	    	 return (globalVariables.get(name));
	       else throw new IndexOutOfBoundsException("can,t handle");
	}

}
