import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import lejos.nxt.*;
import lejos.robotics.navigation.*;

public class Explorer {
  public static void main(String[] args) {
    Robot robo = new Robot();
    Node A = new Node("A", 0, 0, "corner");
    boolean atEnd = false;

    Stack<Node> stack = new Stack<Node>();
    stack.push(A);

    int white = 45;
    double angle = 90.0;

    ArrayList<Node> toExplore = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();

    Node prev = A;
    int xpos = 0;
    int ypos = 0;
    boolean yinc = true;
    int nodeName = 0;

    while(atEnd && (toExplore.length == explored.length)) {
      // variables for DFS
      nodeName++;
      if (yinc) {
	     ypos += 2;
     } else {
        xpos += 2;
      }

      boolean rDetect, lDetect = false;
      double counter = 0.0;

      // ultrasonic sensor for obstacles

      double[] lightArr = robo.getLightValue();
      double r = lightArr[0];
      double l = lightArr[1];

      if(l >= white && r >= white) {
        robo.moveForward(2.0);
      } else if (l <= white && r >= white) {
        // TODO
        /* with adjusting included
        while(counter < angle / 5) {
          robo.makeRotate(5.0);
           if(r <= white)  {
            rDetect = true;
            SET TYPE OF NODE TO JUNCTION ELSE CORNER
            SET THEIR EXPLORED COUNTER TO 1
          }
            counter++;
           }
        */

        Node B = new Node(nodeName.toString(), xpos, ypos, "junction");
        B.pred = prev;
        stack.add(B);
	      prev = B;
        yinc = !yinc;
        robo.makeRotate(90.0);

      } else if (l >= white && r <= white) {
        // TODO
        // mirror previous if statement but with *-1 angles
        // junction behaviour
	       robo.makeRotate(90.0);
      } else if (l <= white && r <= white) {
        // £££££££££££££££££££££ USE NAVIGATOR CLASS TO GET CURRENT COORDINATES £££££££££££££££££££££
        // TODO
        // add crossroad
        // crossroad behaviour
        robo.makeRotate(-90.0);
      } else {
  	// anything else
      }
   }

  }
}
