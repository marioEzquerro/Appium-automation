package com.barify.utils;

import org.apache.commons.lang.StringUtils;

public class Utilities {
    public static String token;

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
        return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) / (double) longerLength;
    }

    public static String replaceSpecialCaracters(String text){
        return text.toLowerCase().trim()
                .replace("&ntilde;", "ñ")
                .replace("&aacute;", "á")
                .replace("&iacute;", "í")
                .replace("&eacute;", "é")
                .replace("&oacute;", "ó")
                .replace("&uacute;", "ú")
                .replace("&quot;", "\"\"");
    }





}
