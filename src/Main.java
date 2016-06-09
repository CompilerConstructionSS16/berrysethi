
public class Main {

	public static void main(String[] args) {
			Parse( StringToolkit.Orify("(ab)*|e+fg?")).Debug("");
			//Parse( StringToolkit.Orify("((ab)*)")).Debug("");
			//Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
			//Parse( StringToolkit.Orify("(ab)?|(e)*(dg+h)")).Debug("");
			//Parse( StringToolkit.Orify("ab|xe")).Debug("");
		
	}

	// Used for '.', '|' 
	public static Node Concatify(Node node1, Node node2, Node.Type type){
		if( node1 == null) return node2;
		if( node2 == null) return node1;
		Node nodeRoot = new Node( type);
		node1.addParent( nodeRoot);
		node2.addParent( nodeRoot);
		return nodeRoot;
	}

	// Used for '*', '+', '?'
	public static Node Parentify(Node node, Node.Type type){
		if( node == null) return null;
		Node nodeRoot = new Node( type);
		node.addParent( nodeRoot);
		return nodeRoot;
	}

	// This function returns the Node from last of nonterminal in the string
	// or calculates the parse tree of the last parenthesis in the string
	// it depends on what is on the end
	public static Node LastNode(String input){
		if( input.length() == 0) return null;

		char c = input.charAt(input.length()-1);

		// When it is end of a parenthesis find the start and calculate tree inside. 
		if( c == ')'){
			int indexOpen = StringToolkit.ParenthesisOpeningIndex(input);
			Node node = Parse(input.substring(indexOpen+1, input.length()-1));
			node.firstCharIndex = indexOpen;
			return node;
		}else{
			Node node = new Node( Node.Type.LEAF);
			node.firstCharIndex = input.length()-1;
			node.nonterminal = c;
			return node;
		}

	}
	public static Node Parse(String input){
		int z = input.length()-1;
		Node node;

		if( z < 0) return null;

		// input is just one nonterminal
		if( z == 0){
			node = new Node( Node.Type.LEAF);
			node.firstCharIndex = 0;
			node.nonterminal = input.charAt(0);
			return node;
		}
		System.out.println(input);

		switch (input.charAt(z)) {

			/* find the last nonterminal or parenthesis not including the 
			end character and parent it with one of '*','?','+' nodes  */
			case '*':  	node = Parentify( LastNode( input.substring(0, z)), Node.Type.ASTERISK); break;
			case '?':  	node = Parentify( LastNode( input.substring(0, z)), Node.Type.Q_MARK); break;
			case '+':  	node = Parentify( LastNode( input.substring(0, z)), Node.Type.PLUS); break;

			/* find the last nonterminal or parenthesis anywhere*/
			default:	node = LastNode( input); break;
        }

        if( node == null) return null;

        // firstCharIndex of nonterminal is its index in the string 
        // firstCharIndex of parenthesis is where '(' is at
        int i = node.firstCharIndex;

        if( i == 0){ 
        	return node;
        }else if( i > 0 && input.charAt(i-1) == '|'){
			return Concatify( Parse(input.substring(0, i-1)), node, Node.Type.OR);
		}else{
			return Concatify( Parse(input.substring(0, i)), node, Node.Type.CONCAT);
		}
	}

}
