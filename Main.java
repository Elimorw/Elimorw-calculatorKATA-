import java.util.Objects;
import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            System.out.println("Input: ");
            String exp = sc.nextLine();
            String outt = calc(exp);

            if (Objects.equals(exp, "stop") || Objects.equals(outt, "stop")) {
                break;
            }else System.out.println(outt);

        }

    }


    public static String calc(String input) {
        Convert converter = new Convert();

        String[] act = {"+", "-", "/", "*"};
        String[] regAct = {"\\+", "-", "/", "\\*"};
        boolean stop = true;

            // Ищим индекс для знака
            int actIndex = 0;
            boolean fl = false;
            for (int i = 0; i < act.length; i++) {
                if (input.contains(act[i])) {
                    actIndex = i;
                    fl = true;
                    break;
                }
            }
            if (!fl) {
                System.out.println("Неверный знак!!!");
                return "stop";
            }
                // Вход в обработку исключений
            try {
                // Разделяем по знаку, !!!метод работает только с регуляркой!!!
                String[] dt = input.split(regAct[actIndex]);
                if (dt.length > 2) {
                    System.out.println("Вы вели более двух операндов!  Допустимо только 1.");
                    return "stop";
                }

                if (converter.isRoman(dt[0]) == converter.isRoman(dt[1])) {
                    int x, y;
                    // Проверяем является ли число римским
                    boolean isRoman = converter.isRoman(dt[0]);
                    // Если число римское переводим его в арабское
                    if (isRoman) {
                        x = converter.romanToInt(dt[0]);
                        y = converter.romanToInt(dt[1]);

                    } else {
                        x = Integer.parseInt(dt[0]);
                        y = Integer.parseInt(dt[1]);
                    }
                    if ((x >= 1 && x <= 10) && (y >= 1 && y <= 10)) {
                        System.out.println("Output:");
                    } else {
                        System.out.println("Числа должны быть в диапазоне от 1 до 10 включительно");
                        return "stop";
                    }
                    int result = switch (actIndex) {
                        case 0 -> x + y;
                        case 1 -> x - y;
                        case 2 -> x / y;
                        default -> x * y;
                    };
                    // вывод значения в римских числах

                    if (isRoman) {
                        return converter.intToRoman(result);
                        // Вывод значения в арабских числах
                    } else {
                        return String.valueOf(result);

                    }
                } else {
                    System.out.println("Числа должны быть в одном формате");
                    return "stop";
                }
                // Обработка ошибки если число не целое
            } catch (NumberFormatException e) {
                System.out.println("Числа должны быть целыми");
                return "stop";
            }
    }

}





