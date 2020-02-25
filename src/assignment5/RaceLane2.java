package assignment5;

//--------------------------------------------
//Assignment5
//Hossein Sabouri 29575669
//For COMP 248 SECTION EE- FALL 2017
//Driver RaceLane2
//--------------------------------------------
import java.util.Scanner;

public class RaceLane2 {

  public static void main(String[] args) {

    // create the cars
    Scanner keyboard = new Scanner(System.in);
    System.out.println("How many cars are going to race:");
    int n = keyboard.nextInt();
    Car[] carsArray = new Car[n];

    System.out.println("How many laps:");
    int laps = keyboard.nextInt();

    for (int i = 0; i < n; i++) {
      System.out.println("Please enter the model of car" + i);
      String model = keyboard.next();
      System.out.println("Please enter the max speed of the car" + i);
      int maxSpeed;
      do {
        maxSpeed = keyboard.nextInt();
        if (maxSpeed < 2 || maxSpeed > 7)
          System.out.println("Speed should be between 2 and 7");

      } while (maxSpeed < 2 || maxSpeed > 7);
      Car car = new Car(model, maxSpeed);
      carsArray[i] = car;
    }

    // start the race until each car reach max speed
    // accelerate each car
    // move the car

    // using boolean to know when the winners arrived.
    Car[] winners = new Car[3];
    boolean isFirstCarFinished = false;
    boolean isSecondCarFinished = false;
    boolean isThirdCarFinished = false;
    //to know number of cars that passed the first lap. this is to know when to print the number of crashed car.
    int carsPassedHundred = 0;

    // beginning of the race.
    do {
      Car.numberOfCrashedCar = 0;
      //this is to move the cars.
      for (int i = 0; i < carsArray.length; i++) {
        System.out.println(carsArray[i]);
        if (carsArray[i].isCrashed()) {
          //cars that crashed no need to move.
          continue;
        }
        // if the car is not finished, keep racing
        if (carsArray[i].getLocation() <= (laps * 100)) {
          carsArray[i].accelerate();
          carsArray[i].move();

          // update if the car is crash-able and passed the first lap
          if (carsArray[i].isCarsIsCrashable() == false) {
            if (carsArray[i].getLocation() > 100) {
              carsPassedHundred += 1;
              carsArray[i].setCarsIsCrashable(true);
            }
          }
        }

        //When the cars finish the laps save the winners.
        if (carsArray[i].getLocation() > (laps * 100) && !carsArray[i].isStopped()) {
          carsArray[i].stop();
          n -= 1;
          if (isFirstCarFinished == false) {
            winners[0] = carsArray[i];
            isFirstCarFinished = true;
          } else if (isSecondCarFinished == false) {
            winners[1] = carsArray[i];
            isSecondCarFinished = true;
          } else if (isThirdCarFinished == false) {
            winners[2] = carsArray[i];
            isThirdCarFinished = true;
          }
        }
      }

      // verify if there is a crash after the cars have moved.
      for (int i = 0; i < carsArray.length; i++) {

        if (carsArray[i].isCrashed()) {
          continue;
        }

        int samePosition = 1;
        int secondCarposition = 0;
        int thirdCarposition = 0;

        if (carsArray[i].isCarsIsCrashable())
          for (int j = i + 1; j < carsArray.length && samePosition < 3; j++)
            if (carsArray[j].isCarsIsCrashable()
                  && !carsArray[j].isCrashed()
                  && carsArray[i].getLocation() % 100 == carsArray[j].getLocation() % 100) {
              samePosition++;
              // save the other cars that have the same position place
              if (secondCarposition == 0)
                secondCarposition = j;
              else if (thirdCarposition == 0)
                thirdCarposition = j;
            }

        //if three cars are at the same position there is crash. Remove them from the race and stop the cars.
        if (samePosition == 3) {
          n -= 3;

          // there is a crash, let's remove the cars
          carsArray[i].setCrashed(true);
          carsArray[secondCarposition].setCrashed(true);
          carsArray[thirdCarposition].setCrashed(true);

          carsArray[i].stop();
          carsArray[secondCarposition].stop();
          carsArray[thirdCarposition].stop();

          System.out.println("The following cars got crashed:");
          System.out.println(carsArray[i]);
          System.out.println(carsArray[secondCarposition]);
          System.out.println(carsArray[thirdCarposition]);
          Car.numberOfCrashedCar += 3;

          //make everyone stop
          for (i = 0; i < carsArray.length; i++) {
            carsArray[i].stop();
          }

          break;
        }
      }

      if (carsPassedHundred >= 3) {
        System.out.println("Num of Crashed cars: " + Car.numberOfCrashedCar);
        System.out.println("Number of current cars in the race: " + n);
      }

    } while (raceisFinished(carsArray, winners, laps) != true);
    //When the race is finished, if there is at least one winner, show it.
    if (isFirstCarFinished)
      System.out.println("The Winners are: ");
    for (int i = 0; i < winners.length; i++) {
      if (winners[i] != null) {
        System.out.println(winners[i]);
      }
    }
  }

  //To verify of all the cars are stopped.
  public static boolean allCarStopped(Car[] cars) {
    for (int i = 0; i < cars.length; ++i) {
      if (cars[i].getCurrentSpeed() != 0) {
        return false;
      }
    }
    return true;
  }

  // To verify if all the cars finished.
  public static boolean raceisFinished(Car[] cars, Car[] winners, int laps) {
    // if we have 3 winners
    boolean weHave3Winners = true;
    for (int i = 0; i < winners.length; ++i) {
      if (winners[i] == null) {
        weHave3Winners = false;
        break;
      }
    }
    if (weHave3Winners) {
      return true;
    }

    // if all cars crashed
    boolean isAllCarCrashed = true;
    for (int i = 0; i < cars.length; ++i) {
      if (cars[i].isCrashed() != true)
        isAllCarCrashed = false;
    }
    if (isAllCarCrashed)
      return true;

    // verify if all the cars passed the finish line
    for (int i = 0; i < cars.length; ++i) {
      if (cars[i].isCrashed() != true && cars[i].getLocation() <= 100 * laps)
        return false;
    }
    return true;
  }

}
