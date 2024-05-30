import java.util.Scanner;

public class Calculator {

    static final String[] romanNums = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static final String[] arabNums = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] regex = new String[]{"\\+", "-", "\\*", "/"};
    static final String[] operation = new String[]{"+", "-", "*", "/"};
    static String[] in;
    private static int opIndex = -1;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        System.out.println("""
                Введите математическое выражение(сложение, вычитание, умножение или деление) в одну строку без пробеловs
                арабскими(1+2) либо римскими(I+II) цифрамиs
                на вход принимаются целые числа от 1 до 10 включительно""");

        String input = scanner.nextLine();

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
            System.exit(0);
        }

        if (converter.isRoman(in[0]) != converter.isRoman(in[1])) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
                System.exit(0);
            }
        }

        boolean isRoman = converter.isRoman(in[0]);

        if (isRoman) {
            for (String romanNum : romanNums) {
                if (romanNum.equals(in[0]) || romanNum.equals(in[1])) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
                        System.exit(0);
                    }
                    break;
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
        } else {
            for (String arabNum : arabNums) {
                if (arabNum.equals(in[0]) || arabNum.equals(in[1])) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
                        System.exit(0);
                    }
                    break;
                }
            }
            System.out.println(calc(input));
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
