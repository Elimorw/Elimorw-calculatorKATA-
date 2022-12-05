import java.util.Objects;
import java.util.Scanner;

class Main {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        boolean stop = true;
        while (stop) {
            System.out.println("Input: ");
            String exp = sc.nextLine();
            String outt = calc(exp);

            if (Objects.equals(exp, "stop")) {
                break;
            } else if (!outt.equals("Вы вели более двух операндов!  Допустимо только 1.") && !outt.equals("Числа должны быть в диапазоне от 1 до 10 включительно") &&
                       !outt.equals("Числа должны быть в одном формате") && !outt.equals("Числа должны быть целыми") && !outt.equals("Неверный знак!!!")) {
                System.out.println("Output:\n" + outt);
            }else{
                System.out.println(outt);
                stop = false;
            }

        }

    }



    public static String calc(String input) {
        Convert converter = new Convert();

        String[] act = {"+", "-", "/", "*"};
        String[] regAct = {"\\+", "-", "/", "\\*"};

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
                return "Неверный знак!!!";
            }

            // Вход в обработку исключений
            try {

                // Разделяем по знаку, !!!метод работает только с регуляркой!!!
                String[] dt = input.split(regAct[actIndex]);
                if(dt.length > 2) {
                    return ("Вы вели более двух операндов!  Допустимо только 1.");

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
                    if((x >= 1 && x <= 10) && (y >= 1 && y<=10)){
                        System.out.println("Output:");
                    }else {
                        return ("Числа должны быть в диапазоне от 1 до 10 включительно");
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
                    return "Числа должны быть в одном формате" ;
                }
                // Обработка ошибки если число не целое
            } catch (NumberFormatException e){
                return "Числа должны быть целыми";
            }
    }

}

