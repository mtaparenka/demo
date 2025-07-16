import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

/*

 */
public class Task1268Test {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);

        int position = 0;
        int substringEnd = 1;
        List<List<String>> result = new ArrayList<>();

        while (substringEnd <= searchWord.length()) {
            List<String> row = new ArrayList<>();

            int i = position;

            while (row.size() < 3 && i < products.length) {
                if (products[i].startsWith(searchWord.substring(0, substringEnd))) {
                    if (row.isEmpty()) {
                        position = i;
                    }

                    row.add(products[i]);
                }

                i++;
            }

            result.add(row);
            substringEnd++;
        }

        return result;
    }

    public List<List<String>> suggestedProductsTreeMap(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";

        for (char c : searchWord.toCharArray()) {
            key += c;
            var row = new ArrayList<String>();
            var closestMatch = map.ceilingEntry(key);
            var farthestMatch = map.floorEntry(key + "~");

            if (closestMatch != null && farthestMatch != null) {
                int i = closestMatch.getValue();

                while (row.size() < 3 && i <= farthestMatch.getValue()) {
                    row.add(products[i]);
                    i++;
                }
            }

            res.add(row);
        }

        return res;
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test(String[] products, String searchWord, List<List<String>> expected) {
        Assertions.assertIterableEquals(expected, suggestedProductsTreeMap(products, searchWord));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of(
                        new String[] {"mobile","mouse","moneypot","monitor","mousepad"},
                        "mouse",
                        List.of(
                                List.of("mobile","moneypot","monitor"),
                                List.of("mobile","moneypot","monitor"),
                                List.of("mouse","mousepad"),
                                List.of("mouse","mousepad"),
                                List.of("mouse","mousepad")
                        )
                ),
                Arguments.of(
                        new String[] {"bags","baggage","banner","box","cloths"},
                        "bags",
                        List.of(
                                List.of("baggage","bags","banner"),
                                List.of("baggage","bags","banner"),
                                List.of("baggage","bags"),
                                List.of("bags")
                        )
                )
        );
    }
}
