import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import lejos.nxt.*;
import lejos.robotics.navigation.*;

public class Explorer {
  public static void main(String[] args) {
    Robot robo = new Robot();
    Node A = new Node("A", 0, 0);
    boolean atEnd = false;
    
    Stack<Node> stack = new Stack<Node>();
    stack.push(A);

    int white = 45;
    double angle = 90.0;
    
    ArrayList<Node> toExplore;
    ArrayList<Node> explored;

    Node prev = A;
    int xpos = 0;
    int ypos = 0;
    boolean yinc = true;
    int nodeName = 0;

    while(atEnd && (toExplore.length == explored.length)) {
      // variables for DFS
      nodeName++;
      if (yinc) 
	ypos += 2; 
      else 
        xpos += 2;

      boolean rDetect, lDetect = false;
      double counter = 0.0;
      double[] lightArr = robo.getLightValue();

      double r = lightArr[0];
      double l = lightArr[1];

      if(l >= white && r >= white) {
        robo.moveForward(2.0);
      } else if (l <= white && r >= white) {
        Node B = new Node(nodeName.toString(), xpos, ypos);
        B.pred = A;
        stack.add(B);
	prev = B;
        yinc = !yinc;
        robo.makeRotate(90.0);
        /* with adjusting included
        while(counter < angle) {
          robo.makeRotate(5.0); 
	  if(r <= white) rDetect = true;
	  counter++;   
	}
        */
      } else if (l >= white && r <= white) {
	robo.makeRotate(-90.0);
      } else if (l <= white && r <= white) {
	robo.makeRotate(90.0);
      } else {
  	// anything else
      }
   }

  }
}
