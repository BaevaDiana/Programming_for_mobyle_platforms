package Game_Tic_Tac_Toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    // двумерный массив кнопок - поле игры
    public JButton[][] board;
    // переменная для проверки чья очередь выполнить ход
    private boolean player1Turn = true;
    public int n = 3;
    public int i,j;

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
                // создание нового потока
                Thread player = new Thread(() ->
                {
                    // выполняет ход первый игрок
                    if (player1Turn)
                    {
                        buttonClicked.setText("X");
                    }
                    // выполняет ход второй игрок
                    else
                    {
                        buttonClicked.setText("O");
                    }
                    // проверка есть ли победитель
                    if (checkForWin())
                    {
                        JOptionPane.showMessageDialog(null, (player1Turn ? "X" : "O") + " выйграл!");
                        System.exit(0);
                    }
                    // проверка на ничью
                    else if (checkForTie())
                    {
                        JOptionPane.showMessageDialog(null, "Ничья!");
                        System.exit(0);
                    }
                    // смена хода
                    else
                    {
                        player1Turn = !player1Turn;
                    }
                });
                // запуск нового потока при нажатии на кнопку
                player.start();
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
