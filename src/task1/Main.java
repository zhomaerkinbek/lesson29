package task1;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var cats = Cat.makeCats(10);
        choose(cats);
    }

    static void choose(List<Cat> cats){
        Printer.print(cats);
        int number;
        int n = 0;
        while (n < 5) {
            System.out.println("1. Cортировка по породе кота через ссылку на метод\n" +
                    "2. Cортировка по породе кота через лямбда-выражение\n" +
                    "3. Cортировка по имени и возрасту через ссылку на метод\n" +
                    "4. Cортировка по имени и возрасту через лямбда-выражение\n" +
                    "5. Удаление из списка всех котов определенного цвета через ссылку на метод\n" +
                    "6. Удаление из списка всех котов определенного цвета через лямбда-выражение\n" +
                    "7. Удаление из списка всех котов, длина имени которого равна 5 через ссылку на метод\n" +
                    "8. Удаление из списка всех котов, длина имени которого равна 5 лямбда-выражение\n"
            );
            while (true) {
                System.out.print("Введите цифры для выполнения действия: ");
                try {
                    number = new Scanner(System.in).nextInt();
                } catch (Exception e) {
                    System.out.println("Ошибка! Введите число");
                    continue;
                }
                break;
            }
            switch (number) {
                case 1 -> cats.sort(Cat::sortByBreed);
                case 2 -> cats.sort((c1, c2) -> c1.getBreed().toString().compareTo(c2.getBreed().toString()));
                case 3 -> cats.sort(Cat::sortByNameAndAge);
                case 4 -> cats.sort((c1, c2) -> {
                    if(c1.getName().equals(c2.getName())){
                        return c1.getAge() - c2.getAge();
                    }
                    return c1.getName().compareTo(c2.getName());
                });
                case 5 -> {
                    var cat1 = Cat.makeCats(1);
                    System.out.println("Удаляем котов с цветом: " + cat1.get(0).getColor());
                    cats.removeIf(cat1.get(0)::deleteByColor);
                }
                case 6 -> {
                    System.out.println("Удаляем котов с цветом: GINGER");
                    cats.removeIf(cat -> cat.getColor().equals(task1.Cat.Color.GINGER));}
                case 7 -> {
                    var cat2 = Cat.makeCats(1);
                    cats.removeIf(cat2.get(0)::deleteByNameLengthEqualsFive);
                }
                case 8 -> cats.removeIf(cat -> cat.getName().length() == 5);
                default -> System.out.println("Такой команды нету!");
            }
            Printer.print(cats);
            n++;
        }
    }

}
