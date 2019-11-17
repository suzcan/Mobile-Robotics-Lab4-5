import lejos.nxt.*;
import lejos.robotics.navigation.*;

class Robot {
  private DifferentialPilot pilot;
  private Navigator navbot;
  private LightSensor rightLight;
  private LightSensor leftLight;
  private UltrasonicSensor sonar;
  public Pose pose;

  public Robot() {
    // check connections are correct!
    //set up the robot
    pilot = new DifferentialPilot(5.0, 16.2, Motor.A, Motor.C);
    //pilot.setRotateSpeed(0.5);
    //set up the navigator
    navbot = new Navigator(pilot);
    //set up light sensor
    rightLight = new LightSensor(SensorPort.S1);
    leftLight = new LightSensor(SensorPort.S4);
    //set up sonar sensor
    sonar = new UltrasonicSensor(SensorPort.S3);
    
    pose = new Pose();
  }

  public void moveForward(double distance) {
    this.pilot.travel(distance);
  }
  
  public void moveTo(Node n) {
    this.navbot.goTo(n.xPos, n.yPos);
    this.navbot.waitForStop();
  }

  public double[] getLightValue() {
	  return new double[] {this.rightLight.getLightValue(), this.leftLight.getLightValue()};
  }

  public double getSonarDistance() {
	  return this.sonar.getDistance();
  }

  public void makeRotate(double angle) {
	  this.pilot.rotate(angle);
  }

  public float[] getPose() {
    return new float[] = {pose.getX, pose.getY};
  }
  
}
