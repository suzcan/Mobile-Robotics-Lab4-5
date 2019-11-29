import lejos.nxt.*;
import lejos.robotics.navigation.*;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.geom.Point;

class Robot {
  private DifferentialPilot pilot;
  private Navigator navbot;
  private LightSensor rightLight;
  private LightSensor leftLight;
  private UltrasonicSensor sonar;
  public Pose pose;
  PoseProvider pp;

  public Robot() {
    // check connections are correct!
    //set up the robot
    pilot = new DifferentialPilot(5.0, 16.2, Motor.A, Motor.C);
    //pilot.setRotateSpeed(0.5);
    //set up the navigator
    //set up light sensor
    rightLight = new LightSensor(SensorPort.S1);
    leftLight = new LightSensor(SensorPort.S4);
    //set up sonar sensor
    sonar = new UltrasonicSensor(SensorPort.S3);
    navbot = new Navigator(pilot, new OdometryPoseProvider(pilot));
    pose = navbot.getPoseProvider().getPose();

    pilot.setTravelSpeed(6.0);
  }

  public void moveForward() {
    this.pilot.forward();
  }

  public void moveForward(double distance) {
    this.pilot.travel(distance);
  }

  public void moveBackward(double distance) {
    this.pilot.travel(distance*-1.0, true);
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
    return new float[] {pose.getX(), pose.getY()};
  }

  public float getX() {
    Point point = navbot.getPoseProvider().getPose().getLocation();
    return (float)point.getX();
  }

  public float getY() {
    Point point = navbot.getPoseProvider().getPose().getLocation();
    return (float)point.getY();
  }

  public void setHeading(float heading) {
    Pose pose = navbot.getPoseProvider().getPose();
    pose.setHeading(heading);
  }

}
