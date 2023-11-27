package agents.tateDonnelly;
import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

public class Agent implements MarioAgent {
	private boolean facing_right;
	private boolean[] action = null;
	
	
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
		boolean shouldMarioJump=true;
		if(model.mayMarioJump() && shouldMarioJump){
			action[MarioActions.JUMP.getValue()]=true;
		}
	}
}
