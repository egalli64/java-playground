package mit6005.w1;

public class Recursion {
    private static String partialSubsequence = "";

    public static String subsequencesLouis(String word) {
        if (word.isEmpty()) {
            // base case
            return partialSubsequence;
        } else {
            // recursive step
            String withoutFirstLetter = subsequencesLouis(word.substring(1));
            partialSubsequence += word.charAt(0);
            String withFirstLetter = subsequencesLouis(word.substring(1));
            return withoutFirstLetter + "," + withFirstLetter;
        }
    }

    private static String subsequences(String root, String word) {
        if (word.isEmpty()) {
            return root;
        }
        return subsequences(root, word.substring(1)) + "," + subsequences(root + word.charAt(0), word.substring(1));
    }

    public static String subsequences(String word) {
        return subsequences("", word);
    }

    public static String subsequences_original(String word) {
        if (word.isEmpty()) {
            return "";
        } else {
            char firstLetter = word.charAt(0);
            String rightSide = word.substring(1);

            String rightSubs = subsequences(rightSide);

            StringBuilder result = new StringBuilder();
            for (String sub : rightSubs.split(",")) {
                result.append(',');
                result.append(sub);
                result.append(',');
                result.append(firstLetter);
                result.append(sub);
            }
            return result.substring(1);
        }
    }

    public static String stringValue(int n, int base) {
        if (n < 0)
            return "-" + stringValue(-n, base);
        if (n < base)
            return "" + n;
        return stringValue(n / base, base) + "0123456789ABCDEF".charAt(n % base);
    }
}
