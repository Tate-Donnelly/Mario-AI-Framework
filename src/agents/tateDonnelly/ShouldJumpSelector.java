package agents.tateDonnelly;

import engine.core.MarioForwardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShouldJumpSelector extends Selector{
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	private int[][] enemies;
	
	public ShouldJumpSelector(Agent agent,MarioForwardModel model, int[][] scene, int[][] enemies) {
		super(new ArrayList<>());
		this.agent=agent;
		this.model = model;
		this.scene = scene;
		this.enemies = enemies;
		SetSelector();
	}
	
	private void SetSelector(){
		IsGapNode isGapNode=new IsGapNode(agent,model,scene);
		IsObstacleNode isObstacleNode=new IsObstacleNode(agent,model,scene);
		IsEnemyNode isEnemyNode=new IsEnemyNode(agent,model,enemies);
		children=Arrays.asList(new Node[]{isGapNode,isEnemyNode,isObstacleNode});
		Evaluate();
	}
	
}
