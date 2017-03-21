package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        Color[][] array = new Color[][]{{Color.BLUE, Color.GREEN, Color.INDIGO, Color.RED},
                                        {Color.BLUE, Color.GREEN, Color.INDIGO, Color.RED},
                                        {Color.BLUE, Color.GREEN, Color.INDIGO, Color.RED},
                                        {Color.BLUE, Color.GREEN, Color.INDIGO, Color.RED}};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println(new PhotoPaint().paintFill(array, 1, 1, Color.RED));

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
