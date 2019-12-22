package utils;

public class Utils {

    /*
        XOR двух строк с двоичными данными
     */

    public static String XOR(String s1, String s2) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            boolean a = Boolean.getBoolean(String.valueOf(s1.charAt(i)));
            boolean b = Boolean.getBoolean(String.valueOf(s2.charAt(i)));

            if (a ^ b) {
                result.append("1");
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    public static String f(String s1, String s2) {
        return XOR(s1, s2);
    }
}
