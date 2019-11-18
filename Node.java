import java.util.*;

class Node {
    public String name; //name of node
    public Node pred; //predecessor
    public Boolean explored = false; //whether node has been explored
    public ArrayList<Node> children = new ArrayList<>(); //stores children
    public int xPos, yPos; //coordinates of the node in cartesian space
<<<<<<< HEAD
    public String type; // type of intersection either: corner, crossroads, junction
    public int exploredCounter; // this represents node connections that have been explored, not necessarily children
=======
    public int childrenCount = 0;
    public int exploredChildrenCount = 0;
>>>>>>> 74cec91e96a2a9e7061dd3a9c4281835ba519872

    //constructor
    public Node(String name, int xPos, int yPos, String type, int exploredCounter) {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        this.explored = false;
        this.children = new ArrayList<Node>();
        this.type = type;
        this.exploredCounter = exploredCounter;
    }

    public String getType() {
      return this.type;
    }

    public String toString(){
        return this.name + ", " + this.xPos + ", " + this.yPos;
    }

    public void addChild(Node node) {
        this.children.add(node);
        node.pred=this;
    }

    public Node getChild() {
	    for(int i = 0; i < children.size(); i++) {
	        if(!children.get(i).explored) {
	    	    return children.get(i);
	        }
	    }
	    return null;
    }

    public Boolean equals(Node n) {
	    return (n.name == this.name) ? true : false;
    }

    public void setType(String type) {
      this.type = type;
    }

    public int getExploredCount() {
      return this.exploredCounter;
    }

    public void setExploredCount(int exploredCounter) {
      this.exploredCounter = exploredCounter;
    }
}
