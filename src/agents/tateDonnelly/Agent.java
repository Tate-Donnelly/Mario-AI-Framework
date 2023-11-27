package agents.tateDonnelly;
import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
	private boolean facing_right;
	private boolean[] action = null;
	boolean shouldMarioJump=false;
	
	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {
		action = new boolean[MarioActions.numberOfActions()];
		facing_right=true;
		action[MarioActions.RIGHT.getValue()] = true;
		action[MarioActions.LEFT.getValue()] = false;
		action[MarioActions.JUMP.getValue()] = false;
		action[MarioActions.SPEED.getValue()] = false;
	}
	
	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
		agentRun(model);
		agentJump(model);
		return action;
	}
	
	@Override
	public String getAgentName() {
		return "Tate's Agent";
	}
	
	private void agentRun(MarioForwardModel model){
		//agentDirection();
		agentSpeed();
	}
	
	private void agentSpeed(){
		action[MarioActions.SPEED.getValue()]=false;
	}
	
	private void agentDirection(){
		boolean shouldChangeDirections=false;
		if(shouldChangeDirections){
			facing_right=!facing_right;
		}
		
		action[MarioActions.RIGHT.getValue()]=facing_right;
		action[MarioActions.LEFT.getValue()]=!facing_right;
	}
	
	private void agentJump(MarioForwardModel model){
		boolean canJump=model.mayMarioJump() && model.isMarioOnGround();
		boolean jumpRestrictions=checkForEnemies(model) || shouldJumpScene(model) || isThereAGap(model);
		if(canJump && jumpRestrictions){
			action[MarioActions.JUMP.getValue()]=true;
		} else if (model.isMarioOnGround()) {
			action[MarioActions.JUMP.getValue()]=false;
		}
	}
	
	private boolean checkForEnemies(MarioForwardModel model){
		int[][] enemies = model.getMarioEnemiesObservation();
		for (int i = 0; i > -4; i--) {
			for (int j = 1; j < 4; j++) {
				if(getCoords(j,i,enemies)!=0){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean shouldJumpScene(MarioForwardModel model){
		int[][] scene = model.getMarioSceneObservation();
		for (int i = 0; i > -2; i--) {
			for (int j = 1; j < 5; j++) {
				int coord=getCoords(j,i,scene);
				if(coord!=0){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isThereAGap(MarioForwardModel model){
		int[][] scene = model.getMarioSceneObservation();
		for (int i = 1; i < 3; i++) {
			for (int j = 2; j < 8; j++) {
				if (getCoords(i, j, scene) != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private int getCoords(int xCoords, int yCoords, int[][] scene) {
		int realX = 8 + xCoords;
		int realY = 8 + yCoords;
		
		return scene[realX][realY];
	}
}
