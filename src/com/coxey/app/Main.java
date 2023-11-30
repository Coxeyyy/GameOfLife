package com.coxey.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * Как играть в игру "Жизнь"
         * Запустить игру с помощью комбинации клавиш Shift+F10
         * Ввести размер поля и количество живых клеток в формате: X Y N
         * Наслаждаться
         */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter field size and number of live cells: ");
        int sizeX = scanner.nextInt();
        int sizeY = scanner.nextInt();
        int numberOfCell = scanner.nextInt();

        GameOfLife gameOfLife = new GameOfLife(sizeX,sizeY);
        // Initial Board
        System.out.println("Initial Board");
        gameOfLife.createCells(numberOfCell);
        gameOfLife.printBoard();

        boolean continueGame = true;
        while (continueGame){
            continueGame = gameOfLife.updateGameBoard();
            System.out.println("Updated Board");
            gameOfLife.printBoard();
            Thread.sleep(500);
        }
    }
}
