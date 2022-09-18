import java.util.Arrays;
import java.util.List;

/**
 * <a
 * href="https://leetcode.com/problems/integer-to-roman/">Integer to Roman</a>
 */
public class IntegerToRoman {

    static class Representer {
        private final int upperBound;
        private final int lowerBound;

        private final String romanChar;

        public Representer(int upperBound, int lowerBound, String romanChar) {
            this.upperBound = upperBound;
            this.lowerBound = lowerBound;
            this.romanChar = romanChar;
        }

        public int getUpperBound() {
            return upperBound;
        }

        public int getLowerBound() {
            return lowerBound;
        }

        public String getRomanChar() {
            return romanChar;
        }

        public boolean isAppropriate(Integer value) {
            return (value >= this.lowerBound && value < this.upperBound);
        }
    }

    static class RepresenterFactory {
        private static final List<Representer> representers = Arrays.asList(
          new Representer(4, 1, "I"),
          new Representer(5, 4, "IV"),
          new Representer(9, 5, "V"),
          new Representer(10, 9, "IX"),
          new Representer(40, 10, "X"),
          new Representer(50, 40, "XL"),
          new Representer(90, 50, "L"),
          new Representer(100, 90, "XC"),
          new Representer(400, 100, "C"),
          new Representer(500, 400, "CD"),
          new Representer(900, 500, "D"),
          new Representer(1000, 900, "CM"),
          new Representer(4000, 1000, "M")
        );

        public static Representer getAppropriateRepresenter(int value) {
            return representers.stream().filter(representer -> representer.isAppropriate(value)).findFirst().get();
        }
    }

    private static String intToRoman(Integer num) {
        return getRomanRepresentation("", num);
    }

    private static String getRomanRepresentation(String holder, Integer num) {
        if (num.toString().length() != 0) {
            String addition = "";
            Representer representer = RepresenterFactory.getAppropriateRepresenter(num);
            addition = String.valueOf(representer.getRomanChar());
            int rest = num - representer.getLowerBound();
            holder = holder.concat(addition);
            if (rest <= 0) {
                return holder;
            }

            return getRomanRepresentation(holder, rest);
        }
        return holder;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3425));
    }
}
