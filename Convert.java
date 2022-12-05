import java.util.TreeMap;

public class Convert {

    TreeMap<Character, Integer> rKeyMap = new TreeMap<>();
    TreeMap<Integer, String> anKeyMap = new TreeMap<>();

    public Convert() {
        anKeyMap.put(1000, "M");
        anKeyMap.put(900, "CM");
        anKeyMap.put(500, "D");
        anKeyMap.put(400, "CD");
        anKeyMap.put(100, "C");
        anKeyMap.put(90, "XC");
        anKeyMap.put(50, "L");
        anKeyMap.put(40, "XL");
        anKeyMap.put(10, "X");
        anKeyMap.put(9, "IX");
        anKeyMap.put(5, "V");
        anKeyMap.put(4, "IV");
        anKeyMap.put(1, "I");


        rKeyMap.put('I', 1);
        rKeyMap.put('V', 5);
        rKeyMap.put('X', 10);
        rKeyMap.put('L', 50);
        rKeyMap.put('C', 100);
        rKeyMap.put ('D', 500);
        rKeyMap.put('M', 1000);


    }
    public boolean isRoman(String number){
        return rKeyMap.containsKey(number.charAt(0));
    }

    // Переводим обратно в римские числа
    public String intToRoman(int number) {
        String roman = "";
        int arabianKey;
        do {
            arabianKey = anKeyMap.floorKey(number);
            roman += anKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return roman;


    }
    //Алгоритм для перевода в в арабские числа
    public int romanToInt(String s) {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = rKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = rKeyMap.get(arr[i]);

            if (arabian < rKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;

    }

}
