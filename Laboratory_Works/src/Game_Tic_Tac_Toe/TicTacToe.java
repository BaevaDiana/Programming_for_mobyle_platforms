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
