package algorithm;

/*
    Главный алгоритм. Используется кодировка Unicode 16 bit
 */

import utils.Utils;

public class DES {

    // размер блока
    private final int sizeOfBlock = 128;
    // размер одного символа
    private final int sizeOfChar = 16;
    // сдвиг ключа
    private final int shiftKey = 2;
    // количество раундов
    private final int quantityOfRounds = 16;

    // сами блоки в двоичном формате
    private String[] blocks;

    /*
        Метод, доводящий строку до размера, который делится
        нацело на sizeOfBlock. Увеличиваем, добавляя символ "решетка"
     */

    private String StringToRightLength(String input) {
        StringBuilder inputBuilder = new StringBuilder(input);
        while (((inputBuilder.length() * sizeOfChar) % sizeOfBlock) != 0) {
            inputBuilder.append("#");
        }
        input = inputBuilder.toString();
        return input;
    }

    /*
        Метод, разбивающий строку в двоичном формате на блоки
     */

    private void CutBinaryStringIntoBlocks(String input) {
        blocks = new String[input.length() / sizeOfBlock];
        int lengthOfBlock = input.length() / blocks.length;

        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = input.substring(i * lengthOfBlock, lengthOfBlock);
        }
    }

    /*
        Метод, переводящий строку в двоичный формат
     */

    private String StringToBinaryFormat(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            StringBuilder charBinary = new StringBuilder(Integer.toBinaryString(input.charAt(i)));

            while (charBinary.length() < sizeOfChar) {
                charBinary.insert(0, "0");
            }
            output.append(charBinary);
        }
        return output.toString();
    }

    /*
        Метод, доводящий длину ключа до нужной длины
     */

    private String CorrectKeyWord(String input, int lengthKey) {
        if (input.length() > lengthKey) {
            input = input.substring(0, lengthKey);
        } else {
            StringBuilder inputBuilder = new StringBuilder(input);
            while (inputBuilder.length() < lengthKey) {
                inputBuilder.insert(0, "0");
            }
            input = inputBuilder.toString();
        }
        return input;
    }

    /*
        Один раунд шифрования алгоритмом DES
     */

    private String EncodeDESOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2, input.length() / 2);

        return (R + Utils.XOR(L, Utils.f(R, key)));
    }

    /*
        Один раунд расшифровки алгоритмом DES
     */

    private String DecodeDESOneRound(String input, String key) {
        String L = input.substring(0, input.length() / 2);
        String R = input.substring(input.length() / 2, input.length() / 2);

        return (Utils.XOR(Utils.f(L, key), R) + L);
    }

     /*
        Вычисление ключа для следующего раунда зашифровки DES.
        Циклический сдвиг >> shiftKey.
     */

     private String KeyToNextRound(String key) {
         StringBuilder keyBuilder = new StringBuilder(key);
         for (int i = 0; i < shiftKey; i++) {
             keyBuilder.insert(0, keyBuilder.charAt(keyBuilder.length() - 1));
             keyBuilder = new StringBuilder(keyBuilder.substring(keyBuilder.length() - 1));
         }
         key = keyBuilder.toString();
         return key;
     }

    /*
        Вычисление ключа для следующего раунда расшифорвки DES.
        Циклический сдвиг << shiftKey.
     */

    private String KeyToPrevRound(String key) {
        StringBuilder keyBuilder = new StringBuilder(key);
        for (int i = 0; i < shiftKey; i++) {
            keyBuilder.append(keyBuilder.charAt(0));
            keyBuilder = new StringBuilder(keyBuilder.substring(0, 1));
        }
        key = keyBuilder.toString();
        return key;
    }

    /*
        Метод, переводящий строку в символьный формат
     */

    private String StringFromBinaryToNormalFormat(String input) {
        StringBuilder output = new StringBuilder();

        while (input.length() > 0) {
            String charBinary = input.substring(0, sizeOfChar);
            input = input.substring(0, sizeOfChar);

            int a = 0;
            int degree = charBinary.length() - 1;

            for (int i = 0; i < charBinary.length(); i++) {
                char c = charBinary.charAt(i);
                a += (int) c * (int)Math.pow(2, degree--);
            }
            output.append(a);
        }
        return output.toString();
    }

    /*
        Кнопка "Зашифровать"
     */

    /*
        Кнопка "Расшифровать"
     */
}

