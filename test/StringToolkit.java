
public class StringToolkit {

    public static int ParenthesisOpeningIndex(String input) {
        int closingCount = 0;
        for (int i = input.length() - 2; i >= 0; i--) {
            if (input.charAt(i) == ')') {
                closingCount++;
            } else if (input.charAt(i) == '(') {
                if (closingCount == 0) {
                    return i;
                } else {
                    closingCount--;
                }
            }
        }
        return -1;
    }

    /* this function is for only '|' to work correctly
        for example it turns
        ab|cd to (ab)|(cd)
    */
    public static String Orify(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                if (input.charAt(i + 1) != '(') {
                    for (int k = i + 1; k < input.length(); k++) {
                        if (input.charAt(k) == ')' || input.charAt(k) == '|') {
                            return Orify(input.substring(0, i + 1) + '(' + input.substring(i + 1, k) + ')' + input.substring(k));
                        }
                    }
                    return Orify(input.substring(0, i + 1) + '(' + input.substring(i + 1) + ')');
                } else if (input.charAt(i - 1) != ')') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (input.charAt(k) == '(' || input.charAt(k) == '|') {
                            return Orify(input.substring(0, k + 1) + '(' + input.substring(k + 1, i) + ')' + input.substring(i));
                        }
                    }
                    return Orify('(' + input.substring(0, i) + ')' + input.substring(i));
                }
            }
        }
        return input;
    }

}
