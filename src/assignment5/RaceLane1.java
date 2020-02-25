package assignment5;

import java.util.Scanner;

public class RaceLane1 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Please enter the model of the first car:");
    String firstCarModel = keyboard.next();

    System.out.println("Please enter the location of the first car:");
    int firstCarLocation = keyboard.nextInt();

    System.out.println("Please enter the speed of the first car:");
    int firstCarMaxSpeed = keyboard.nextInt();

    Car firstCar = new Car(firstCarModel, firstCarLocation, firstCarMaxSpeed);

    System.out.println("Please enter the model of the second car:");

    String secondCarModel = keyboard.next();

    System.out.println("Please enter the location of the second car:");
    int secondCarLocation = keyboard.nextInt();

    System.out.println("Please enter the speed of the second car:");
    int secondCarMaxSpeed = keyboard.nextInt();

    Car secondCar = new Car(secondCarModel, secondCarLocation, secondCarMaxSpeed);

    firstCar.toString();
    System.out.println(firstCar);

    secondCar.toString();
    System.out.println(secondCar);

    Car leftCar = null;
    Car rightCar = null;
    // to verify which car is on the left side.
    if (firstCar.getLocation() < secondCar.getLocation()) {
      System.out
            .println("Car " + firstCar.getModel() + " is on the left side of the car " + secondCar.getModel() + ".");
      secondCar.turnArond();
      firstCar.toString();
      secondCar.toString();
      System.out.println();
      System.out.println(firstCar);
      System.out.println(secondCar);
      leftCar = firstCar;
      rightCar = secondCar;

    } else {
      System.out
            .println("Car " + firstCar.getModel() + " is on the right side of the car " + secondCar.getModel() + ".");
      firstCar.turnArond();
      firstCar.toString();
      secondCar.toString();
      System.out.println();
      System.out.println(firstCar);
      System.out.println(secondCar);
      leftCar = secondCar;
      rightCar = firstCar;
    }
    firstCar.go();
    secondCar.go();

    //print position of the cars.
    firstCar.toString();
    secondCar.toString();
    System.out.println();
    System.out.println(firstCar);
    System.out.println(secondCar);

    //collide
    firstCar.move();
    secondCar.move();
    //While the left car is still on the left there is no crash. 
    while (leftCar.getLocation() < rightCar.getLocation()) {
      firstCar.move();
      secondCar.move();
      System.out.println();
      System.out.println(firstCar);
      System.out.println(secondCar);
    }
    System.out.print("Boom!!");
  }
}