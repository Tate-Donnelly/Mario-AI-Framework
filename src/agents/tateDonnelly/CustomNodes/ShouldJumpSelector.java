package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.Selector;
import engine.core.MarioForwardModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ShouldJumpSelector extends Selector {
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	private int[][] enemies;
	
	public ShouldJumpSelector(Agent agent,MarioForwardModel model, int[][] scene, int[][] enemies) {
		super(new ArrayList<>(),true);
		this.agent=agent;
		this.model = model;
		this.scene = scene;
		this.enemies = enemies;
		SetSelector();
	}
	
	private void SetSelector(){
		IsGapNode isGapNode=new IsGapNode(agent,model,scene,2);
		IsObstacleNode isObstacleNode=new IsObstacleNode(agent,model,scene,5);
		IsEnemyNode isEnemyNode=new IsEnemyNode(agent,model,enemies,4);
		children=Arrays.asList(new Node[]{isGapNode,isEnemyNode,isObstacleNode});
		Evaluate();
	}
	
}
