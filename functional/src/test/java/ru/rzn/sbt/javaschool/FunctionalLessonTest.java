package ru.rzn.sbt.javaschool;

import org.junit.Test;
import ru.rzn.sbt.javaschool.functional.FunctionalLesson;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class FunctionalLessonTest {

    FunctionalLesson instance = new FunctionalLesson();

    @Test
    public void sort() {
        System.out.println(">>> sort() test output start <<<");
        List<Integer> l1 = randomIntegerList();
        List<Integer> l2 = new ArrayList<>(l1);
        System.out.println(Arrays.toString(l1.toArray()));
        instance.sort(l1);
        instance.sort_f(l2);
        System.out.println(Arrays.toString(l1.toArray()));
        System.out.println(Arrays.toString(l2.toArray()));
        assertArrayEquals(l1.toArray(), l2.toArray());
        System.out.println(">>> sort() test output end <<<");
    }

    @Test
    public void sout() {
        System.out.println(">>> sout() test output start <<<");
        List<String> list = randomStringList();
        PrintStream out = System.out;
        try {
            class SpyPS extends PrintStream {
                private final List<String> printed;

                public SpyPS(List<String> printed) {
                    super(System.out);
                    this.printed = printed;
                }

                @Override public void println(String s) {
                    super.println(s);
                    printed.add(s);
                }
            };

            List<String> printed1 = new ArrayList<>();
            System.setOut(new SpyPS(printed1));
            instance.sout(list);

            System.setOut(out);
            System.out.println("===========================");

            List<String> printed2 = new ArrayList<>();
            System.setOut(new SpyPS(printed2));
            instance.sout_f(list);

            System.setOut(out);
            System.out.println(">>> sout() test output end <<<");

            assertArrayEquals(printed1.toArray(), printed2.toArray());
        } catch (Throwable t) {
            System.setOut(out);
            fail();
        }
    }

    @Test
    public void mayTheOddsBeEverInYourFavor() {
        System.out.println(">>> mayTheOddsBeEverInYourFavor() test output start <<<");
        List<String> list1 = randomStringList();
        List<String> list2 = new ArrayList<>(list1);
        System.out.println(Arrays.toString(list1.toArray()));

        instance.mayTheOddsBeEverInYourFavor(list1);
        instance.mayTheOddsBeEverInYourFavor_f(list2);
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(Arrays.toString(list2.toArray()));

        System.out.println(">>> mayTheOddsBeEverInYourFavor() test output end <<<");
        assertArrayEquals(list1.toArray(), list2.toArray());
    }

    @Test
    public void answer42() {
        System.out.println(">>> answer42() test output start <<<");
        List<String> list1 = randomStringList();
        List<String> list2 = new ArrayList<>(list1);
        System.out.println(Arrays.toString(list1.toArray()));

        instance.answer42(list1);
        instance.answer42_f(list2);
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(Arrays.toString(list2.toArray()));

        System.out.println(">>> answer42() test output end <<<");
        assertArrayEquals(list1.toArray(), list2.toArray());
    }

    @Test
    public void ustas2alex() {
        System.out.println(">>> ustas2alex() test output start <<<");
        List<String> list = randomStringList();
        System.out.println(Arrays.toString(list.toArray()));

        String s = instance.ustas2alex(list);
        String s_f = instance.ustas2alex_f(list);
        System.out.println(s);
        System.out.println(s_f);

        System.out.println(">>> ustas2alex() test output end <<<");
        assertEquals(s, s_f);
    }

    @Test
    public void properties() {
        System.out.println(">>> properties() test output start <<<");
        Map<String, Object> map = new HashMap<>();
        randomStringList().forEach(s -> map.put(s, random.nextInt()));
        System.out.println(map);

        String p = instance.properties(map);
        String p_f = instance.properties_f(map);
        System.out.println(p);
        System.out.println("=====================");
        System.out.println(p_f);

        System.out.println(">>> properties() test output end <<<");
        assertEquals(p, p_f);
    }

    @Test
    public void lower() {
        System.out.println(">>> lower() test output start <<<");
        List<String> src = Collections.unmodifiableList(randomStringList());
        System.out.println(Arrays.toString(src.toArray()));

        List<String> list = instance.lower(src);
        List<String> list_f = instance.lower_f(src);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list_f.toArray()));

        System.out.println(">>> lower() test output end <<<");
        assertArrayEquals(list.toArray(), list_f.toArray());
    }

    @Test
    public void lowerAndOdd() {
        System.out.println(">>> lowerAndOdd() test output start <<<");
        List<String> src = Collections.unmodifiableList(randomStringList());
        System.out.println(Arrays.toString(src.toArray()));

        List<String> list = instance.lowerAndOdd(src);
        List<String> list_f = instance.lowerAndOdd_f(src);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list_f.toArray()));

        System.out.println(">>> lowerAndOdd() test output end <<<");
        assertArrayEquals(list.toArray(), list_f.toArray());
    }

    @Test
    public void count() throws Exception {
        System.out.println(">>> count() test output start <<<");

        long count = instance.count();
        long count_f = instance.count_f();
        System.out.println(count);
        System.out.println(count_f);

        System.out.println(">>> count() test output end <<<");
        assertEquals(count, count_f);
    }

    @Test
    public void countWords() throws Exception {
        System.out.println(">>> countWords() test output start <<<");

        long count = instance.countWords();
        long count_f = instance.countWords_f();
        System.out.println(count);
        System.out.println(count_f);

        System.out.println(">>> countWords() test output end <<<");
        assertEquals(count, count_f);
    }

    @Test
    public void fWord() throws Exception {
        System.out.println(">>> fWord() test output start <<<");

        String s = instance.fWord();
        String s_f = instance.fWord_f();
        System.out.println(s);
        System.out.println(s_f);

        System.out.println(">>> fWord() test output end <<<");
        assertEquals(s, s_f);
    }


    /* ========================================================================================================= */
    /* ===================        NO MORE TESTS - ONLY UTILITY METHODS       =================================== */
    /* ========================================================================================================= */

    Random random = new Random();

    public static final int INTS_MAX_COUNT = 20;
    public static final int INTS_MAX_VAL = 99999;

    private List<Integer> randomIntegerList() {
        return Stream.generate(() -> random.nextInt(INTS_MAX_VAL))
                .filter(i -> i > 0)
                .limit(random.nextInt(INTS_MAX_COUNT))
                .collect(toList());
    }

    public static final int STRINGS_MIN_COUNT = 2;
    public static final int STRINGS_MAX_COUNT = 10;
    public static final int STRINGS_MIN_CHAR_COUNT = 2;
    public static final int STRINGS_MAX_CHAR_COUNT = 20;
    public static final int STRINGS_CHAR_COUNT_RANGE = STRINGS_MAX_CHAR_COUNT - STRINGS_MIN_CHAR_COUNT;
    public static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final int STRINGS_MIN_CHAR = 0;
    public static final int STRINGS_MAX_CHAR = STRINGS_MIN_CHAR + CHARS.length();

    private List<String> randomStringList() {
        return IntStream.range(0, STRINGS_MIN_COUNT + random.nextInt(STRINGS_MAX_COUNT - STRINGS_MIN_COUNT))
                .mapToObj(n -> random.ints(STRINGS_MIN_CHAR_COUNT + random.nextInt(STRINGS_CHAR_COUNT_RANGE),
                                           STRINGS_MIN_CHAR, STRINGS_MAX_CHAR)
                    .mapToObj(i -> new Character( CHARS.charAt(i)))
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString()
                ).collect(toList());
    }

}
