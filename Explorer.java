import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import lejos.nxt.*;
import lejos.robotics.navigation.*;

public class Explorer {
  
  public static void main(String[] args) {
    Robot robo = new Robot();
<<<<<<< HEAD
    Node A = new Node("A", 0, 0, "corner");
    boolean atEnd = false;
=======
    int nodeCounter = 1;
    Node startingPoint = new Node("1", 0, 0);
    startingPoint.childrenCount = 1;
    startingPoint.pred.exploredChildrenCount++;
>>>>>>> 74cec91e96a2a9e7061dd3a9c4281835ba519872

    Stack<Node> stack = new Stack<Node>();
    stack.push(startingPoint);

<<<<<<< HEAD
    int white = 45;
    double angle = 90.0;

    ArrayList<Node> toExplore = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();
=======
    ArrayList<Node> toExplore = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();
    toExplore.add(startingPoint);
    explored.add(startingPoint);
>>>>>>> 74cec91e96a2a9e7061dd3a9c4281835ba519872

    int darkColourThreshold = 45;
    double angle = 90.0;

    do {
      // variables for DFS
<<<<<<< HEAD
      nodeName++;
      if (yinc) {
	     ypos += 2;
     } else {
        xpos += 2;
      }

      boolean rDetect, lDetect = false;
      double counter = 0.0;

      // ultrasonic sensor for obstacles

=======
>>>>>>> 74cec91e96a2a9e7061dd3a9c4281835ba519872
      double[] lightArr = robo.getLightValue();
      double r = lightArr[0];
      double l = lightArr[1];

      boolean rDetect, lDetect = false;
      double counter = 0.0;
      
      if(l >= darkColourThreshold && r >= darkColourThreshold) {
        // move forward on straight line
        robo.moveForward(2.0);
<<<<<<< HEAD
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
=======
      } else if (l <= darkColourThreshold && r >= darkColourThreshold) {
        // Left sensor on tape
        // TODO: adjust for sonar 
        int rotateCount = 0;
        while (r < darkColourThreshold) {
          rotateCount++;
          robo.makeRotate(1.0); // might be too small
          r = robo.getLightValue()[0]; // get new value
        }
        if(rotateCount <= 15) {
          // just adjust
          robo.makeRotate(-4.0);
        } else if (rotateCount >= 90) {
          // corner
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) { // not visited
            String name = Integer.toString(++nodeCounter);
            Node pred = stack.pop();

            Node newNode = new Node(name, x, y);
            pred.addChild(newNode);
            newNode.childrenCount = 1;
            newNode.pred = pred;
            newNode.pred.exploredChildrenCount++;
            stack.push(pred);
            stack.push(newNode);

            toExplore.add(newNode);
            explored.add(newNode);
            
            // adjust to continue journey
          } else {
            Node oldNode = toExplore.get(index);
            explored.add(oldNode);
            robo.makeRotate(180.0);
          }
        } else {
          // junction
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) {
            String name = Integer.toString(++nodeCounter);
            Node pred = stack.pop();

            Node newNode = new Node(name, x, y);

            newNode.childrenCount = 2;
            newNode.pred = pred;
            newNode.pred.exploredChildrenCount++;
            stack.push(newNode);

            // adjust to continue journey
            robo.makeRotate(-5.0);
            robo.moveForward(5.0);
          } else {
            Node oldNode = toExplore.get(index);
            explored.add(oldNode);
            robo.makeRotate(180.0);
          }
        }
      } else if (l >= darkColourThreshold && r <= darkColourThreshold) {
        // right sensor on tape
        // TODO: adjust for sonar 
        int rotateCount = 0;
        while (l < darkColourThreshold) {
          rotateCount++;
          robo.makeRotate(1.0); // might be too small
          l = robo.getLightValue()[1]; // get new value
        }
        if(rotateCount <= 15) {
          // just adjust
          robo.makeRotate(4.0);
        } else if (rotateCount >= 90) {
          // corner
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) {
            String name = Integer.toString(++nodeCounter);

            Node pred = stack.pop();

            Node newNode = new Node(name, x, y);
            pred.addChild(newNode);
            newNode.childrenCount = 1;
            newNode.pred = pred;
            newNode.pred.exploredChildrenCount++;
            stack.push(pred);
            stack.push(newNode);

            toExplore.add(newNode);
            explored.add(newNode);

            // adjust to continue journey
            robo.makeRotate(-5.0);
            robo.moveForward(5.0);
          } else {
            Node oldNode = toExplore.get(index);
            explored.add(oldNode);
            robo.makeRotate(180.0);
          }
        } else {
          // junction
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) {
             String name = Integer.toString(++nodeCounter);
             Node newNode = new Node(name, x, y);
            newNode.childrenCount = 2;
            newNode.pred = stack.peek();
            newNode.pred.exploredChildrenCount++;
            stack.push(newNode);
            // adjust to continue journey
            robo.makeRotate(-5.0);
            robo.moveForward(5.0);
          } else {
            Node oldNode = toExplore.get(index);
            explored.add(oldNode);
            robo.makeRotate(180.0);
          }        
        }
      } else if (l <= darkColourThreshold && r <= darkColourThreshold) {
        // both sensors on tape
        int rotateCount = 0;
        while (l < darkColourThreshold) {
          rotateCount++;
          robo.makeRotate(1.0); // might be too small
          l = robo.getLightValue()[1]; // get new value
        }
        if(rotateCount <= 45) {
          // crossroads
          String name = Integer.toString(++nodeCounter);
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) {
            String name = Integer.toString(++nodeCounter);

            Node pred = stack.pop();

            Node newNode = new Node(name, x, y);
            newNode.childrenCount = 3; // 4; inconsistent with other junction counts
            pred.addChild(newNode);
            newNode.pred = pred;
            newNode.pred.exploredChildrenCount++;
            stack.push(pred);
            stack.push(newNode);
          } else {

          }
        } else {
          // junction
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();

          int index = previouslyVisited(toExplore, x, y);

          if(index == -1) {
            String name = Integer.toString(++nodeCounter);
            Node pred = stack.pop();

            Node newNode = new Node(name, x, y);
            pred.addChild(newNode);
            newNode.childrenCount = 2;
            newNode.pred = pred;
            newNode.pred.exploredChildrenCount++;
            stack.push(pred);
            stack.push(newNode);
          } else {
            Node oldNode = toExplore.get(index);
            explored.add(oldNode);
            robo.makeRotate(180.0);
          }
        }
>>>>>>> 74cec91e96a2a9e7061dd3a9c4281835ba519872
      } else {
        System.out.println("Unknown case");
      }
    } while (toExplore.size() == explored.size());
  } 

  static int previouslyVisited(ArrayList<Node> toExplore, int x, int y) {
    for(int i = 0;  i < toExplore.size(); i++) {
      Node curr = toExplore.get(i);
      if(curr.xPos == x && curr.yPos == y) return i;
    }
    return -1;
  }
}

