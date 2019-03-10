package ru.rzn.sbt.javaschool.functional;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Java 8: lambdas and stream API.<br/>
 * <br/>
 * Как работать с эти классом:<br/>
 * 0. Снять галочку в Ctrl+Alt+S -> Editor -> Inspections -> Java -> Java language level migration aids -> Java8 <br/>
 * 1. Всё уже написано за вас. Запускаем, проверяем зелёные тесты.<br/>
 * 2. У каждого метода, реализованного в императивном стиле, есть метод-дублёр (с суффиксом {@code _f} на конце).<br/>
 * 3. Реализуйте в методе-дублёре ту же функциональность, используя lambdas и stream API.<br/>
 * 4. Запустите тесты и проверьте себя.<br/>
 * 5. Перепишите свой код ещё более коротко и красиво :)<br/>
 * <br/>
 */
public class FunctionalLesson {

    /**
     * Упражнение 1. По порядку становись!<br/>
     * <br/>
     * + для тех, кому скучно: сформулируйте правило сортировки словами и запишите в комментарий
     *
     * Список целых чисел сортируется в порядке возрастания сумм цифр, из которых число состоит.
     * Если сумма цифр одинаковая, то в порядке возрастания чисел.
     * Например: было 45, 23. 4+5=9. 2+3 =5. 5<9. Отсортирует: 23, 45.
     * Было 20, 11. 1+1=2, 2=2, 11<20. Отсотирует: 11, 20.
     * Пример:
     * до
     * [45, 23, 20, 51, 14, 11]
     * после
     * [11, 20, 14, 23, 51, 45]
     */
    public void sort(List<Integer> list) {
        Collections.sort(list, new Comparator<Integer> () {
            @Override
            public int compare(Integer o1, Integer o2) {
                int s = 0, i1 = o1, i2 = o2;
                do {s += i1 % 10;} while ((i1 = i1/10) > 0);
                do {s -= i2 % 10;} while ((i2 = i2/10) > 0);
                return s != 0 ? s : o1 - o2;
            }
        });
    }

    public void sort_f(List<Integer> list) {
        Collections.sort(list, (o1, o2)-> {
                int s = 0, i1 = o1, i2 = o2;
                do {s += i1 % 10;} while ((i1 = i1/10) > 0);
                do {s -= i2 % 10;} while ((i2 = i2/10) > 0);
                return s != 0 ? s : o1 - o2;
         });
    }

    /**
     * Упражнение 2. Давай короче!<br/>
     * <br/>
     * 1. Сократите запись, используя метод {@link Iterable#forEach(Consumer)}.<br/>
     * 2. Сократите ещё немного, используя ссылку на метод {@link java.io.PrintStream#println(String)}
     * объекта системного потока вывода.<br/>
     */
    public void sout(List<String> list) {
        for (String s: list) {
            out.println(s);
        }

    }

    public void sout_f(List<String> list) {
        list.forEach(out::println);
    }

    /**
     * Упражнение 3. Happy Hunger Games!
     */
    public void mayTheOddsBeEverInYourFavor(List<String> list) {
        Iterator<String> i = list.iterator();
        while (i.hasNext()) {
            if (i.next().length() % 2 == 0) {
                i.remove();
            }
        }
    }

    public void mayTheOddsBeEverInYourFavor_f(List<String> list) {
        list.removeIf(e->e.length() % 2 == 0);
    }

    /**
     * Упражнение 4. The answer is 42.<br/>
     */
    public void answer42(List<String> myStrings) {
        ListIterator<String> i = myStrings.listIterator();
        while (i.hasNext()) {
            i.set(i.next().toUpperCase());
        }
    }

    public void answer42_f(List<String> myStrings) {
        //myStrings.replaceAll(e->e.toUpperCase());
        myStrings.replaceAll(String::toUpperCase);
    }

    /*
     * Упражнение 5. The answer has to be 42.<br/>
     * Перепишите тело метода answer42_f так, чтобы оно уложилось в 42 символа (не считая пробельных символов)
     */

    /**
     * Упражнение 6. Шифровка в центр.
     */
    public String ustas2alex(List<String> words) {
        StringBuilder sb = new StringBuilder(words.size());
        for (String w: words) {
            sb.append(w.charAt(w.length() / 2));
        }
        return sb.toString();
    }

    public String ustas2alex_f(List<String> words) {
//        v1 lambda
//        StringBuilder sb = new StringBuilder(words.size());
//        words.forEach(w->sb.append(w.charAt(w.length() / 2)));
//        return sb.toString();
//        16. Упражнения 6 и 7 реализуйте с помощью parallelStream
        StringBuilder sb = new StringBuilder(words.size());
        words.parallelStream()
                .forEachOrdered(w->sb.append(w.charAt(w.length() / 2)));
        return sb.toString();
    }

    /**
     * Упражнение 7. Properties.
     */
    public String properties(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> e: map.entrySet()) {
            sb.append(String.format("%s=%s\n", e.getKey(), e.getValue()));
        }
        return sb.toString();
    }

    public String properties_f(Map<String, Object> map) {
//        v1 lambda
//        StringBuilder sb = new StringBuilder();
//        map.entrySet().forEach(e->sb.append(String.format("%s=%s\n", e.getKey(), e.getValue())));
//        return sb.toString();
//        16. Упражнения 6 и 7 реализуйте с помощью parallelStream
        StringBuilder sb = new StringBuilder();
        map.entrySet().parallelStream().forEachOrdered(e->sb.append(String.format("%s=%s\n", e.getKey(), e.getValue())));
        return sb.toString();
    }

    /**
     * Упражнение 8. cAPS lOCK mUST dIE
     */
    public List<String> lower(List<String> list) {
        List<String> newList = new ArrayList<>(list.size());
        for (String s: list) {
            newList.add(s.toLowerCase());
        }
        return newList;
    }

    public List<String> lower_f(List<String> list) {
        return list.stream().map(String::toLowerCase).collect(toList());
    }

    /**
     * Упражнение 9. dIE
     */
    public List<String> lowerAndOdd(List<String> list) {
        List<String> newList = new ArrayList<>(list.size());
        for (String s: list) {
            if(s.length() % 2 == 1) {
                newList.add(s.toLowerCase());
            }
        }
        return newList;
    }

    public List<String> lowerAndOdd_f(List<String> list) {
        return list.stream()
                .filter(s->s.length() % 2 == 1)
                .map(String::toLowerCase)
                .collect(toList());
    }

    public static final String THIS_FILE = "src/main/java/ru/rzn/sbt/javaschool/functional/FunctionalLesson.java";

    /**
     * Упражнение 10. Go count yourself!
     */
    public long count() throws Exception {
        long count = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))) {
            while (reader.readLine() != null) {
                count++;
            }
        }
        return count;
    }

    /** Hint: BufferedReader can produce a Stream! */
    public long count_f() throws Exception {
        long count = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))) {
            count = reader.lines().count();
        }
        return count;
    }

    public static final String WORD_DELIMITERS = "[\\s\\(\\+-.:;,]+";
    /**
     * Упражнение 11. Every word counts.
     */
    public long countWords() throws Exception {
        long count = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count+= line.split(WORD_DELIMITERS).length;
            }
        }
        return count;
    }

    public long countWords_f() throws Exception {
        long count = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))) {
            count = reader.lines()
                    .map(s->s.split(WORD_DELIMITERS))
                    .flatMap(Arrays::stream)
                    .count();
        }
        return count;
    }

    /**
     * Упражнение 12. F-word.
     */
    public String fWord() throws Exception {
        Set<String> words = new TreeSet<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] newWords = line.split(WORD_DELIMITERS);
                for (String w: newWords) {
                    if(w.toLowerCase().startsWith("f")) {
                        words.add(w);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (String w: words) {
            if(first) {
                first = false;
            } else {
                result.append(" ");
            }
            result.append(w);
        }
        return result.toString();
    }

    /** Hint: Collectors.joining() helps a lot! */
    public String fWord_f() throws Exception {
        String result = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(THIS_FILE))
        ) {
            result = reader.lines()
                    .map(s->s.split(WORD_DELIMITERS))
                    .flatMap(Arrays::stream)
                    .filter(s->s.toLowerCase().startsWith("f"))
                    .distinct()
                    .sorted()
                    .collect(Collectors.joining(" "));
        }
        return result;
    }

    /* 13. Сгенерировать список из случайного числа (не более N) случайных чисел от 0 до X
     * (Один из вариантов решения - метод randomIntegerList() в тесте к этому уроку)
     */
    public static List<Integer> randomIntegerList() {
        int N=10, X=500;
        Random random = new Random();
        List<Integer> intArray = random.ints(random.nextInt(N+1), 0, X)
                                        .mapToObj(m->Integer.valueOf(m))
                                        .collect(toList());
        out.println(intArray);
        return intArray;
    }


    /*14. Сгенерировать список из случайного числа (не менее N1 и не более N2) строк
     *    случайной длины (не менее M1 и не более M2), состоящих из случайных символов с кодами от C1 до C2.
     *    (Один из вариантов решения - метод randomStringList() в тесте к этому уроку,
     *    ещё один вариант - в методе logEverything_full в BasicsLessonTest)*/
    public static List<String> randomStringList() {
        Random random = new Random();
        int N2=10, N1=5, M2=15, M1=1, C2=128, C1=50;
        //version1
        List<String> ls1 = Stream.generate(()->
                                random.ints(random.nextInt(M2 - M1) + M1, C1, C2)
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString()
                            )
                .limit(random.nextInt(N2-N1)+N1)
                .collect(toList());
        out.println(ls1);
        //version 2
        List<String> ls = Stream.generate(()->
                            Stream.generate(()->String.valueOf(Character.toChars(random.nextInt(C2-C1)+C1)))
                            .limit(random.nextInt(M2-M1)+M1)
                            .collect(Collectors.joining())
                            )
                .limit(random.nextInt(N2-N1)+N1)
                .collect(toList());
        out.println(ls);
        return ls;
    }

    public static void main(String[] args) {
        randomIntegerList();
        randomStringList();
    }

    /*
     * Для тех, у кого осталось время:
     *
     *
     * 13. Сгенерировать список из случайного числа (не более N) случайных чисел от 0 до X
     * (Один из вариантов решения - метод randomIntegerList() в тесте к этому уроку)
     *
     * 14. Сгенерировать список из случайного числа (не менее N1 и не более N2) строк
     *    случайной длины (не менее M1 и не более M2), состоящих из случайных символов с кодами от C1 до C2.
     * (Один из вариантов решения - метод randomStringList() в тесте к этому уроку,
     *  ещё один вариант - в методе logEverything_full в BasicsLessonTest)
     *
     *
     * 15. В упражнении 12 код императивного метода написан человеком, который не учил Collections Framework.
     *    Оптимизируйте его.
     *    ----- Сделано
     *
     * 16. Упражнения 6 и 7 реализуйте с помощью parallelStream
     *    ----- Сделано
     *
     */

}
