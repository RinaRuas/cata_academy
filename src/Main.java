import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Введите выражение (например, 1 + 2 или I + II):");
        String expression = input.nextLine().trim();

        // Разбиваем строку по пробелам
        String[] parts = expression.split(" ");

        // Проверяем, что выражение содержит ровно 3 части: число, оператор, число
        if (parts.length != 3) {
           System.out.println("Ошибка! Вводите только 2 числа");
           return;
        }


        String num_1_str = parts[0];
        char operator = parts[1].charAt(0);
        String num_2_str = parts[2];

        boolean isRoman = false;

        if (isRoman(num_1_str) && isRoman(num_2_str)) {
            isRoman = true;
        } else if (!isRoman(num_1_str) && !isRoman(num_2_str)) {
        } else {
            System.out.println("Ошибка! Введите оба числа либо римские либо арабские");
            return;
        }

        int num_1 = 0;
        int num_2 = 0;

        if (isRoman) {
            num_1 = romanToArabic(num_1_str);
            num_2 = romanToArabic(num_2_str);
        } else {
            num_1 = Integer.parseInt(num_1_str);
            num_2 = Integer.parseInt(num_2_str);
        }

        if (num_1 > 10 || num_2 > 10 || num_1 < 1 || num_2 < 1) {
            System.out.println("Ошибка: Введите числа от 1 до 10!");
            return;
        }

        int result = 0;
        switch (operator) {
            case '+':
                result = num_1 + num_2;
                break;
            case '-':
                result = num_1 - num_2;
                break;
            case '*':
                result = num_1 * num_2;
                break;
            case '/':
                if (num_2 == 0) {
                    System.out.println("Ошибка: Деление на ноль!");
                    return;
                }
                result = num_1 / num_2;
                break;
            default:
                System.out.println("Ошибка: Некорректный оператор!");
                return;
        }

        if (isRoman) {
            if (result <= 0) {
                System.out.println("Ошибка! римские цифры не могут быть отрицательными!");
                return;
            }
            System.out.println("Результат: " + arabicToRoman(result));
        } else {
            System.out.println("Результат: " + result);
        }

        input.close();
    }

    public static boolean isRoman(String str) {
        return str.matches("[IVXLCDM]+");
    }

    public static int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanCharToInt(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    public static int romanCharToInt(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            default: throw new IllegalArgumentException("Некорректный символ римского числа: " + c);
        }
    }

    public static String arabicToRoman(int number) {
        String[] romanNumerals = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return romanNumerals[number];
    }
}
