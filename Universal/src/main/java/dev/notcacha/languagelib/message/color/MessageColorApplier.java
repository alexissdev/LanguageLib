package dev.notcacha.languagelib.message.color;

public interface MessageColorApplier {

    /**
     * Set color from message
     *
     * @param altColorChar    the {@link Character} that will be changed by the {@link Character} that sets the color
     * @param textToTranslate text to be set color
     * @return {@param textToTranslate} with set colors
     */

    static String apply(char altColorChar, String textToTranslate) {
        char color = '\u00A7';

        String codes = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && codes.indexOf(b[i + 1]) > -1) {
                b[i] = color;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

}
