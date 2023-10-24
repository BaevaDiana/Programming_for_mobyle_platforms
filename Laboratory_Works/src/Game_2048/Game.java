package Game_2048;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
    // создание нового объекта класса Board, который представляет игровое поле
    Board game = new Board();
    // создание нового объекта класса Game, который используется для настройки графического интерфейса
    static Game newGame = new Game();
    // создание нового объекта класса JFrame с заголовком
    static JFrame frame = new JFrame("2048");
    // строковое представление игрового поля
    String gameBoard = game.toString();

    // настройка графического интерфейса
    public static void setUpGUI()
    {
        // добавление слушателя событий к объекту
        frame.addKeyListener(newGame);
        // добавление объекта newGame в контейнер contentPane объекта frame
        frame.getContentPane().add(newGame);
        // установка размером окна игры
        frame.setSize(600, 400);
        // установка окна видимым
        frame.setVisible(true);
        // нельзя изменять размер окна
        frame.setResizable(false);
    }

    // используется keyPressed по нажатию по кнопке клавиатуры
    // проверка нажатия соответствующих клавиши Wasd или клавиши со стрелками
    // обновление JFrame при каждом нажатии
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
        // TODO Auto-generated method
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method
    }

    // отрисовка отдельной плитки
    public void drawTiles(Graphics g, Tile tile, int x, int y)
    {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        // создание объекта Graphics2D из объекта Graphics
        Graphics2D g2 = (Graphics2D)g;
        // установка цвета рисования - серого
        g2.setColor(Color.lightGray);
        // заполнение прямоугольника с закругленными углами размером 50x50 пикселей
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        // установка цвета рисования для заголовка цифры - чёрный
        g2.setColor(Color.black);
        // проверка значения плитки
        if (tileValue > 0)
        {
            // установка цвета рисования в зависимости от значения плитки
            g2.setColor(tile.getColor());
            // аналогичное заполнение
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            // установка цвета рисования для заголовка цифры - серого
            g2.setColor(Color.black );
            // вывод значения плитки в центр прямоугольника
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }

    // отрисовка графического интерфейса
    @Override
    public void paint(Graphics g)
    {
        // отрисовка фона окна
        super.paint(g);
        // создание объекта Graphics2D из объекта Graphics
        Graphics2D g2 = (Graphics2D)g;
        // вывод соответствующих заголовков
        g2.drawString("2048", 250, 20);
        g2.drawString("Счёт: " + game.getScore(),200 - 4 * String.valueOf(game.getScore()).length(), 40);
        g2.drawString("Наибольшее значение: " + game.getHighTile(), 280 - 4 * String.valueOf(game.getHighTile()).length(),40);
        // начало игры
        g2.drawString("Нажмите 'Enter' чтобы начать", 210, 315);
        g2.drawString("Используйте клавиши 'wasd' или клавиши со стрелками для игры", 125, 340);
        // установка цвета рисования для окна - серый
        g2.setColor(Color.gray);
        // заполнение прямоугольника цветом
        g2.fillRect(140, 50, 250, 250);
        int i, j;
        // отрисовка плиток
        for (i = 0; i < 4; i++)
        {
            for (j = 0; j < 4; j++)
            {
                drawTiles(g, game.board[i][j], j * 60 + 150, i * 60 + 60);
            }
        }
        // случай, когда игровое поле переполнено
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
        // случай, когда игра окончена
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



