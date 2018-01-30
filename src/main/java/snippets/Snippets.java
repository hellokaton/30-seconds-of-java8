package snippets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Snippets {

    /**
     * Calculates the greatest common denominator (gcd) of an array of numbers
     *
     * @param numbers Array of numbers
     * @return gcd of array of numbers
     */
    public static OptionalInt gcd(int[] numbers) {
        return Arrays.stream(numbers)
                .reduce((a, b) -> gcd(a, b));
    }

    /**
     * Calculates the lowest common multiple (lcm) of an array of numbers.
     *
     * @param numbers Array of numbers
     * @return lcm of array of numbers
     */
    public static OptionalInt lcm(int[] numbers) {
        IntBinaryOperator lcm = (x, y) -> (x * y) / gcd(x, y);
        return Arrays.stream(numbers)
                .reduce((a, b) -> lcm.applyAsInt(a, b));
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Returns the maximum value in an array.
     *
     * @param numbers Array of numbers
     * @return maximum value in an array
     */
    public static OptionalInt arrayMax(int[] numbers) {
        return Arrays.stream(numbers).max();
    }

    /**
     * Returns the minimum value in an array.
     *
     * @param numbers Array of numbers
     * @return minimum value in an array
     */
    public static OptionalInt arrayMin(int[] numbers) {
        return Arrays.stream(numbers).min();
    }

    /**
     * Chunks an array into smaller arrays of a specified size.
     *
     * @param numbers Input array of numbers
     * @param size    The chunk size
     * @return Smaller chunks
     */
    public static int[][] chunk(int[] numbers, int size) {
        return IntStream.iterate(0, i -> i + size)
                .limit((long) Math.ceil((double) numbers.length / size))
                .mapToObj(cur -> Arrays.copyOfRange(numbers, cur, cur + size > numbers.length ? numbers.length : cur + size))
                .toArray(int[][]::new);
    }

    /**
     * Counts the occurrences of a value in an array.
     *
     * @param numbers Array of numbers
     * @param value   the value for which we have to count occurrences
     * @return count of total number of occurrences of the value
     */
    public static long countOccurrences(int[] numbers, int value) {
        return Arrays.stream(numbers)
                .filter(number -> number == value)
                .count();
    }

    /**
     * Deep flattens an array.
     *
     * @param input A nested array containing integers
     * @return flattened array
     */
    public static int[] deepFlatten(Object[] input) {
        return Arrays.stream(input)
                .flatMapToInt(o -> {
                    if (o instanceof Object[]) {
                        return Arrays.stream(deepFlatten((Object[]) o));
                    }
                    return IntStream.of((Integer) o);
                }).toArray();
    }

    /**
     * Returns the difference between two arrays.
     *
     * @param first  the first array
     * @param second the second array
     * @return Elements in first that are not in second
     */
    public static int[] difference(int[] first, int[] second) {
        Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(v -> !set.contains(v))
                .toArray();
    }

    /**
     * Filters out all values from an array for which the comparator function does not return true.
     *
     * @param first      the first array
     * @param second     the second array
     * @param comparator the comparator function
     * @return the resulting array
     */
    public static int[] differenceWith(int[] first, int[] second, IntBinaryOperator comparator) {
        return Arrays.stream(first)
                .filter(a ->
                        Arrays.stream(second)
                                .noneMatch(b -> comparator.applyAsInt(a, b) == 0)
                ).toArray();
    }

    /**
     * Returns all the distinct values of an array.
     *
     * @param elements ints
     * @return distinct values
     */
    public static int[] distinctValuesOfArray(int[] elements) {
        return Arrays.stream(elements).distinct().toArray();
    }

    /**
     * Removes elements in an array until the passed function returns true. Returns the remaining elements in the array.
     *
     * @param elements
     * @param condition
     * @return
     */
    public static int[] dropElements(int[] elements, IntPredicate condition) {
        while (elements.length > 0 && !condition.test(elements[0])) {
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }
        return elements;
    }

    /**
     * Returns a new array with n elements removed from the right
     *
     * @param elements
     * @param n        number of elements to remove
     * @return array after removing n elements
     */
    public static int[] dropRight(int[] elements, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n is less than 0");
        }
        return n < elements.length
                ? Arrays.copyOfRange(elements, 0, elements.length - n)
                : new int[0];
    }

    /**
     * Returns every nth element in an array.
     *
     * @param elements
     * @param nth
     * @return
     */
    public static int[] everyNth(int[] elements, int nth) {
        return IntStream.range(0, elements.length)
                .filter(i -> i % nth == nth - 1)
                .map(i -> elements[i])
                .toArray();
    }

    /**
     * Filters out the non-unique values in an array.
     * <p>
     * Use Array.stream().filter() for an array containing only the unique values.
     *
     * @param elements input array
     * @return unique values in the array
     */
    public static int[] filterNonUnique(int[] elements) {
        return Arrays.stream(elements)
                .filter(el -> indexOf(elements, el) == lastIndexOf(elements, el))
                .toArray();
    }

    /**
     * Find index of element in the array. Return -1 in case element does not exist.
     * <p>
     * Uses IntStream.range().filter() to find index of the element in the array.
     *
     * @param elements input array
     * @param el       element to find
     * @return index of the element
     */
    public static int indexOf(int[] elements, int el) {
        return IntStream.range(0, elements.length)
                .filter(idx -> elements[idx] == el)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Find last index of element in the array. Return -1 in case element does not exist.
     * <p>
     * Uses IntStream.iterate().limit().filter() to find index of the element in the array.
     *
     * @param elements input array
     * @param el       element to find
     * @return index of the element
     */
    public static int lastIndexOf(int[] elements, int el) {
        return IntStream.iterate(elements.length - 1, i -> i - 1)
                .limit(elements.length)
                .filter(idx -> elements[idx] == el)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Flattens an array.
     *
     * @param elements input array
     * @return flattened array
     */
    public static int[] flatten(Object[] elements) {
        return Arrays.stream(elements)
                .flatMapToInt(el -> el instanceof int[]
                        ? Arrays.stream((int[]) el)
                        : IntStream.of((int) el)
                ).toArray();
    }

    /**
     * Flattens an array up to the specified depth.
     *
     * @param elements input array
     * @param depth    depth to which to flatten array
     * @return flattened array
     */
    public static Object[] flattenDepth(Object[] elements, int depth) {
        if (depth == 0) {
            return elements;
        }
        return Arrays.stream(elements)
                .flatMap(el -> el instanceof Object[]
                        ? Arrays.stream(flattenDepth((Object[]) el, depth - 1))
                        : Arrays.stream(new Object[]{el})
                ).toArray();


    }

    /**
     * Groups the elements of an array based on the given function.
     *
     * @param elements input array
     * @param func     function
     * @param <T>      type parameter
     * @return grouped elements in a Map
     */
    public static <T, R> Map<R, List<T>> groupBy(T[] elements, Function<T, R> func) {
        return Arrays.stream(elements).collect(Collectors.groupingBy(func));
    }

    /**
     * Returns all the elements of an array except the last one.
     * Use Arrays.copyOfRange() to return all except the last one
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> T[] initial(T[] elements) {
        return Arrays.copyOfRange(elements, 0, elements.length - 1);
    }

    /**
     * Initializes an array containing the numbers in the specified range where start and end are inclusive.
     *
     * @param end
     * @param start
     * @return
     */
    public static int[] initializeArrayWithRange(int end, int start) {
        return IntStream.rangeClosed(start, end).toArray();
    }

    public static int[] initializeArrayWithValues(int n, int value) {
        return IntStream.generate(() -> value).limit(n).toArray();
    }

    public static int[] intersection(int[] first, int[] second) {
        Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(set::contains)
                .toArray();
    }

    public static <T extends Comparable<? super T>> int isSorted(T[] arr) {
        final int direction = arr[0].compareTo(arr[1]) < 0 ? 1 : -1;
        for (int i = 0; i < arr.length; i++) {
            T val = arr[i];
            if (i == arr.length - 1) return direction;
            else if ((val.compareTo(arr[i + 1]) * direction > 0)) return 0;
        }
        return direction;
    }

    public static <T> String join(T[] arr, String separator, String end) {
        return IntStream.range(0, arr.length)
                .mapToObj(i -> new SimpleEntry<>(i, arr[i]))
                .reduce("", (acc, val) -> val.getKey() == arr.length - 2
                        ? acc + val.getValue() + end
                        : val.getKey() == arr.length - 1 ? acc + val.getValue() : acc + val.getValue() + separator, (fst, snd) -> fst);
    }

    public static <T> String join(T[] arr, String separator) {
        return join(arr, separator, separator);
    }

    public static <T> String join(T[] arr) {
        return join(arr, ",");
    }

    public static <T> T nthElement(T[] arr, int n) {
        if (n > 0) {
            return Arrays.copyOfRange(arr, n, arr.length)[0];
        }
        return Arrays.copyOfRange(arr, arr.length + n, arr.length)[0];
    }

    public static <T, R> Map<T, R> pick(Map<T, R> obj, T[] arr) {
        return Arrays.stream(arr)
                .filter(obj::containsKey)
                .collect(Collectors.toMap(k -> k, obj::get));
    }

    public static Map<String, Object>[] reducedFilter(Map<String, Object>[] data, String[] keys, Predicate<Map<String, Object>> fn) {
        return Arrays.stream(data)
                .filter(fn)
                .map(el -> Arrays.stream(keys).filter(el::containsKey)
                        .collect(Collectors.toMap(Function.identity(), el::get)))
                .toArray((IntFunction<Map<String, Object>[]>) Map[]::new);
    }

    public static <T> T sample(T[] arr) {
        return arr[(int) Math.floor(Math.random() * arr.length)];
    }

    public static <T> T[] sampleSize(T[] input, int n) {
        T[] arr = Arrays.copyOf(input, input.length);
        int length = arr.length;
        int m = length;
        while (m > 0) {
            int i = (int) Math.floor(Math.random() * m--);
            T tmp = arr[i];
            arr[i] = arr[m];
            arr[m] = tmp;
        }
        return Arrays.copyOfRange(arr, 0, n > length ? length : n);
    }

    public static <T> T[] shuffle(T[] input) {
        T[] arr = Arrays.copyOf(input, input.length);
        int length = arr.length;
        int m = length;
        while (m > 0) {
            int i = (int) Math.floor(Math.random() * m--);
            T tmp = arr[i];
            arr[i] = arr[m];
            arr[m] = tmp;
        }
        return arr;
    }

    public static <T> T[] similarity(T[] first, T[] second) {
        return Arrays.stream(first)
                .filter(a -> Arrays.stream(second).anyMatch(b -> Objects.equals(a, b)))
                // Make a new array of first's runtime type, but empty content:
                .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }

    public static <T> T[] emptyArray(Class<T> clz) {
        return (T[]) Array.newInstance(clz, 0);
    }


    public static <T extends Comparable<? super T>> int sortedIndex(T[] arr, T el) {
        boolean isDescending = arr[0].compareTo(arr[arr.length - 1]) > 0;
        return IntStream.range(0, arr.length)
                .filter(i -> isDescending ? el.compareTo(arr[i]) >= 0 : el.compareTo(arr[i]) <= 0)
                .findFirst()
                .orElse(arr.length);
    }

    public static <T> T[] symmetricDifference(T[] first, T[] second) {
        Set<T> sA = new HashSet<>(Arrays.asList(first));
        Set<T> sB = new HashSet<>(Arrays.asList(second));

        return Stream.concat(
                Arrays.stream(first).filter(a -> !sB.contains(a)),
                Arrays.stream(second).filter(b -> !sA.contains(b))
        ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }

    public static <T> T[] tail(T[] arr) {
        return arr.length > 1
                ? Arrays.copyOfRange(arr, 1, arr.length)
                : arr;
    }

    public static <T> T[] take(T[] arr, int n) {
        return Arrays.copyOfRange(arr, 0, n);
    }

    public static <T> T[] takeRight(T[] arr, int n) {
        return Arrays.copyOfRange(arr, arr.length - n, arr.length);
    }

    public static <T> T[] union(T[] first, T[] second) {
        Set<T> set = new HashSet<>(Arrays.asList(first));
        set.addAll(Arrays.asList(second));
        return set.toArray((T[]) Arrays.copyOf(new Object[0], 0, first.getClass()));
    }

    public static <T> T[] without(T[] arr, T... elements) {
        List<T> excludeElements = Arrays.asList(elements);
        return Arrays.stream(arr)
                .filter(el -> !excludeElements.contains(el))
                .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, arr.getClass()));
    }

    public static List<Object[]> zip(Object[]... arrays) {
        OptionalInt max = Arrays.stream(arrays).mapToInt(arr -> arr.length).max();
        return IntStream.range(0, max.getAsInt())
                .mapToObj(i -> Arrays.stream(arrays)
                        .map(arr -> i < arr.length ? arr[i] : null)
                        .toArray())
                .collect(Collectors.toList());
    }

    public static Map<String, Object> zipObject(String[] props, Object[] values) {
        return IntStream.range(0, props.length)
                .mapToObj(i -> new SimpleEntry<>(props[i], i < values.length ? values[i] : null))
                .collect(
                        HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);
    }

    public static double average(int[] arr) {
        return IntStream.of(arr)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

    public static List<String> anagrams(String input) {
        if (input.length() <= 2) {
            return input.length() == 2
                    ? Arrays.asList(input, input.substring(1) + input.substring(0, 1))
                    : Collections.singletonList(input);
        }
        return IntStream.range(0, input.length())
                .mapToObj(i -> new SimpleEntry<>(i, input.substring(i, i + 1)))
                .flatMap(entry ->
                        anagrams(input.substring(0, entry.getKey()) + input.substring(entry.getKey() + 1))
                                .stream()
                                .map(s -> entry.getValue() + s))
                .collect(Collectors.toList());
    }

    public static int byteSize(String input) {
        // Read the link below to learn more
        // https://stackoverflow.com/questions/16270994/difference-between-string-length-and-string-getbytes-length
        return input.getBytes().length;
    }

    public static String capitalize(String input, boolean lowerRest) {
        return input.substring(0, 1).toUpperCase() +
                (lowerRest
                        ? input.substring(1, input.length()).toLowerCase()
                        : input.substring(1, input.length()));
    }

    public static String capitalizeEveryWord(final String input) {
        return Pattern.compile("\\b(?=\\w)").splitAsStream(input)
                .map(w -> capitalize(w, false))
                .collect(Collectors.joining());
    }

    public static int countVowels(String input) {
        return input.replaceAll("[^aeiouAEIOU]", "").length();
    }

    public static String escapeRegExp(String input) {
        return Pattern.quote(input);
    }

    public static String fromCamelCase(String input, String separator) {
        return input
                .replaceAll("([a-z\\d])([A-Z])", "$1" + separator + "$2")
                .toLowerCase();
    }

    public static boolean isAbsoluteUrl(String url) {
        return Pattern.compile("^[a-z][a-z0-9+.-]*:").matcher(url).find();
    }

    public static boolean isLowerCase(String input) {
        return Objects.equals(input, input.toLowerCase());
    }

    public static boolean isUpperCase(String input) {
        return Objects.equals(input, input.toUpperCase());
    }

    public static String mask(String input, int num, String mask) {
        int length = input.length();
        return num > 0
                ?
                input.substring(0, length - num).replaceAll(".", mask)
                        + input.substring(length - num)
                :
                input.substring(0, Math.negateExact(num))
                        + input.substring(Math.negateExact(num), length).replaceAll(".", mask);
    }

    public static boolean isPalindrome(String input) {
        String s = input.toLowerCase().replaceAll("[\\W_]", "");
        return Objects.equals(
                s,
                new StringBuilder(s).reverse().toString()
        );
    }

    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String sortCharactersInString(String input) {
        return Arrays.stream(input.split("")).sorted().collect(Collectors.joining());
    }

    public static String[] splitLines(String input) {
        return input.split("\\r?\\n");
    }

    public static String toCamelCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        String s = matchedParts.stream()
                .map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase())
                .collect(Collectors.joining());
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static String toKebabCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }

    public static List<String> match(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts;
    }

    public static String toSnakeCase(String input) {
        Matcher matcher = Pattern.compile("[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+").matcher(input);
        List<String> matchedParts = new ArrayList<>();
        while (matcher.find()) {
            matchedParts.add(matcher.group(0));
        }
        return matchedParts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.joining("_"));
    }

    public static String truncateString(String input, int num) {
        return input.length() > num
                ? input.substring(0, num > 3 ? num - 3 : num) + "..."
                : input;
    }

    public static String[] words(String input) {
        return Arrays.stream(input.split("[^a-zA-Z-]+"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    //Read the link below for more information
    // https://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
    public static String convertInputStreamToString(final InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    public static int[] randomInts(int total, int start, int end) {
        return ThreadLocalRandom.current().ints(total, start, end).toArray();
    }

    public String readFileAsString(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    public static String stackTraceAsString(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static <T> T[] concat(T[] first, T[] second) {
        return Stream.concat(
                Stream.of(first),
                Stream.of(second)
        ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
    }

    public static String getCurrentWorkingDirectoryPath() {
        return FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    }

    public static boolean isNumeric(final String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return IntStream.range(0, input.length())
                .allMatch(i -> Character.isDigit(input.charAt(i)));
    }

    public static int findNextPositivePowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    public static boolean isEven(final int value) {
        return (value & 0b1) == 0;
    }

    public static boolean isPowerOfTwo(final int value) {
        return value > 0 && ((value & (~value + 1)) == value);
    }

    public static int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static String tmpDirName() {
        String tmpDirName = System.getProperty("java.io.tmpdir");
        if (!tmpDirName.endsWith(File.separator)) {
            tmpDirName += File.separator;
        }

        return tmpDirName;
    }

    public static String osName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isDebuggerAttached() {
        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getInputArguments()
                .stream()
                .anyMatch(arg -> arg.contains("-agentlib:jdwp"));

    }

    /**
     * Input a line of numbers separated by space as integers
     * and return ArrayList of Integers.
     * eg. the String "1 2 3 4 5 6 7 8 9" is returned as an ArrayList of Integers.
     *
     * @param numbers range of numbers separated by space as a string
     * @return ArrayList of Integers
     */

    public static int[] stringToIntegers(String numbers) {
        return Arrays.stream(numbers.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    /* Class Utilities */

    public static List<Class<?>> getAllInterfaces(final Class<?> cls) {
        return Stream.concat(
                Arrays.stream(cls.getInterfaces()).flatMap(intf ->
                        Stream.concat(Stream.of(intf), getAllInterfaces(intf).stream())),
                cls.getSuperclass() == null ? Stream.empty() : getAllInterfaces(cls.getSuperclass()).stream()
        ).distinct().collect(Collectors.toList());
    }

    public static boolean isInnerClass(final Class<?> cls) {
        return cls != null && cls.getEnclosingClass() != null;
    }

    public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }
}