package dev.notcacha.languagelib.message.color;

public interface MessageColorApplier {

    static String apply(char altColorChar, String textToTranslate) {
        char COLOR_CHAR = '\u00A7';
        String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

}
