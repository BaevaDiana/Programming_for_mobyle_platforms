package Game_Tic_Tac_Toe;
// Импортируем необходимые библиотеки
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe_1 extends JFrame {
    // Создаем игровое поле
    private JButton[][] board = new JButton[3][3];
    // Создаем потоки для игроков
    private Thread player1, player2;
    // Создаем объект для синхронизации потоков
    private Object lock = new Object();
    // Создаем переменную для хранения текущего игрока
    private int currentPlayer = 1;

    public TicTacToe_1() {
        // Создаем окно с заголовком "Крестики-Нолики"
        super("Крестики-Нолики");
        // Устанавливаем размер окна
        setSize(300, 300);
        // Устанавливаем менеджер компоновки
        setLayout(new GridLayout(3, 3));
        // Создаем кнопки для игрового поля
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 48));
                board[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Получаем кнопку, на которую нажал игрок
                        JButton button = (JButton) e.getSource();
                        // Получаем координаты кнопки
                        int row = -1, col = -1;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (board[i][j] == button) {
                                    row = i;
                                    col = j;
                                    break;
                                }
                            }
                        }
                        // Если кнопка уже была нажата, то ничего не делаем
                        if (!button.getText().equals("")) {
                            return;
                        }
                        // Блокируем другого игрока
                        synchronized (lock) {
                            // Устанавливаем значение на кнопке
                            button.setText(currentPlayer == 1 ? "X" : "O");
                            // Переключаем текущего игрока
                            currentPlayer = currentPlayer == 1 ? 2 : 1;
                            // Оповещаем другого игрока
                            lock.notify();
                        }
                    }
                });
                add(board[i][j]);
            }
        }
        // Создаем потоки для игроков
        player1 = new Thread(new Player(1));
        player2 = new Thread(new Player(2));
        // Запускаем потоки
        player1.start();
        player2.start();
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

    public static void main(String[] args) {
        // Создаем объект игры
        TicTacToe_1 game = new TicTacToe_1();
        // Устанавливаем видимость окна
        game.setVisible(true);
    }}