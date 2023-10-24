package Game_2048;

// Класс Board (Игровое поле) работает с игровым полем.

public class Board {

    // массив элементов из класса Tile
    public Tile[][] board;
    // инициализация переменной размерности сетки
    public int grids = 4;
    // инициализация переменной границы
    public int border = 0;
    // инициализация переменной игрового счёта
    public int score = 0;

    // создание игрового поля размером 4x4
    public Board()
    {
        board = new Tile[4][4];
        int i, j;
        for (i = 0; i < board.length; i++)
        {
            for (j = 0; j < board[i].length; j++)
            {
                board[i][j] = new Tile();
            }
        }
    }

    // геттер, возвращающий доску
    public Tile[][] getBoard()
    {
        return board;
    }

    // геттер, возвращающий игровой счёт
    public int getScore()
    {
        return score;
    }

    // поиск самой большой по значению плитки
    public int getHighTile()
    {
        int high = board[0][0].getValue();
        int i, j;
        for (i = 0; i < board.length; i++)
        {
            for (j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getValue() > high)
                {
                    high = board[i][j].getValue();
                }
            }
        }
        return high;
    }

    // возвращение игровой доски в виде строки (для графического интерфейса)
    @Override
    public String toString()
    {
        String s = "";
        int i, j;
        for (i = 0; i < board.length; i++)
        {
            for (j = 0; j < board[i].length; j++)
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    // генерация 2 или 4 на пустом месте каждый раз, когда делается ход
    public void spawn()
    {
        boolean empty = true;
        while (empty)
        {
            int row = (int)(Math.random() * 4);
            int col = (int)(Math.random() * 4);
            double x = Math.random();
            if (board[row][col].getValue() == 0)
            {
                if (x < 0.2)
                {
                    board[row][col] = new Tile(4);
                    empty = false;
                }
                else
                {
                    board[row][col] = new Tile(2);
                    empty = false;
                }
            }
        }
    }

    // проверка, полностью ли заполнено игровое поле (когда все плитки заняты, но не выигрышной комбинацией)
    public boolean blackOut()
    {
        int count = 0;
        int i, j;
        for (i = 0; i < board.length; i++)
        {
            for (j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getValue() > 0)
                {
                    count++;
                }
            }
        }
        if (count == 16)
        {
            return true;
        }
        return false;
    }

    // проверка окончена ли игра (когда игровое поле заполнено и ни одна из плиток не может объединиться)
    public boolean gameOver()
    {
        int count = 0;
        int i, j;
        for (i = 0; i < board.length; i++)
        {
            for (j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getValue() > 0)
                {
                    if (i == 0 && j == 0)
                    {
                        if (board[i][j].getValue() != board[i + 1][j].getValue() && board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (i == 0 && j == 3)
                    {
                        if (board[i][j].getValue() != board[i + 1][j].getValue() && board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (i == 3 && j == 3)
                    {
                        if (board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (i == 3 && j == 0)
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i][j + 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (i == 0 && (j == 1 || j == 2))
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue() && board[i][j].getValue() != board[i][j + 1].getValue() && board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (i == 3 && (j == 1 || j == 2))
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i][j + 1].getValue() && board[i][j].getValue() != board[i][j - 1].getValue())
                        {
                            count++;
                        }
                    }
                    else if (j == 0 && (i == 1 || i == 2))
                    {
                        if ( board[i][j].getValue() != board[i][j + 1].getValue() && board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                    else if (j == 3 && (i == 1 || i == 2))
                    {
                        if ( board[i][j].getValue() != board[i][j - 1].getValue() && board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i + 1][j].getValue())
                        {
                            count++;
                        }
                    }
                    else
                    {
                        if (board[i][j].getValue() != board[i][j - 1].getValue() && board[i][j].getValue() != board[i][j + 1].getValue() && board[i][j].getValue() != board[i - 1][j].getValue() && board[i][j].getValue() != board[i + 1][j].getValue())
                        {
                            count++;
                        }
                    }
                }
            }
        }
        if (count == 16 )
        {
            return true;
        }
        return false;
    }

    // сравнение значения двух плиток по вертикали и определение, совпадают ли они или нет
    // если значение 0 (пустая плитка) — их значения суммируются
    // рекурсия для прохождения всего столбца
    private void verticalMove(int row, int col, String direction)
    {
        Tile initial = board[border][col];
        Tile compare = board[row][col];
        if (initial.getValue() == 0 || initial.getValue() == compare.getValue())
        {
            if (row > border || (direction.equals("down") && (row < border)))
            {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0 )
                {
                    score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }
        else
        {
            if (direction.equals("down"))
            {
                border--;
            }
            else
            {
                border++;
            }
            verticalMove(row, col, direction);
        }
    }

    // нажатие клавиши «w» или стрелки вверх
    // вызов метода verticalMove с параметром «вверх» для каждой плитки
    public void up()
    {
        int i, j;
        for (i = 0; i < grids; i++)
        {
            border = 0;
            for (j = 0; j < grids; j++)
            {
                if (board[j][i].getValue() != 0)
                {
                    if (border <= j)
                    {
                        verticalMove(j, i, "up");
                    }
                }
            }
        }
    }

    // нажатие клавиши «s» или стрелки вниз
    // вызов метода verticalMove с параметром «вниз» для каждой плитки
    public void down()
    {
        int i, j;
        for (i = 0; i < grids; i++)
        {
            border = (grids - 1);
            for (j = grids - 1; j >= 0; j--)
            {
                if (board[j][i].getValue() != 0)
                {
                    if (border >= j)
                    {
                        verticalMove(j, i, "down");
                    }
                }
            }
        }
    }

    // аналогичное сравнение двух плиток по горизонтали и определение совпадают они или нет
    private void horizontalMove(int row, int col, String direction)
    {
        Tile initial = board[row][border];
        Tile compare = board[row][col];
        if (initial.getValue() == 0 || initial.getValue() == compare.getValue())
        {
            if (col > border || (direction.equals("right") && (col < border)))
            {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0 )
                {
                    score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        }
        else
        {
            if (direction.equals("right"))
            {
                border--;
            }
            else
            {
                border++;
            }
            horizontalMove(row, col, direction);
        }
    }

    // нажатие клавиши «a» или стрелки влево
    // вызов метода HorizontalMove с параметром «влево» для каждой плитки
    public void left()
    {
        int i, j;
        for (i = 0; i < grids; i++)
        {
            border = 0;
            for (j = 0; j < grids; j++)
            {
                if (board[i][j].getValue() != 0)
                {
                    if (border <= j)
                    {
                        horizontalMove(i, j,"left");
                    }
                }
            }
        }
    }

    // нажатие клавиши «d» или стрелки вправо
    // вызов метода HorizontalMove с параметром «вправо» для каждой плитки
    public void right()
    {
        int i, j;
        for (i = 0; i < grids; i++)
        {
            border = (grids - 1);
            for (j = (grids - 1);j >= 0; j--)
            {
                if (board[i][j].getValue() != 0)
                {
                    if (border >= j)
                    {
                        horizontalMove(i, j, "right");
                    }
                }
            }
        }
    }

}
