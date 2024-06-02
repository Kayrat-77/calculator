import java.util.Scanner;

public class Calculator {

    static final String[] regex = new String[]{"\\+", "-", "\\*", "/"};
    static final String[] operation = new String[]{"+", "-", "*", "/"};
    static String[] in;
    private static int opIndex;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        System.out.println("""
                Введите математическое выражение(сложение, вычитание, умножение или деление) в одну строку без пробелов\s
                арабскими(1+2) либо римскими(I+II) цифрами\s
                на вход принимаются целые числа от 1 до 10 включительно""");

        String input = scanner.nextLine();

        opIndex = -1;

        for (int i = 0; i < operation.length; i++) {
            if (input.contains(operation[i])) {
                opIndex = i;
                break;
            }
        }

        try {
            in = input.split(regex[opIndex]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Строка не соответствует одной из вышеописанных арифметических операций");
            e.printStackTrace();
            System.exit(0);
        }

        boolean isRoman = converter.isRoman(in[0]) && converter.isRoman(in[1]);
        boolean isArabian = converter.isArabian(in[0]) && converter.isArabian(in[1]);

        if (isRoman) {
            if (converter.isRoman(in[0]) != converter.isRoman(in[1])) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,  " +
                                       "на вход принимаются целые числа от 1 до 10 включительно");
                    e.printStackTrace();
                    System.exit(0);
                }
            }

            String num1 = converter.romanToArab(in[0]);
            String num2 = converter.romanToArab(in[1]);
            input = num1 + operation[opIndex] + num2;
            int res = Integer.parseInt(calc(input));

            if (res <= 0) {
                try {
                    throw new NumFormatException();
                } catch (NumFormatException e) {
                    System.out.println("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                    System.exit(0);
                }
            }
            System.out.println(Converter.intToRoman(Integer.parseInt(calc(input))));

        } else if (isArabian) {
            if (converter.isArabian(in[0]) != converter.isArabian(in[1])) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,  " +
                                       "на вход принимаются целые числа от 1 до 10 включительно");
                    e.printStackTrace();
                    System.exit(0);
                }
            }
            System.out.println(calc(input));
        } else {
            System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, " +
                               "на вход принимаются целые числа от 1 до 10 включительно");
            System.exit(0);
        }
    }

    public static String calc(String input) {

        in = input.split(regex[opIndex]);

        for (int i = 0; i < operation.length; i++) {
            if (input.contains(operation[i])) {
                opIndex = i;
                break;
            }
        }
        int a = Integer.parseInt(in[0]);
        int b = Integer.parseInt(in[1]);
        int result = 0;

        switch (operation[opIndex]) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
        }

        return String.valueOf((result));
    }
}
