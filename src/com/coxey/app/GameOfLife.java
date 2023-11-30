package com.coxey.app;

import java.util.Random;

public class GameOfLife {
    /**
     * row - поле, отвечающее за количество строк
     * col - поле, отвечающее за количество столбцов
     * initialNumberOfCells - поле, отвечающее за начальное количество живых клеток
     * gameBoard - двумерный массив символов, где '.' - мертвая клетка
     *                                            '*' - живая клетка
     */
    private int row;
    private int col;
    private int initialNumberOfCells;
    private char[][] gameBoard;

    /**
     * Конструктор инициализирует поля, отвечающие за размер поля
     * и двумерный массив
     */
    public GameOfLife(int row, int col){
        this.row = row;
        this.col = col;
        gameBoard = new char[row][col];
    }

    /**
     * Метод создает живые клетки на игровом поле
     * В начале поле заполняется мертвыми клетками '.'
     * Затем случайным образом в разные места двумерного массива помещаются живые клетки '*'
     */
    public void createCells(int initialNumberOfCells){
        for(int i = 0; i<row; i++){
            for(int j = 0; j<col; j++){
                gameBoard[i][j] = '.';
            }
        }
        Random rn = new Random();

        for(int i = 0; i<initialNumberOfCells; i++){
            int randomRow = rn.nextInt(row - 1);
            int randomCol = rn.nextInt(col - 1);
            gameBoard[randomRow][randomCol] = '*';
        }

    }

    // Метод отображает на экран игровое поле
    public void printBoard(){
        for(int i = 0; i<row;i++){
            for (int j = 0; j<col;j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Метод обновляет игровое поле для новой генерации
     * В методе присутствуют проверки для завершения игры, если выполнится хотя бы одно требование:
     * 1) На поле не останется ни одной «живой» клетки;
     * 2)Конфигурация на очередном шаге в точности (без сдвигов и поворотов) повторит себя
     *      же на одном из более ранних шагов (складывается периодическая конфигурация)
     */
    public boolean updateGameBoard(){
        char[][] newGeneratingGameBoard = new char[row][col];
        int counterDiedCells = 0;
        int counterOfIdenticalValues = 0;
        int count = 0;

        for(int i = 0; i< gameBoard.length; i++){
            for(int j = 0; j<gameBoard[i].length;j++){
                count = countNeighbours(i,j);
                if(count < 2){
                    newGeneratingGameBoard[i][j] = '.';
                }
                if(count == 2 || count == 3){
                    newGeneratingGameBoard[i][j] = '*';
                }
                if(count > 3){
                    newGeneratingGameBoard[i][j] = '.';
                }
            }
        }

        for(int i = 0; i< gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if(gameBoard[i][j] == newGeneratingGameBoard[i][j]){
                    counterOfIdenticalValues++;
                }
            }
        }
        if(counterOfIdenticalValues == row*col){
            return false;
        }
        gameBoard = newGeneratingGameBoard;

        for(int i = 0; i< gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if(gameBoard[i][j]=='.'){
                    counterDiedCells++;
                }
            }
        }
        if(counterDiedCells == row*col){
            return false;
        }
        return true;
    }

    // Приватный метод для подсчета количества соседей у точки
    private int countNeighbours(int rowCell, int colCell){
        int count = 0;

        for (int i = rowCell - 1; i <= rowCell + 1; i++){
            for(int j = colCell - 1; j <= colCell+1; j++){
                if( i>=0 && i<gameBoard.length && j>=0 && j<gameBoard[i].length && (i != rowCell || j != colCell) ){
                    if(gameBoard[i][j] == '*'){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}