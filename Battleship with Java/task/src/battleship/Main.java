package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        Scanner scanner = new Scanner(System.in);
        System.out.println(battlefield.toString());
        System.out.println("Enter the coordinates of the ship:");
        String cor1 = scanner.next();
        String cor2 = scanner.next();

        battlefield.placeShipOnField(cor1, cor2);
    }
}
