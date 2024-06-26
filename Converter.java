import java.util.*;
public class Converter {
    public static String intToRoman(int number) {
        if (number >= 4000 || number <= 0)
            return null;
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (number >= key) {
                number -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(1000, "M");
        initMap.put(900, "CM");
        initMap.put(500, "D");
        initMap.put(400, "CD");
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }

    TreeMap<String, String> romanKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put("I", "1");
        romanKeyMap.put("II", "2");
        romanKeyMap.put("III", "3");
        romanKeyMap.put("IV", "4");
        romanKeyMap.put("V", "5");
        romanKeyMap.put("VI", "6");
        romanKeyMap.put("VII", "7");
        romanKeyMap.put("VIII", "8");
        romanKeyMap.put("IX", "9");
        romanKeyMap.put("X", "10");
    }
    
    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number);
    }

    public boolean isArabian(String number) {
        return romanKeyMap.containsValue(number);
    }
    
    public String romanToArab(String s) {
        return romanKeyMap.get(s);
    }
}
