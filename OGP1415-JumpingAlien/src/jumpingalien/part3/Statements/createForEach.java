package jumpingalien.part3.Statements;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

public class createForEach extends Statement {


	private Statement body;
	private SortDirection direction;
	private Expression where;
	private Kind variableKind;
	private String name;
	private Expression sort;



	public createForEach(String variableName, Kind variableKind,
			Expression where, Expression sort, SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setVariableName(variableName);
		this.setvariableKind(variableKind);
		this.setWhere(where);
		this.setSort(sort);
		this.setDirection(sortDirection);
		this.setBody(body);
	}
	
	public Statement getBdy(){
		return this.body;
	}

	
	public SortDirection getDirection(){
		return this.direction;
	}
	
	public Expression where(){
		return this.where;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Kind getVarKind(){
		return this.variableKind;
	}
	
	
	private void setBody(Statement body) {
		this.body = body;
		
	}



	private void setDirection(SortDirection sort) {
		this.direction = sort;
	}



	private void setSort(Expression sort) {
		this.sort = sort;
		
	}

	public Expression getSort(){
		return this.sort;
	}


	private void setWhere(Expression where) {
		this.where = where;
	}



	private void setvariableKind(Kind variableKind) {
        this.variableKind  = variableKind;		
	}



	private void setVariableName(String variableName) {
		this.name = variableName;
	}


	@Override
	public void executeCycle(Program program) {
		if(!(this.isValidProgram(program)))
			throw new NullPointerException("Program is null "+ getSourceLocation());
		if(this.getSort() != null)
		 program.addVariablesToMap(this.getName(), (Type) this.getSort().evaluate(program).getValueOfConstant(program));
		else 
			program.addVariablesToMap(this.getName(), new gameObjectType(null));
		if(this.getDirection() == SortDirection.ASCENDING)
			program.getObject().setDirection((int) Math.cos(0));
		else if (this.getDirection() == SortDirection.DESCENDING)
			program.getObject().setDirection((int) Math.cos(Math.PI));
		this.getBdy().executeCycle(program);
	}

	@Override
	public boolean isWellFormed() {
		return (!(this.hasActionStatements()));
	}

	@Override
	public boolean hasActionStatements() {
		return this.getBdy().hasActionStatements();
	}

	
}
