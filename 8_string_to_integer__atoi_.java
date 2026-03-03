class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;
        
        int INT_MAX = Integer.MAX_VALUE; //  2147483647
        int INT_MIN = Integer.MIN_VALUE; // -2147483648
        
        // 1. Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        
        // 2. Handle sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }
        
        // 3. Convert digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            
            // 4. Check overflow before multiplying
            if (result > (INT_MAX - digit) / 10) {
                return sign == 1 ? INT_MAX : INT_MIN;
            }
            
            result = result * 10 + digit;
            i++;
        }
        
        return sign * result;
    }
}