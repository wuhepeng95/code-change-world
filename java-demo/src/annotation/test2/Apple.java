package annotation.test2;

public class Apple {
    @FruitColor(fruitColor = FruitColor.Yanse.RED)
    private FruitColor.Yanse fruitColor;

    @AppleSize
    private int size;

    public static void main(String[] args) {
        Apple apple = new Apple();
        System.out.println(apple.fruitColor);
        System.out.println(apple.size);

    }
}
