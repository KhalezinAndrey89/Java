import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Введите выражение: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Результат: " + calc(scanner.nextLine()));
    }
    public static String calc(String input) throws Exception {
        String[] arr = input.replaceAll(" ", "").split("(?<=[-+*/])|(?=[-+*/])");
        int num1, num2, result;
        int count = 0;
        boolean romeBool = false;

        if (arr.length == 3) {
            for (int i=0; i < arr.length; i+=2) {
                try {
                    Integer.parseInt(arr[i]);
                } catch (NumberFormatException e) {
                    count++;
                }
            }
        } else {
            throw new Exception("формат математической операции не удовлетворяет заданию " +
                    "- два операнда и один оператор (+, -, /, *)");
        }

        if (count == 0) {
            num1 = Integer.parseInt(arr[0]);
            num2 = Integer.parseInt(arr[2]);
        } else if (count == 2) {
            num1 = getArabianNum(arr[0]);
            num2 = getArabianNum(arr[2]);
            romeBool = true;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }

        if (num1 >= 1 & num1 <= 10 & num2 >= 1 & num2 <= 10) {
            switch (arr[1]) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> result = num1 / num2;
                default -> throw new Exception("Операция не распознана");
            }
        } else {
            throw new Exception("Введенные числа должны быть от 1 до 10 включительно");
        }

        if (romeBool & result < 1) {
            throw new Exception("Римские числа не могут быть меньше еденицы");
        } else if (romeBool) {
            return interpreter(result);
        } else {
            return Integer.toString(result);
        }
    }
    static String interpreter(int result) {
        String[] array = {"", "", ""};

        if (result / 100 != 0) {
            array[0] = getRomeNum(100);
        }
        if (result / 10 % 10 != 0) {
            array[1] = getRomeNum(result / 10 * 10);
        }
        if (result % 10 != 0) {
            array[2] = getRomeNum(result % 10);
        }

        return String.join(",", array).replaceAll(",", "");
    }
    static int getArabianNum(String number) throws Exception {
        number = number.toUpperCase();
        return switch (number) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new Exception("Данное число не является римским в диаппазоне от 1 до 10");
        };
    }
    static String getRomeNum(int number){
        return switch (number) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            case 20 -> "XX";
            case 30 -> "XXX";
            case 40 -> "XL";
            case 50 -> "L";
            case 60 -> "LX";
            case 70 -> "LXX";
            case 80 -> "LXXX";
            case 90 -> "XC";
            case 100 -> "C";
            default -> "";
        };
    }
}