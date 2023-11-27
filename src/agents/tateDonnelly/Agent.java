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
		action[MarioActions.SPEED.getValue()] = true;
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
		checkForEnemies(model);
		agentDirection();
		agentSpeed();
	}
	
	private void agentSpeed(){
		action[MarioActions.SPEED.getValue()]=true;
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
		int[][] scene = model.getMarioSceneObservation();
		if(model.mayMarioJump() && shouldMarioJump){
			action[MarioActions.JUMP.getValue()]=true;
		}
	}
	
	private void checkForEnemies(MarioForwardModel model){
		int[][] enemies = model.getMarioEnemiesObservation();
		for (int i = 0; i > -6; i--) {
			for (int j = 1; j < 6; j++) {
				if(getCoords(j,i,enemies)!=0){
					shouldMarioJump=true;
				}
			}
		}
		
	}
	
	private int getCoords(int xCoords, int yCoords, int[][] scene) {
		int realX = 8 + xCoords;
		int realY = 8 + yCoords;
		System.out.println("X: "+realX+" Y: "+realY);
		
		return scene[realX][realY];
	}
}
