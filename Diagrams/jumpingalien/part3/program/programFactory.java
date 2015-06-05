package jumpingalien.part3.program;

import java.util.List;
import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.Expressions.Addition;
import jumpingalien.part3.Expressions.AndExpression;
import jumpingalien.part3.Expressions.Division;
import jumpingalien.part3.Expressions.DoubleLiteral;
import jumpingalien.part3.Expressions.Equals;
import jumpingalien.part3.Expressions.Expression;
import jumpingalien.part3.Expressions.FalseLiteral;
import jumpingalien.part3.Expressions.GetX;
import jumpingalien.part3.Expressions.GetY;
import jumpingalien.part3.Expressions.Multiplication;
import jumpingalien.part3.Expressions.NotEquals;
import jumpingalien.part3.Expressions.NotExpression;
import jumpingalien.part3.Expressions.NullLiteral;
import jumpingalien.part3.Expressions.RandomExpression;
import jumpingalien.part3.Expressions.ReadVariable;
import jumpingalien.part3.Expressions.Sqrt;
import jumpingalien.part3.Expressions.TrueLiteral;
import jumpingalien.part3.Expressions.createDirection;
import jumpingalien.part3.Expressions.getHitPoints;
import jumpingalien.part3.Expressions.getTile;
import jumpingalien.part3.Expressions.getWidth;
import jumpingalien.part3.Expressions.getheight;
import jumpingalien.part3.Expressions.greaterOrEquals;
import jumpingalien.part3.Expressions.greaterThan;
import jumpingalien.part3.Expressions.isDead;
import jumpingalien.part3.Expressions.isDucking;
import jumpingalien.part3.Expressions.isJumping;
import jumpingalien.part3.Expressions.isMagma;
import jumpingalien.part3.Expressions.isMazub;
import jumpingalien.part3.Expressions.isMoving;
import jumpingalien.part3.Expressions.isPassAble;
import jumpingalien.part3.Expressions.isPlant;
import jumpingalien.part3.Expressions.isShark;
import jumpingalien.part3.Expressions.isSlime;
import jumpingalien.part3.Expressions.isTerrain;
import jumpingalien.part3.Expressions.isWater;
import jumpingalien.part3.Expressions.lessThan;
import jumpingalien.part3.Expressions.lessThanOrEqual;
import jumpingalien.part3.Expressions.orExpression;
import jumpingalien.part3.Expressions.searchObject;
import jumpingalien.part3.Expressions.selfDefined;
import jumpingalien.part3.Expressions.subtraction;
import jumpingalien.part3.Statements.Print;
import jumpingalien.part3.Statements.Sequence;
import jumpingalien.part3.Statements.Skip;
import jumpingalien.part3.Statements.StartJumping;
import jumpingalien.part3.Statements.Statement;
import jumpingalien.part3.Statements.assignment;
import jumpingalien.part3.Statements.creatWait;
import jumpingalien.part3.Statements.createBreak;
import jumpingalien.part3.Statements.createForEach;
import jumpingalien.part3.Statements.createIf;
import jumpingalien.part3.Statements.startDucking;
import jumpingalien.part3.Statements.startRun;
import jumpingalien.part3.Statements.stopDucking;
import jumpingalien.part3.Statements.stopJumping;
import jumpingalien.part3.Statements.stopRun;
import jumpingalien.part3.Statements.whileStatement;
import jumpingalien.part3.Type.BooleanType;
import jumpingalien.part3.Type.DirectionType;
import jumpingalien.part3.Type.DoubleType;
import jumpingalien.part3.Type.Type;
import jumpingalien.part3.Type.gameObjectType;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

public class programFactory implements IProgramFactory<Expression, Statement,Type,Program> {

	@Override
	public Expression createReadVariable(String variableName, Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(variableName,variableType,sourceLocation);
	}

	@Override
	public Expression createDoubleConstant(double value, SourceLocation sourceLocation) {
		return new DoubleLiteral(value,sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new TrueLiteral(sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new FalseLiteral(sourceLocation);
	}

	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		return new NullLiteral(sourceLocation);
	}


	
	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		return new selfDefined(sourceLocation);
	}

	@Override
	public Expression createDirectionConstant(Direction value,SourceLocation sourceLocation) {
		return new createDirection(value , sourceLocation);
	}

	@Override
	public Expression createAddition(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Addition(left,right,sourceLocation);
	}

	@Override
	public Expression createSubtraction(Expression left, Expression right, SourceLocation sourceLocation) {
		return new subtraction(left,right,sourceLocation);
	}

	@Override
	public Expression createMultiplication(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Multiplication(left,right,sourceLocation);
	}

	@Override
	public Expression createDivision(Expression left, Expression right, SourceLocation sourceLocation) {
         
		return new Division(left,right,sourceLocation);
	}

	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new Sqrt(expr,sourceLocation);
	}

	@Override
	public Expression createRandom(Expression maxValue, SourceLocation sourceLocation) {
		return new RandomExpression(maxValue,sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new AndExpression(left,right,sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new orExpression(left,right,sourceLocation);
	}

	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		return new NotExpression(expr,sourceLocation);
	}

	@Override
	public Expression createLessThan(Expression left, Expression right, SourceLocation sourceLocation) {
		return new lessThan(left,right,sourceLocation);
	}

	@Override
	public Expression createLessThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		return new lessThanOrEqual(left,right,sourceLocation);
	}

	@Override
	public Expression createGreaterThan(Expression left, Expression right, SourceLocation sourceLocation) {
		return new greaterThan(left,right,sourceLocation);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		return new greaterOrEquals(left,right,sourceLocation);
	}

	@Override
	public Expression createEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Equals(left,right,sourceLocation);
	}

	@Override
	public Expression createNotEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		return new NotEquals(left,right,sourceLocation);
	}

	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr,sourceLocation);
	}

	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr,sourceLocation);
	}

	@Override
	public Expression createGetWidth(Expression expr, SourceLocation sourceLocation) {
		return new getWidth(expr,sourceLocation);
	}
	

	@Override
	public Expression createGetHeight(Expression expr, SourceLocation sourceLocation) {
		return new getheight(expr,sourceLocation);
	}

	@Override
	public Expression createGetHitPoints(Expression expr, SourceLocation sourceLocation) {
		return new getHitPoints(expr,sourceLocation);
	}

	@Override
	public Expression createGetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		return new getTile(x,y,sourceLocation);
	}

	@Override
	public Expression createSearchObject(Expression direction, SourceLocation sourceLocation) {
		return new searchObject(direction,sourceLocation);
	}

	@Override
	public Expression createIsMazub(Expression expr, SourceLocation sourceLocation) {
		return new isMazub(expr, sourceLocation);
	}

	@Override
	public Expression createIsShark(Expression expr, SourceLocation sourceLocation) {
		return new isShark(expr,sourceLocation);
	}

	@Override
	public Expression createIsSlime(Expression expr, SourceLocation sourceLocation) {
		return new isSlime(expr,sourceLocation);
	}

	@Override
	public Expression createIsPlant(Expression expr, SourceLocation sourceLocation) {
		return new isPlant(expr, sourceLocation);
	}

	
	@Override
	public Expression createIsDead(Expression expr, SourceLocation sourceLocation) {
		return new isDead(expr,sourceLocation);
	}

	@Override
	public Expression createIsTerrain(Expression expr, SourceLocation sourceLocation) {
		return new isTerrain(expr,sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression expr, SourceLocation sourceLocation) {
		return new isPassAble(expr,sourceLocation);
	}

	@Override
	public Expression createIsWater(Expression expr, SourceLocation sourceLocation) {
		return new isWater(expr,sourceLocation);
	}

	@Override
	public Expression createIsMagma(Expression expr, SourceLocation sourceLocation) {
		return new isMagma(expr,sourceLocation);
	}

	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new jumpingalien.part3.Expressions.isAir(expr,sourceLocation);
	}

	@Override
	public Expression createIsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		return new isMoving(expr,direction,sourceLocation);
	}

	@Override
	public Expression createIsDucking(Expression expr, SourceLocation sourceLocation) {
		return new isDucking(expr,sourceLocation);
	}

	@Override
	public Expression createIsJumping(Expression expr, SourceLocation sourceLocation) {
		return new isJumping(expr,sourceLocation);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType, Expression value,
			SourceLocation sourceLocation) {
		return new assignment(variableName,variableType,value,sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new whileStatement(condition,body,sourceLocation);
	}

	@Override
	public Statement createForEach(String variableName,Kind variableKind,
			Expression where, Expression sort, SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {

		return new createForEach(variableName,variableKind,where,sort,sortDirection,body,sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new createBreak(sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new createIf(condition,ifBody,elseBody,sourceLocation);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value,sourceLocation);
	}

	@Override
	public Statement createStartRun(Expression direction, SourceLocation sourceLocation) {
		return new startRun(direction,sourceLocation);
	}

	@Override
	public Statement createStopRun(Expression direction, SourceLocation sourceLocation) {
		return new stopRun(direction,sourceLocation);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJumping(sourceLocation);
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
        return new stopJumping(sourceLocation);
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new startDucking(sourceLocation);
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new stopDucking(sourceLocation);
	}

	@Override
	public Statement createWait(Expression duration, SourceLocation sourceLocation) {
		return new creatWait(duration,sourceLocation);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Skip(sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new Sequence(statements,sourceLocation);
	}

	@Override
	public Type getDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type getBoolType() {
		return new BooleanType(false);
	}

	@Override
	public Type getGameObjectType() {
		return new gameObjectType();
	}

	@Override
	public Type getDirectionType() {
		return new DirectionType();
	}

	@Override
	public Program createProgram(Statement mainStatement, Map<String, Type> globalVariables) {
		return new Program(mainStatement,globalVariables);
	}
}
