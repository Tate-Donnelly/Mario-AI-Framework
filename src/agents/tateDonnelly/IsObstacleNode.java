package agents.tateDonnelly;

import engine.core.MarioForwardModel;

import java.util.List;

public class IsObstacleNode extends Node{
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	
	public IsObstacleNode(Agent agent,MarioForwardModel model, int[][] scene) {
		this.agent=agent;
		this.model = model;
		this.scene = scene;
	}
	
	
	@Override
	public NodeState Evaluate() {
		for (int i = 0; i > -2; i--) {
			for (int j = 1; j < 5; j++) {
				int coord=agent.getCoords(j,i,scene);
				if(coord!=0){
					System.out.println("Success");
					return NodeState.SUCCESS;
				}
			}
		}
		return NodeState.FAILURE;
	}
}
