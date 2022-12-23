package com.vito.eeasy.window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithoutRepeatingChars {

    class Window {
        private int left;
        private int right;
        private final String underlying;
        private final Map<Character, Integer> chars = new HashMap<>();

        public Window(String underlying) {
            this.left = 0;
            this.right = 1;
            this.underlying = underlying;
            chars.put(underlying.charAt(0), 1);
        }

        public int repeatingChars() {
            return (int) chars.entrySet().stream().filter(en -> en.getValue() >= 2).count();
        }

        public int length() {
            return right - left;
        }

        public boolean canExpand() {
            return right < underlying.length();
        }

        public boolean canShrink() {
            return length() > 1;
        }

        public void expand() {
            char nextChar = underlying.charAt(right);
            right++;
            chars.put(nextChar, chars.getOrDefault(nextChar, 0) + 1);
        }

        public void shrink() {
            char charToRemove = underlying.charAt(left);
            left++;
            chars.put(charToRemove, chars.getOrDefault(charToRemove, 0) - 1);
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        var w = new Window(s);
        int maxLen = 1;
        while (w.canExpand() || w.canShrink()) {

            if (w.repeatingChars() > 0) {
                w.shrink();
            } else {
                maxLen = Math.max(maxLen, w.length());
                if (w.canExpand()) {
                    w.expand();
                } else {
                    break;
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        var z = new LongestSubstrWithoutRepeatingChars();
        System.out.println(z.lengthOfLongestSubstring(" "));
    }
}
