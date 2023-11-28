package agents.tateDonnelly;

import engine.core.MarioForwardModel;

public class IsEnemyNode extends Node{
	private Agent agent;
	private MarioForwardModel model;
	int[][] enemies;
	
	public IsEnemyNode(Agent agent,MarioForwardModel model, int[][] enemies) {
		this.agent=agent;
		this.model = model;
		this.enemies=enemies;
	}
	
	
	@Override
	public NodeState Evaluate() {
		for (int i = 0; i > -4; i--) {
			for (int j = 1; j < 4; j++) {
				if(agent.getCoords(j,i,enemies)!=0){
					return NodeState.SUCCESS;
				}
			}
		}
		return NodeState.FAILURE;
	}
}
