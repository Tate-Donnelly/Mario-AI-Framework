package agents.tateDonnelly;
import agents.tateDonnelly.CustomNodes.*;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.Selector;
import agents.tateDonnelly.StandardNodes.Sequence;
import agents.tateDonnelly.Tasks.JumpTask;
import agents.tateDonnelly.Tasks.ResetJumpTask;
import agents.tateDonnelly.Tasks.RunTask;
import agents.tateDonnelly.Tasks.QuestionBlockJumpTask;
import engine.core.MarioAgent;
import engine.core.MarioForwardModel;
import engine.core.MarioTimer;
import engine.helper.MarioActions;

import java.util.Arrays;
import java.util.List;

public class Agent implements MarioAgent {
	private boolean facing_right;
	private boolean[] action = null;
	
	private Node behaviorTree;
	
	
	@Override
	public void initialize(MarioForwardModel model, MarioTimer timer) {
		action = new boolean[MarioActions.numberOfActions()];
		facing_right=true;
		action[MarioActions.RIGHT.getValue()] = false;
		action[MarioActions.LEFT.getValue()] = false;
		action[MarioActions.JUMP.getValue()] = false;
		action[MarioActions.SPEED.getValue()] = false;
		action[MarioActions.DOWN.getValue()] = false;
		SetTree(model);
	}
	
	@Override
	public boolean[] getActions(MarioForwardModel model, MarioTimer timer) {
		SetTree(model);
		behaviorTree.Evaluate();
		return action;
	}
	
	private void SetTree(MarioForwardModel model){
		int[][] scene = model.getMarioSceneObservation();
		int[][] enemies = model.getMarioEnemiesObservation();
		
		ShouldResetNode shouldResetJump=new ShouldResetNode(model);
		ResetJumpTask resetJumpTask=new ResetJumpTask(this);
		Sequence resetJumpSeq=new Sequence(Arrays.asList(new Node[]{shouldResetJump,resetJumpTask}));
		
		//Should agent jump
		ShouldJumpSelector shouldJumpSelector=new ShouldJumpSelector(this,model,scene,enemies);
		JumpTask jumpTask=new JumpTask(this,model);
		CanJumpNode canAgentJump=new CanJumpNode(model);
		List<Node> jumpSeqChildren= Arrays.asList(new Node[]{canAgentJump,shouldJumpSelector,jumpTask});
		Sequence jumpSequence=new Sequence(jumpSeqChildren);
		
		//StarBlock
		IsQuestionBlock isQuestionBlockNode =new IsQuestionBlock(this,model,scene);
		QuestionBlockJumpTask questionBlockJumpTask =new QuestionBlockJumpTask(this,model);
		List<Node> qBlockSeqChildren= Arrays.asList(new Node[]{canAgentJump,isQuestionBlockNode, questionBlockJumpTask});
		Sequence qBlockSeq=new Sequence(qBlockSeqChildren);
		
		//Did Mario pass Q block?
		PastQuestionBlockNode pastQuestionBlockNode=new PastQuestionBlockNode(this,model,scene,5);
		RunTask runTask=new RunTask(this,false);
		Sequence pastQBlockSeq=new Sequence(Arrays.asList(new Node[]{pastQuestionBlockNode, runTask}));
		
		
		List<Node> qBlockSelChildren= Arrays.asList(new Node[]{qBlockSeq,pastQBlockSeq});
		Selector qBlockSelector=new Selector(qBlockSelChildren,false);
		
		RunTask agentRunRight=new RunTask(this,true);
		List<Node> selectorChildren= Arrays.asList(new Node[]{jumpSequence,qBlockSelector,resetJumpSeq,agentRunRight});
		behaviorTree=new Selector(selectorChildren,false);
	}
	
	public void setRun(boolean shouldRun, boolean faceRight){
		action[MarioActions.RIGHT.getValue()]=shouldRun && faceRight;
		action[MarioActions.LEFT.getValue()]=shouldRun && !faceRight;
	}
	
	@Override
	public String getAgentName() {
		return "Tate's Agent";
	}
	
	public int getCoords(int xCoords, int yCoords, int[][] scene) {
		int realX = 8 + xCoords;
		int realY = 8 + yCoords;
		
		return scene[realX][realY];
	}
	
	public void setJump(boolean shouldJump) {
		action[MarioActions.JUMP.getValue()]=shouldJump;
	}
}
