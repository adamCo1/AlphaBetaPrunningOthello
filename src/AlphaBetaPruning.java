import java.util.List;

public class AlphaBetaPruning implements ISolver
{
	@Override
	public String getSolverName() 
	{
		return "Alpha-Beta Pruning";
	}
	
	@Override
	public double solve
	(
		IBoard board
	) 
	{
		Node root = new Node(board, Node.NodeType.MAX);
		return AlphaBetaPruningAlgorithm(root, - Double.MAX_VALUE, Double.MAX_VALUE);
	}

	
	private double AlphaBetaPruningAlgorithm(Node 	node, double 	p_alpha, double 	p_beta) {

		double value = 0;

		if(node.getNodeType() == Node.NodeType.MAX)
			value = - Double.MAX_VALUE;
		else
			value = Double.MAX_VALUE;

		List<Node> childrens = node.getNodeChildren();

		for(Node child :
		childrens){

			double childValue ;

			if(child.isTerminalNode()){//
				childValue = child.getScore();
			}else{//not a terminal . calculate its value recursively . for alpha pass down the best options of the
				//maximizer to the root and for the beta pass the best option for the minimizer to the root

				if(p_alpha >= p_beta)//don't explore more children's
					return value;

				childValue = AlphaBetaPruningAlgorithm(child,p_alpha,p_beta);
			}

			if(node.getNodeType() == Node.NodeType.MAX){
				value = Math.max(value,childValue);
				p_alpha = value ;
			}else{
				value = Math.min(value,childValue);
				p_beta = value;
			}
		}

		return value;
	}
	
}
