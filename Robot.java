import lejos.nxt.*;
import lejos.robotics.navigation.*;

class Robot {
  private DifferentialPilot pilot;
  private Navigator navbot;
  private LightSensor rightLight;
  private LightSensor leftLight;
  private UltrasonicSensor sonar;

  public Robot() {
    // check connections are correct!
    //set up the robot
    pilot = new DifferentialPilot(5.0, 17.5, Motor.A, Motor.C);
    //set up the navigator
    navbot = new Navigator(pilot);
    //set up light sensor
    rightLight = new LightSensor(SensorPort.S1);
    leftLight = new LightSensor(SensorPort.S4);
    //set up sonar sensor
    sonar = new UltrasonicSensor(SensorPort.S3);
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
  
}
