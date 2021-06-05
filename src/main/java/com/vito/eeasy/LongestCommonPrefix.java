package com.vito.eeasy;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {

        Character cc = null;
        int i = 0;

        for (;; i++) {
            boolean match = true;
            cc = null;

            for (String str : strs) {
                if (i >= str.length()) {
                    match = false;
                    break;
                } else {
                    if (cc == null) {
                        cc = str.charAt(i);
                    } else {
                        if (cc != str.charAt(i)) {
                            match = false;
                            break;
                        }
                    }

                }
            }

            if (!match) {
                break;
            }
        }

        return strs[0].substring(0, i);
    }
}
