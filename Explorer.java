import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import lejos.nxt.*;
import lejos.robotics.navigation.*;

public class Explorer {
  
  public static void main(String[] args) {
    Robot robo = new Robot();
    int nodeCounter = 1;
    Node startingPoint = new Node("1", 0, 0);
    startingPoint.childrenCount = 1;
    startingPoint.pred.exploredChildrenCount++;

    Stack<Node> stack = new Stack<Node>();
    stack.push(startingPoint);

    ArrayList<Node> toExplore = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();
    toExplore.add(startingPoint);
    explored.add(startingPoint);

    int darkColourThreshold = 45;
    double angle = 90.0;

    do {
      // variables for DFS
      double[] lightArr = robo.getLightValue();
      double r = lightArr[0];
      double l = lightArr[1];

      boolean rDetect, lDetect = false;
      double counter = 0.0;
      
      if(l >= darkColourThreshold && r >= darkColourThreshold) {
        // move forward on straight line
        robo.moveForward(2.0);
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

