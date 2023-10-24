package Game_2048;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JPanel implements KeyListener {
    //
    Board game = new Board();
    //
    static Game newGame = new Game();
    //
    static JFrame frame = new JFrame("2048");
    //
    static Color green;
    //
    String gameBoard = game.toString();

    // настройка графического интерфейса
    public static void setUpGUI()
    {
        frame.addKeyListener(newGame);
        frame.getContentPane().add(newGame);
        frame.setSize( 600, 400 );
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // проверка нажатия соответствующих клавиши Wasd или клавиши со стрелками
    // обновление JFrame при каждом движении
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP)
        {
            game.up();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        }
        else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            game.down();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        }
        else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            game.left();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        }
        else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            game.right();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            game = new Board();
            game.spawn();
            game.spawn();
            frame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    // отрисовка отдельной плитки
    public void drawTiles(Graphics g, Tile tile, int x, int y)
    {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);
        if (tileValue > 0)
        {
            g2.setColor(tile.getColor());
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black );
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }

    // отрисовка графического интерфейса
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString("2048", 250, 20);
        g2.drawString("Счёт: " + game.getScore(),200 - 4 * String.valueOf(game.getScore()).length(), 40);
        g2.drawString("Наибольшее значение: " + game.getHighTile(), 280 - 4 * String.valueOf(game.getHighTile()).length(),40);
        g2.drawString("Нажмите 'Enter' чтобы начать", 210, 315);
        g2.drawString("Используйте клавиши 'wasd' или клавиши со стрелками для игры", 125, 340);
        g2.setColor(Color.gray);
        g2.fillRect(140, 50, 250, 250);
        int i, j;
        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                drawTiles(g, game.board[i][j], j * 60 + 150, i * 60 + 60);
            }
        }
        if (game.blackOut())
        {
            g2.drawString("Нажмите 'Enter' чтобы начать заново", 200, 360);
            g2.setColor(Color.gray);
            g2.fillRect(140, 50, 250, 250);
            for (i = 0; i < 4; i++)
            {
                for (j = 0; j < 4; j++)
                {
                    drawTiles(g, game.board[i][j], j * 60 + 150, i * 60 + 60);
                }
            }
        }
        if (game.gameOver())
        {
            g2.setColor(Color.gray);
            g2.fillRect(140, 50, 250, 250);
            for (i = 0; i < 4; i++)
            {
                for (j = 0; j < 4; j++)
                {
                    g2.setColor(Color.RED);
                    g2.fillRoundRect(j * 60 + 150, i * 60 + 60, 50, 50, 5, 5);
                    g2.setColor(Color.black );
                    g.drawString("ИГРА", j * 60 + 160, i * 60 + 75);
                    g.drawString("ОКОНЧЕНА", j * 60 + 160, i * 60 + 95);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        setUpGUI();
    }
}



