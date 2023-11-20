package Game_Tic_Tac_Toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    // двумерный массив кнопок - поле игры
    public JButton[][] board;
    // переменная для проверки чья очередь выполнить ход
    private boolean player1Turn1 = true;
    public int player1Turn;
    public int n = 3;
    public int i,j;

    // Создаем потоки для игроков
    private Thread player1, player2;
    // Создаем объект для синхронизации потоков
    private Object lock = new Object();
    // Создаем переменную для хранения текущего игрока
    private int currentPlayer = 1;

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // использование GridLayout
        setLayout(new GridLayout(n, n));
        board = new JButton[n][n];
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++)
            {
                board[i][j] = new JButton("");
                // к каждой кнопке добавляем слушатель событий
                board[i][j].addActionListener(new ButtonListener());
                add(board[i][j]);
            }
        }
        setVisible(true);
    }

    // реализация обработчика события в отдельном классе
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton buttonClicked = (JButton) e.getSource();
            // если клетка пустая, т.е. выполняется ход
            if (buttonClicked.getText().equals(""))
            {
                    // выполняет ход первый игрок
                    if (currentPlayer == 1)
                    {
                        player1 = new Thread(new TicTacToe.Player(1));
                        buttonClicked.setText("X");
                        //currentPlayer = 1;
                        player1.start();
                    }
                    // выполняет ход второй игрок
                    else
                    {
                        player2 = new Thread(new TicTacToe.Player(2));
                        buttonClicked.setText("O");
                        //currentPlayer = 2;
                        player2.start();
                    }
                    // проверка есть ли победитель
                    if (checkForWin())
                    {
                        JOptionPane.showMessageDialog(null, (player1Turn1 ? "X" : "O") + " выйграл!");
                        System.exit(0);
                    }
                    // проверка на ничью
                    else if (checkForTie())
                    {
                        JOptionPane.showMessageDialog(null, "Ничья!");
                        System.exit(0);
                    }
                     //смена хода
//                    else
//                    {
//                    }
            }

//            //Блокируем другого игрока
//            synchronized (lock) {
//                // Устанавливаем значение на кнопке
//                buttonClicked.setText(currentPlayer == 1 ? "X" : "O");
//                // Переключаем текущего игрока
//                currentPlayer = currentPlayer == 1 ? 2 : 1;
//                // Оповещаем другого игрока
//                lock.notify();
//            }
            // Создаем потоки для игроков
//            player1 = new Thread(new TicTacToe.Player(1));
//            player2 = new Thread(new TicTacToe.Player(2));
            // Запускаем потоки
//            player1.start();
//            player2.start();
        }
    }

    // Класс для игрока
    private class Player implements Runnable {
        // Номер игрока
        private int playerNumber;

        public Player(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public void run() {
            while (true) {
                // Блокируем другого игрока
                synchronized (lock) {
                    // Если текущий игрок не является этим игроком, то ждем
                    while (currentPlayer != playerNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Оповещаем игрока о его ходе
                    System.out.println("Игрок " + playerNumber + " делает ход");
                    // Ждем, пока игрок не сделает ход
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Переключаем текущего игрока
                    currentPlayer = currentPlayer == 1 ? 2 : 1;
                    // Оповещаем другого игрока
                    lock.notify();
                }
            }
        }
    }


    // проверка наличия на поле выигрышной комбинации
    private boolean checkForWin()
    {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // выигрышная комбинация
    private boolean checkRowCol(String s1, String s2, String s3)
    {
        return (s1.equals(s2) && s2.equals(s3) && !s1.equals(""));
    }


    // проверка наличия выигрышной комбинации по строкам
    private boolean checkRowsForWin()
    {
        for (i = 0; i < n; i++)
        {
            if (checkRowCol(board[i][0].getText(), board[i][1].getText(), board[i][2].getText()))
            {
                return true;
            }
        }
        return false;
    }

    // проверка наличия выигрышной комбинации по столбцам
    private boolean checkColumnsForWin()
    {
        for (i = 0; i < n; i++)
        {
            if (checkRowCol(board[0][i].getText(), board[1][i].getText(), board[2][i].getText()))
            {
                return true;
            }
        }
        return false;
    }

    // проверка наличия выигрышной комбинации по диагоналям
    private boolean checkDiagonalsForWin()
    {
        return (checkRowCol(board[0][0].getText(), board[1][1].getText(), board[2][2].getText()) || checkRowCol(board[0][2].getText(), board[1][1].getText(), board[2][0].getText()));
    }

    // проверка на ничью
    private boolean checkForTie()
    {
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++)
            {
                if (board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
//        TicTacToe game = new TicTacToe();
    }
}

