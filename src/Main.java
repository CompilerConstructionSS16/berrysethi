
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "ab";
		Node root = BerrySethi( input);
		root.Debug();
		
	}
	
	public static Node BerrySethi(String input){
		int i = 0;
		Node node;
		if( input.charAt(i) == '('){

			node = MakeTreeFromParanthesis( i, input);


			// find the end of the parenthesis
			// i = endOfParanthesisIndex;

		}else{
			node = new Node( Node.Type.LEAF, null);
			node.nonterminal = input.charAt(i);
		}

		Node node2, node3;

		while( input.length() > i+1){
			i++;

			switch (input.charAt(i)) {

				case '*':  
					node2 = new Node( Node.Type.ASTERISK, null);
					node.addParent(node2);
					break;

				case '?':  
					node2 = new Node( Node.Type.Q_MARK, null);
					node.addParent(node2);
					break;

				case '+':  
					node2 = new Node( Node.Type.PLUS, null);
					node.addParent(node2);
					break;

				case '|':  
					node2 = new Node( Node.Type.OR, null);
					node.addParent(node2);
					break;

				case '(':  
					node3 = MakeTreeFromParanthesis( i, input);
					
					node2 = new Node( Node.Type.CONCAT, null);
					node.addParent(node2);
					node3.addParent(node2);
					break;

				default:
					node3 = new Node( Node.Type.LEAF, null);
					node3.nonterminal = input.charAt(i);
					
					node2 = new Node( Node.Type.CONCAT, null);
					node.addParent(node2);
					node3.addParent(node2);
					break;
	        }
	        node = node2;
	    }
		
		return node;

		
	}

	public static Node MakeTreeFromParanthesis( int startIndex, String input){
		return null;
	}

}
