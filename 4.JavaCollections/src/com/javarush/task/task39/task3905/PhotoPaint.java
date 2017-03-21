package com.javarush.task.task39.task3905;

/*
Залей меня полностью

В процессе разработки новой версии популярного графического редактора возникла необходимость реализовать заливку
области картинки определенным цветом.

Реализуй метод paintFill в классе PhotoPaint таким образом, чтобы он возвращал:
— false, если цвет начальной точки (координаты приходят в качестве параметров) совпадает с цветом заливки или
если начальные координаты выходят за рамки массива.
— модифицировал входящий массив и возвращал true, если заливка все же может быть выполнена.

Точке с координатами (x, y) соответствует элемент массив с индексом [y][x].

P.S. Если алгоритм работы заливки не очевиден, можешь попрактиковаться в графическом редакторе Paint.
*/
public class PhotoPaint {
    public boolean paintFill(Color[][] image, int r, int c, Color desiredColor) {
        int x = r;
        int y = c;
        if (y >= image.length || y < 0 || x >= image[c].length || x < 0 || image == null || desiredColor == null)
            return false;
        if (image[y][x] == desiredColor) return false;

        Color origin = image[y][x];
        image[y][x] = desiredColor;
        if (y != 0 && image[y - 1][x] == origin) paintFill(image, x, y - 1, desiredColor);
        if (y != image.length - 1 && image[y + 1][x] == origin) paintFill(image, x, y + 1, desiredColor);
        if (x != 0 && image[y][x - 1] == origin) paintFill(image, x - 1, y, desiredColor);
        if (x != image[0].length - 1 && image[y][x + 1] == origin) paintFill(image, x + 1, y, desiredColor);


        return true;
    }
}
