import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a
 * href="https://leetcode.com/problems/longest-common-prefix">Longest Common Prefix
 * </a>
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs2 = {"dogs","racecar","car"};
        String[] strs3 = {"flower","flower","flower","flower"};
        String[] strs4 = {"a"};
        String[] strs5 = {"reflower","flow","flight"};
        String[] strs6 = {"aaa","aa","aaa"};

        System.out.println(findLongestCommonPrefix(strs));
        System.out.println(findLongestCommonPrefix(strs2));
        System.out.println(findLongestCommonPrefix(strs3));
        System.out.println(findLongestCommonPrefix(strs4));
        System.out.println(findLongestCommonPrefix(strs5));
        System.out.println(findLongestCommonPrefix(strs6));
    }

    private static String findLongestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }

        HashMap<String, List<String>> prefixWordsMap = new HashMap<>();

        Arrays.stream(strs).forEach(s -> {
            for (int i = 1; i < s.length() + 1; i++) {
                String subString = s.substring(0, i);
                prefixWordsMap.computeIfAbsent(subString, s1 -> {
                    List<String> derivedWords = prefixWordsMap.get(subString.substring(0, subString.length() - 1));
                    if (derivedWords != null && !derivedWords.isEmpty()) {
                        return filter(derivedWords.stream(), subString);
                    } else {
                        return filter(Arrays.stream(strs), subString);
                    }
                });
            }
        });

        Result result = new Result();
        prefixWordsMap.forEach((key, value) -> {
            int size = value.size();
            if (size == strs.length && key.length() > result.key.length()) {
                result.key = key;
                result.count = size;
            }
        });

        return result.key;
    }

    private static List<String> filter(Stream<String> stream, String subString) {
        List<String> filteredWords = stream.filter(word -> word.startsWith(subString)).collect(Collectors.toList());

        return filteredWords.size() > 1 ? filteredWords : Collections.emptyList();
    }

    static class Result {
        String key = "";
        Integer count = 0;
    }
}


