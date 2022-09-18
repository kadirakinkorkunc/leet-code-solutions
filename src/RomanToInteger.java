import java.util.EnumSet;

/**
 * <a
 * href="https://leetcode.com/problems/roman-to-integer/">Roman to Integer</a>
 */
public class RomanToInteger {
    public static void main(String[] args) throws Exception {
        System.out.println(romanToInt("CXLIV"));
    }

    enum InvalidPattern {
        IIII,
        XXXX,
        LL,
        CCCC,
        DD;
    }

    enum RomanSymbol {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        private final int value;

        RomanSymbol(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    public static class SubtractionAnalyzer {
        public enum ValidPattern {
            IV(1),
            IX(1),
            XL(10),
            XC(10),
            CD(100),
            CM(100);

            private final int subtractionAmount;

            public int getSubtractionAmount() {
                return subtractionAmount;
            }

            ValidPattern(int subtractionAmount) {
                this.subtractionAmount = subtractionAmount;
            }
        }

        public static int analyze(String pattern) {
            return EnumSet.allOf(ValidPattern.class)
                    .stream()
                    .filter(validPattern -> pattern.contains(validPattern.name()))
                    .mapToInt(ValidPattern::getSubtractionAmount).sum();
        }
    }

    private static int romanToInt(String s) throws Exception {
        validate(s);
        char[] roman = s.toCharArray();
        int result = 0;
        for (int i = 0; i < roman.length; i++) {
            RomanSymbol currentSymbol = RomanSymbol.valueOf(String.valueOf(roman[i]));

            if (i != (roman.length - 1)) {
                RomanSymbol nextSymbol = RomanSymbol.valueOf(String.valueOf(roman[i+1]));

                if (nextSymbol.getValue() > currentSymbol.getValue()) {
                    continue;
                }
            }
            result += currentSymbol.getValue();
        }
        result -= SubtractionAnalyzer.analyze(s);
        return result;
    }

    private static void validate(String s) throws Exception {
        if(EnumSet.allOf(InvalidPattern.class)
                .stream()
                .anyMatch(invalidPattern -> s.contains(invalidPattern.name())) ) {
            throw new Exception("Invalid pattern.");
        };
    }
}