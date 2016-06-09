import java.util.ArrayList;


public class Node {
	public enum Type{
		CONCAT, OR, Q_MARK, ASTERISK, PLUS, LEAF
	}
	public static int idCounter = 0;
	
	public Node parent;
	public ArrayList<Node> children, first, last, next;
	public boolean empty;
	public Type type;
	public int id;
	public char nonterminal;
	
	public Node( Type type, Node parent){
		this.type = type;
		this.parent = parent;
		if( type != Type.LEAF){
			id = -1;
		}else{
			id = idCounter;
			idCounter++;
		}
		children = new ArrayList<Node>();
		first = new ArrayList<Node>();
		last = new ArrayList<Node>();
		next = new ArrayList<Node>();
		
	}

	public void addParent( Node parent){
		this.parent = parent;
		parent.children.add( this);
	}

	public void Debug(){
		if( type == Type.LEAF){
			System.out.println(nonterminal);
		}else{
			System.out.println(type);
		}

		if( children.size() > 0){

			System.out.println("{");
		}
		for( int i = 0; i < children.size(); i++){
			children.get(i).Debug();
		}

		if( children.size() > 0){

			System.out.println("}");
		}

	}
	
}
