package com.gnoemes.bullhorn.Utils;

public final class TextUtilites {

    public static String parseNameToId(String sourceName) {
        char[] string = sourceName.toLowerCase().toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length; i++) {
            if (string[i] == ' ' || string[i] == '/' || string[i] == '.') {
                builder.append('-');
            }
            else if (string[i] == '(' || string[i] == ')') {
               continue;
            }
            else {
                builder.append(string[i]);
            }
        }
        return builder.toString();
    }
}
