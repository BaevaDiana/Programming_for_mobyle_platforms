//
// package Game_Tic_Tac_Toe;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class TicTacToe extends JFrame {
//    // двумерный массив кнопок - поле игры
//    public JButton[][] board;
//    public int n = 3;
//    public int i,j;
//    // переменная для хранения текущего игрока
//    private int currentPlayer = 1;
//    // потоки для игроков
//    private Thread player1, player2;
//    // объект для синхронизации потоков
//    private Object lock = new Object();
//
//
//    public TicTacToe() {
//        super("Tic Tac Toe");
//        setSize(500, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // использование GridLayout
//        setLayout(new GridLayout(n, n));
//        board = new JButton[n][n];
//        for (i = 0; i < n; i++) {
//            for (j = 0; j < n; j++) {
//                board[i][j] = new JButton("");
//                // к каждой кнопке добавляем слушатель событий
//                board[i][j].addActionListener(new ButtonListener());
//                add(board[i][j]);
//            }
//        }
//        setVisible(true);
//    }
//
//    // реализация обработчика события в отдельном классе
//    private class ButtonListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            JButton buttonClicked = (JButton) e.getSource();
//            // создание потока для первого игрока
//            player1 = new Thread(new TicTacToe.Player(1));
//            // создание потока для второго игрока
//            player2 = new Thread(new TicTacToe.Player(2));
//            // если клетка пустая, то выполняется ход
//            if (buttonClicked.getText().equals("")) {
//                // выполняет ход первый игрок
//                if (currentPlayer == 1) {
//                    buttonClicked.setText("X");
//                }
//                // выполняет ход второй игрок
//                else {
//                    buttonClicked.setText("O");
//                }
//                // проверка есть ли победитель
//                if (checkForWin()) {
//                    JOptionPane.showMessageDialog(null, (currentPlayer == 1 ? "X" : "O") + " выйграл!");
//                    System.exit(0);
//                }
//                // проверка на ничью
//                else if (checkForTie()) {
//                    JOptionPane.showMessageDialog(null, "Ничья!");
//                    System.exit(0);
//                }
//                else {
//                    // смена текущего игрока
//                    currentPlayer = currentPlayer == 1 ? 2 : 1;
//                }
//                // запуск потоков
//                player1.start();
//                player2.start();
//            }
//        }
//    }
//
//    // Класс для игрока
//    private class Player implements Runnable {
//        // номер игрока
//        private int playerNumber;
//
//        public Player(int playerNumber) {
//            this.playerNumber = playerNumber;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                // блокировка другого игрока
//                synchronized (lock) {
//                    // если текущий игрок не является этим игроком, то ждем
//                    while (currentPlayer != playerNumber) {
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println("Игрок " + playerNumber + " делает ход");
//                    // ожидание, пока игрок не сделает ход
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // оповещение другого игрока
//                    lock.notify();
//                }
//            }
//        }
//
//    }
//
//    // проверка наличия на поле выигрышной комбинации
//    private boolean checkForWin() {
//        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
//    }
//
//    // выигрышная комбинация
//    private boolean checkRowCol(String s1, String s2, String s3) {
//        return (s1.equals(s2) && s2.equals(s3) && !s1.equals(""));
//    }
//
//    // проверка наличия выигрышной комбинации по строкам
//    private boolean checkRowsForWin() {
//        for (i = 0; i < n; i++) {
//            if (checkRowCol(board[i][0].getText(), board[i][1].getText(), board[i][2].getText())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // проверка наличия выигрышной комбинации по столбцам
//    private boolean checkColumnsForWin() {
//        for (i = 0; i < n; i++) {
//            if (checkRowCol(board[0][i].getText(), board[1][i].getText(), board[2][i].getText())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // проверка наличия выигрышной комбинации по диагоналям
//    private boolean checkDiagonalsForWin() {
//        return (checkRowCol(board[0][0].getText(), board[1][1].getText(), board[2][2].getText()) || checkRowCol(board[0][2].getText(), board[1][1].getText(), board[2][0].getText()));
//    }
//
//    // проверка на ничью
//    private boolean checkForTie() {
//        for (i = 0; i < n; i++) {
//            for (j = 0; j < n; j++) {
//                if (board[i][j].getText().equals("")) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        new TicTacToe();
//    }
//}
//

package Game_Tic_Tac_Toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    private JButton[][] board;
    private int n = 3;
    private int i, j;
    private int currentPlayer = 1;
    private Thread player1, player2;
    private Object lock = new Object();

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(n, n));
        board = new JButton[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonClicked = (JButton) e.getSource();
                        if (buttonClicked.getText().equals("")) {
                            if (currentPlayer == 1) {
                                buttonClicked.setText("X");
                            } else {
                                buttonClicked.setText("O");
                            }
                            if (checkWin()) {
                                JOptionPane.showMessageDialog(null, (currentPlayer == 1 ? "X" : "O") + " выйграл!");
                                System.exit(0);
                            }
                            if (checkDraw()) {
                                JOptionPane.showMessageDialog(null, "Ничья!");
                                System.exit(0);
                            }
                            if (currentPlayer == 1) {
                                currentPlayer = 2;
                            } else {
                                currentPlayer = 1;
                            }
                            // когда один игрок сделал ход, он уведомляет другого игрока, что тот может сделать свой ход,
                            // вызов метод notifyAll() на объекте блокировки lock
                            synchronized (lock) {
                                lock.notifyAll();
                            }
                        }
                    }
                });
                add(board[i][j]);
            }
        }
        setVisible(true);
        // создание потоков для каждого игрока
        player1 = new Thread(new Player(1, lock));
        player2 = new Thread(new Player(2, lock));
        // запуск созданных потоков
        player1.start();
        player2.start();
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][0].getText().equals(board[i][2].getText()) &&
                    !board[i][0].getText().equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i].getText().equals(board[1][i].getText()) &&
                    board[0][i].getText().equals(board[2][i].getText()) &&
                    !board[0][i].getText().equals("")) {
                return true;
            }
        }
        if (board[0][0].getText().equals(board[1][1].getText()) &&
                board[0][0].getText().equals(board[2][2].getText()) &&
                !board[0][0].getText().equals("")) {
            return true;
        }
        if (board[0][2].getText().equals(board[1][1].getText()) &&
                board[0][2].getText().equals(board[2][0].getText()) &&
                !board[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private class Player implements Runnable {
        private int player;
        private Object lock;

        public Player(int player, Object lock) {
            this.player = player;
            this.lock = lock;
        }

        @Override
        // данный метод run() позволяет игрокам играть в игру параллельно, не блокируя друг друга
        public void run() {
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait(); // поток ожидает
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (checkWin()) {
                        JOptionPane.showMessageDialog(null, (currentPlayer == 1 ? "X" : "O") + " выйграл!");
                        System.exit(0);
                    }
                    if (checkDraw()) {
                        JOptionPane.showMessageDialog(null, "Ничья!");
                        System.exit(0);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
