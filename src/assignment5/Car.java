package assignment5;

public class Car {
  private String model;
  private int location;
  private int currentSpeed;
  private boolean movingForward;
  private int maxSpeed;
  public static int numberOfCrashedCar = 0;
  private boolean CarsIsCrashable = false;
  private boolean isStopped = false;
  private boolean isCrashed = false;

  //default constructor
  public Car() {
  }

  //constructor part1
  public Car(String model, int location, int maxSpeed) {
    this.model = model;
    this.location = location;
    this.maxSpeed = maxSpeed;
    this.movingForward = true;
    this.currentSpeed = 0;
  }

  //constructor part2
  public Car(String model, int maxSpeed) {
    this.model = model;
    this.location = 0;
    this.maxSpeed = maxSpeed;
    this.movingForward = true;
    this.currentSpeed = 0;
  }

  public String getModel() {
    return model;
  }

  public int getMaxSpeed() {
    return maxSpeed;
  }

  public boolean getDirection() {
    return movingForward;
  }

  public int getCurrentSpeed() {
    return currentSpeed;
  }

  public int getLocation() {
    return location;
  }

  public void go() {
    this.currentSpeed = this.maxSpeed;
  }

  public void stop() {
    this.currentSpeed = 0;
    this.isStopped = true;
  }

  public void turnArond() {
    this.movingForward = !movingForward;
  }

  public void move() {
    if (movingForward == true)
      this.location = location + this.currentSpeed;
    else
      this.location = location - this.currentSpeed;
  }

  public String toString() {
    String face = null;

    if (currentSpeed == 0) {
      if (movingForward == true) 
        face = "forward";
      else
        face = "backward";
      return (model + ": Located at " + location + ", facing " + face + " not moving");
    }
    else  {
      if (movingForward == true) 
        face = "forward";
      else
        face = "backward";
      return (model + ": Located at " + location + ", facing " + face + " and moving at " + currentSpeed + " speed.");
    }
  }

  public void accelerate() {
    if (currentSpeed < maxSpeed)
      currentSpeed = currentSpeed + 1;
  }

  public void brake() {
    if (currentSpeed > 0)
      currentSpeed = currentSpeed - 1;
  }

  public boolean isCarsIsCrashable() {
    return CarsIsCrashable;
  }

  public void setCarsIsCrashable(boolean carsIsCrashable) {
    CarsIsCrashable = carsIsCrashable;
  }

  public boolean isStopped() {
    return this.currentSpeed == 0;
  }

  public boolean isCrashed() {
    return isCrashed;
  }

  public void setCrashed(boolean isCrashed) {
    this.isCrashed = isCrashed;
  }
}