package Game_2048;

import java.awt.Color;

// Класс Tile(Плитка) работает с отдельными плитками.

public class Tile {

    // целочисленное значение плитки
    public int value;
    // цвет плитки
    Color tileColor;

    // создание пустой плитки со значением 0
    public Tile()
    {
        value = 0;
    }

    // создание не пустой плитки со значением
    public Tile(int number)
    {
        value = number;
    }

    // сеттер значения плитки (используется при сложении двух плиток вместе)
    public void setValue(int value)
    {
        this.value = value;
    }

    // геттер значения плитки
    public int getValue()
    {
        return value;
    }

    // возвращение значения плитки как строки (для графического интерфейса)
    @Override
    public String toString()
    {
        return "" + value;
    }

    // сеттер цвета плитки (в зависимости от ее значения)
    public void setColor()
    {
        if (this.getValue() == 2)
        {
            tileColor = new Color(238, 228, 218);
        }
        else if ( this.getValue() == 4 )
        {
            tileColor = new Color(237, 224, 200);
        }
        else if (this.getValue() == 8)
        {
            tileColor = new Color(242, 177, 121);
        }
        else if (this.getValue() == 16)
        {
            tileColor = new Color(245, 149, 99 );
        }
        else if (this.getValue() == 32)
        {
            tileColor = new Color(246, 124, 95);
        }
        else if (this.getValue() == 64)
        {
            tileColor = new Color(246, 94, 59);
        }
        else if (this.getValue() == 128)
        {
            tileColor = new Color(237, 207, 114);
        }
        else if (this.getValue() == 256)
        {
            tileColor = new Color(237, 204, 97);
        }
        else if (this.getValue() == 512)
        {
            tileColor = new Color(237, 200, 80);
        }
        else if (this.getValue() == 1024 )
        {
            tileColor = new Color(237, 197, 63);
        }
        else
        {
            tileColor = new Color(237, 194, 46);
        }
    }

    // геттер цвета плитки
    public Color getColor()
    {
        this.setColor();
        return tileColor;
    }

}
