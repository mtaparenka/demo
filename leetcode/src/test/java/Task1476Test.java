import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/*

 */
public class Task1476Test {

    class SubrectangleQueries {
        public int[][] rectangle;

        public SubrectangleQueries(int[][] rectangle) {
            this.rectangle = rectangle;
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    rectangle[i][j] = newValue;
                }
            }
        }

        public int getValue(int row, int col) {
            return rectangle[row][col];
        }
    }

    @ParameterizedTest
    @MethodSource("testSource")
    void test() {
        var sq = new SubrectangleQueries(new int[][]{
                {1, 2, 1, 1},
                {3, 2, 8, 4},
                {5, 5, 1, 3},
                {1, 7, 1, 2}
        });

        sq.updateSubrectangle(1, 1, 3, 3, 8);
        System.out.println(Arrays.deepToString(sq.rectangle));
    }

    static Stream<Arguments> testSource() {
        return Stream.of(
                Arguments.of()
        );
    }
}
