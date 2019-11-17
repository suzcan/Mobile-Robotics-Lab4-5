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
    
    Stack<Node> stack = new Stack<Node>();
    stack.push(startingPoint);

    ArrayList<Node> toExplore = new ArrayList<Node>();
    ArrayList<Node> explored = new ArrayList<Node>();
    
    int darkColourThreshold = 45;
    double angle = 90.0;
    
    while(toExplore.size() == explored.size()) {
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
          String name = Integer.toString(++nodeCounter);
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();
          
          Node newNode = new Node(name, x, y);
          newNode.childrenCount = 1;
          newNode.pred = stack.peek();
          newNode.pred.exploredChildrenCounter++;
          stack.push(newNode);
        } else {
          // junction
          String name = Integer.toString(++nodeCounter);
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();
          
          Node newNode = new Node(name, x, y);
          newNode.childrenCount = 2;
          newNode.pred = stack.peek();
          newNode.pred.exploredChildrenCounter++;
          stack.push(newNode);
        }
      } else if (l >= darkColourThreshold && r <= darkColourThreshold) {
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
          String name = Integer.toString(++nodeCounter);
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();
          
          Node newNode = new Node(name, x, y);
          newNode.childrenCount = 1;
          newNode.pred = stack.peek();
          newNode.pred.exploredChildrenCounter++;
          stack.push(newNode);
        } else {
          // junction
          String name = Integer.toString(++nodeCounter);
          int x = (int) robo.pose.getX();
          int y = (int) robo.pose.getY();
          
          Node newNode = new Node(name, x, y);
          newNode.childrenCount = 2;
          newNode.pred = stack.peek();
          newNode.pred.exploredChildrenCounter++;
          stack.push(newNode);
        }
      } else if (l <= darkColourThreshold && r <= darkColourThreshold) {
        String name = Integer.toString(++nodeCounter);
        int x = (int) robo.pose.getX();
        int y = (int) robo.pose.getY();
        Node newNode = new Node(name, x, y);
        newNode.childrenCount = 4;
        newNode.pred = stack.peek();
        newNode.pred.exploredChildrenCount++;
        stack.push(newNode);
      } else {
        System.out.println("Unknown case");
      }
    }
  }
}
