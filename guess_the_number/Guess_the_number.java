import java.util.Random;
import java.util.Scanner;

class Game {
    public int number;
    public int inputNumber;
    public int noOfGuesses = 0;

    public int getNoOfGuesses() {
        return noOfGuesses;
    }

    public void setNoOfGuesses(int noOfGuesses) {
        this.noOfGuesses = noOfGuesses;
    }

    Game() {
        Random rand = new Random();
        this.number = rand.nextInt(100);
    }

    void takeUserInput() {
        System.out.println("Guess the number:");
        Scanner sc= new Scanner(System.in);
        sc.close();
        inputNumber = sc.nextInt();
    }

    boolean isCorrectNumber() {
        noOfGuesses++;
        if (inputNumber == number) {
            System.out.println("Yes, you guessed right\nYou guessed in " + noOfGuesses + " attempts. The number is " + number);
            return true;
        } else if (inputNumber < number) {
            System.out.println(inputNumber + " Too Low...");
        } else if (inputNumber > number) {
            System.out.println(inputNumber + " Too High...");
        }
        return false;
    }
}

public class Guess_the_number {
    public static void main(String[] args) {
        Game g = new Game();
        boolean b = false;
        while (!b) {
            g.takeUserInput();
            b = g.isCorrectNumber();
        }
    }
}