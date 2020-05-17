package stack;

import java.util.Stack;

/**
 * @author sun
 * @date 2020/3/25 22:17
 * @description
 */
public class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i=0; i<s.length(); i++){
            if (s.charAt(i) != '(' && s.charAt(i) != '[' && s.charAt(i) != '{'){
                if (stack.isEmpty()) return false;
                char topChar = stack.pop();
                if (topChar == '(' && s.charAt(i) != ')') return false;
                if (topChar == '[' && s.charAt(i) != ']') return false;
                if (topChar == '{' && s.charAt(i) != '}') return false;
            }else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid02(String s) {
        Stack<Character> stack = new Stack();

        for(int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '(' && s.charAt(i) != '[' && s.charAt(i) != '{') {
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = (Character)stack.pop();
                if (s.charAt(i) == ')' && topChar != '(') {
                    return false;
                }

                if (s.charAt(i) == ']' && topChar != '[') {
                    return false;
                }

                if (s.charAt(i) == '}' && topChar != '{') {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean valid = solution.isValid("{([])}");
        System.out.println(valid);
    }

}
