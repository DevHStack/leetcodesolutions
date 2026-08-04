class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                seenDigit = true;
            } 
            else if (ch == '+' || ch == '-') {
                // Sign is allowed only at the beginning
                // or immediately after e/E
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } 
            else if (ch == '.') {
                // Dot cannot appear after exponent
                // and only one dot is allowed
                if (seenDot || seenExp) {
                    return false;
                }
                seenDot = true;
            } 
            else if (ch == 'e' || ch == 'E') {
                // Exponent cannot appear twice
                // and must follow at least one digit
                if (seenExp || !seenDigit) {
                    return false;
                }
                seenExp = true;
                seenDigit = false; // Need digits after exponent
            } 
            else {
                return false;
            }
        }

        return seenDigit;
    }
}