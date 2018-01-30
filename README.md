# 30 seconds of java8

[![License](https://img.shields.io/badge/license-CC0--1.0-blue.svg)](https://github.com/biezhi/30-seconds-of-java8/blob/master/LICENSE) 
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com) 
[![Travis Build](https://travis-ci.org/biezhi/30-seconds-of-java8.svg?branch=master)](https://travis-ci.org/biezhi/30-seconds-of-java8)
[![@biezhi on zhihu](https://img.shields.io/badge/zhihu-%40biezhi-red.svg)](https://www.zhihu.com/people/biezhi)
[![](https://img.shields.io/github/followers/biezhi.svg?style=social&label=Follow%20Me)](https://github.com/biezhi)

> 你可以在30秒或更短时间内收集有用的Java8代码片段。

- 使用 <kbd>Ctrl</kbd> + <kbd>F</kbd> 或者 <kbd>command</kbd> + <kbd>F</kbd> 来查找代码piandUAN
- 代码片段基于 Java8，如果你还不熟悉可以在[这里](https://zhuanlan.zhihu.com/java8)学习。

## 目录

### 📚 Array (数组相关)

<details>
<summary>详细信息</summary>

* [`chunk`](#chunk)
* [`countOccurrences`](#countoccurrences)
* [`deepFlatten`](#deepflatten)
* [`difference`](#difference)
* [`differenceWith`](#differencewith)
* [`distinctValuesOfArray`](#distinctvaluesofarray)
* [`dropElements`](#dropelements)
* [`dropRight`](#dropright)
* [`everyNth`](#everynth)
* [`filterNonUnique`](#filternonunique)
* [`flatten`](#flatten)
* [`flattenDepth`](#flattendepth)
* [`groupBy`](#groupby)
* [`head`](#head)
* [`initial`](#initial)
* [`initializeArrayWithRange`](#initializearraywithrange)
* [`initializeArrayWithValues`](#initializearraywithvalues)
* [`intersection`](#intersection)
* [`isSorted`](#issorted)
* [`join`](#join)
* [`nthElement`](#nthelement)
* [`pick`](#pick)
* [`reducedFilter`](#reducedfilter)
* [`remove`](#remove)
* [`sample`](#sample)
* [`sampleSize`](#samplesize)
* [`shuffle`](#shuffle)
* [`similarity`](#similarity)
* [`sortedIndex`](#sortedindex)
* [`symmetricDifference`](#symmetricdifference)
* [`tail`](#tail)
* [`take`](#take)
* [`takeRight`](#takeright)
* [`union`](#union)
* [`without`](#without)
* [`zip`](#zip)
* [`zipObject`](#zipobject)

</details>

<br>[⬆ 回到顶部](#目录)

### ➗ Math (数学相关)

<details>
<summary>详细信息</summary>

* [`average`](#average)
* [`gcd`](#gcd)
* [`lcm`](#lcm)
* [`findNextPositivePowerOfTwo`](#findnextpositivepoweroftwo)
* [`isEven`](#iseven)
* [`isPowerOfTwo`](#ispoweroftwo)
* [`generateRandomInt`](#generaterandomint)

</details>

<br>[⬆ 回到顶部](#目录)


### 📜 String (字符串相关)

<details>
<summary>详细信息</summary>

* [`anagrams`](#anagrams)
* [`byteSize`](#bytesize)
* [`capitalize`](#capitalize)
* [`capitalizeEveryWord`](#capitalizeeveryword)
* [`countVowels`](#countvowels)
* [`escapeRegExp`](#escaperegexp)
* [`fromCamelCase`](#fromcamelcase)
* [`isAbsoluteURL`](#isabsoluteurl)
* [`isLowerCase`](#islowercase)
* [`isUpperCase`](#isuppercase)
* [`isPalindrome`](#ispalindrome)
* [`isNumeric`](#isnumeric)
* [`mask`](#mask)
* [`reverseString`](#reversestring)
* [`sortCharactersInString`](#sortcharactersinstring)
* [`splitLines`](#splitlines)
* [`toCamelCase`](#tocamelcase)
* [`toKebabCase`](#tokebabcase)
* [`match`](#match)
* [`toSnakeCase`](#tosnakecase)
* [`truncateString`](#truncatestring)
* [`words`](#words)
* [`stringToIntegers`](#stringtointegers)


</details>

<br>[⬆ 回到顶部](#目录)

### ⭕️ IO (IO流相关)

<details>
<summary>详细信息</summary>

* [`convertInputStreamToString`](#convertinputstreamtostring)
* [`readFileAsString`](#readfileasstring)
* [`getCurrentWorkingDirectoryPath`](#getcurrentworkingdirectorypath)
* [`tmpDirName`](#tmpdirname)

</details>

<br>[⬆ 回到顶部](#目录)

### ❌ Exception (异常相关)

<details>
<summary>详细信息</summary>

* [`stackTraceAsString`](#stacktraceasstring)

</details>

<br>[⬆ 回到顶部](#目录)

### 🖥 System (系统相关)

<details>
<summary>详细信息</summary>

- [`osName`](#osname)
- [`isDebuggerEnabled`](#isdebuggerenabled)

</details>

<br>[⬆ 回到顶部](#目录)

### 💡 Class (类相关)

<details>
<summary>详细信息</summary>

- [`getAllInterfaces`](#getallinterfaces)
- [`IsInnerClass`](#isinnerclass)

</details>

<br>[⬆ 回到顶部](#目录)

### 💎 Enum (枚举相关)

<details>
<summary>详细信息</summary>

- [`getEnumMap`](#getenummap)

</details>

<br>[⬆ 回到顶部](#目录)

## Array

### chunk

将数组分割成特定大小的小数组。

```java
public static int[][] chunk(int[] numbers, int size) {
    return IntStream.iterate(0, i -> i + size)
            .limit((long) Math.ceil((double) numbers.length / size))
            .mapToObj(cur -> Arrays.copyOfRange(numbers, cur, cur + size > numbers.length ? numbers.length : cur + size))
            .toArray(int[][]::new);
}
```

### concat

```java
public static <T> T[] concat(T[] first, T[] second) {
    return Stream.concat(
            Stream.of(first),
            Stream.of(second)
    ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
}
```

### countOccurrences

计算数组中某个值出现的次数。

使用 `Arrays.stream().filter().count()` 计算等于指定值的值的总数。

```java
public static long countOccurrences(int[] numbers, int value) {
    return Arrays.stream(numbers)
            .filter(number -> number == value)
            .count();
}
```

### deepFlatten

数组扁平化。

使用递归实现，`Arrays.stream().flatMapToInt()`

```java
public static int[] deepFlatten(Object[] input) {
    return Arrays.stream(input)
            .flatMapToInt(o -> {
                if (o instanceof Object[]) {
                    return Arrays.stream(deepFlatten((Object[]) o));
                }
                return IntStream.of((Integer) o);
            }).toArray();
}
```

### difference

返回两个数组之间的差异。

从 b 中创建一个集合，然后在 a 上使用 `Arrays.stream().filter()` 只保留 b 中不包含的值。

```java
public static int[] difference(int[] first, int[] second) {
    Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
    return Arrays.stream(first)
            .filter(v -> !set.contains(v))
            .toArray();
}
```

### differenceWith

从比较器函数不返回true的数组中筛选出所有值。

int的比较器是使用IntbinaryPerator函数来实现的。

使用 `Arrays.stream().filter()` 和 `Arrays.stream().noneMatch()` 查找相应的值。

```java
public static int[] differenceWith(int[] first, int[] second, IntBinaryOperator comparator) {
    return Arrays.stream(first)
            .filter(a ->
                    Arrays.stream(second)
                            .noneMatch(b -> comparator.applyAsInt(a, b) == 0)
            ).toArray();
}
```

### distinctValuesOfArray

返回数组的所有不同值。 

使用 `Arrays.stream().distinct()` 去除所有重复的值。

```java
public static int[] distinctValuesOfArray(int[] elements) {
    return Arrays.stream(elements).distinct().toArray();
}
```

### dropElements

移除数组中的元素，直到传递的函数返回true为止。返回数组中的其余元素。 

使用数组循环遍历数组，将数组的第一个元素删除，直到函数返回的值为真为止。返回其余的元素。

```java
public static int[] dropElements(int[] elements, IntPredicate condition) {
    while (elements.length > 0 && !condition.test(elements[0])) {
        elements = Arrays.copyOfRange(elements, 1, elements.length);
    }
    return elements;
}
```

### dropRight

返回一个新数组，从右边移除n个元素。 

检查n是否短于给定的数组，并使用 `Array.copyOfRange()` 以便对其进行相应的切片或返回一个空数组。

```java
public static int[] dropRight(int[] elements, int n) {
    if (n < 0) {
        throw new IllegalArgumentException("n is less than 0");
    }
    return n < elements.length
            ? Arrays.copyOfRange(elements, 0, elements.length - n)
            : new int[0];
}
```

### everyNth

返回数组中的每个第n个元素。 

使用 `IntStream.range().filter()` 创建一个新数组，该数组包含给定数组的每个第n个元素。

```java
public static int[] everyNth(int[] elements, int nth) {
     return IntStream.range(0, elements.length)
             .filter(i -> i % nth == nth - 1)
             .map(i -> elements[i])
             .toArray();
 }
```

### indexOf

查找数组中元素的索引，在不存在元素的情况下返回-1。 

使用 `IntStream.range().filter()` 查找数组中元素的索引。

```java
public static int indexOf(int[] elements, int el) {
    return IntStream.range(0, elements.length)
            .filter(idx -> elements[idx] == el)
            .findFirst()
            .orElse(-1);
}
```

### lastIndexOf

查找数组中元素的最后索引，在不存在元素的情况下返回-1。 

使用 `IntStream.iterate().limit().filter()` 查找数组中元素的索引。

```java
public static int lastIndexOf(int[] elements, int el) {
    return IntStream.iterate(elements.length - 1, i -> i - 1)
            .limit(elements.length)
            .filter(idx -> elements[idx] == el)
            .findFirst()
            .orElse(-1);
}
```

### filterNonUnique

筛选出数组中的非唯一值。 

对只包含唯一值的数组使用 `Arrays.stream().filter()`。

```java
public static int[] filterNonUnique(int[] elements) {
    return Arrays.stream(elements)
            .filter(el -> indexOf(elements, el) == lastIndexOf(elements, el))
            .toArray();
}
```

### flatten

使数组扁平。

使用 `Arrays.stream().flatMapToInt().toArray()` 创建一个新数组。


```java
public static int[] flatten(Object[] elements) {
    return Arrays.stream(elements)
            .flatMapToInt(el -> el instanceof int[]
                    ? Arrays.stream((int[]) el)
                    : IntStream.of((int) el)
            ).toArray();
}
```

### flattenDepth

将数组压平到指定的深度。

```java
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
```

### groupBy

根据给定函数对数组元素进行分组。

使用 `Arrays.stream().collect(Collectors.groupingBy())` 分组。

```java
public static <T, R> Map<R, List<T>> groupBy(T[] elements, Function<T, R> func) {
    return Arrays.stream(elements).collect(Collectors.groupingBy(func));
}
```

### initial

返回数组中除去最后一个的所有元素。

使用 `Arrays.copyOfRange()` 返回除最后一个之外的所有元素。

```java
public static <T> T[] initial(T[] elements) {
    return Arrays.copyOfRange(elements, 0, elements.length - 1);
}
```

### initializeArrayWithRange

初始化一个数组，该数组包含在指定范围内的数字，传入 `start` 和 `end`。

```java
public static int[] initializeArrayWithRange(int end, int start) {
    return IntStream.rangeClosed(start, end).toArray();
}
```

### initializeArrayWithValues

使用指定的值初始化并填充数组。

```java
public static int[] initializeArrayWithValues(int n, int value) {
    return IntStream.generate(() -> value).limit(n).toArray();
}
```

### intersection

返回两个数组中存在的元素列表。 

从第二步创建一个集合，然后在 a 上使用 `Arrays.stream().filter()` 来保存包含在 b 中的值。

```java
public static int[] intersection(int[] first, int[] second) {
    Set<Integer> set = Arrays.stream(second).boxed().collect(Collectors.toSet());
    return Arrays.stream(first)
            .filter(set::contains)
            .toArray();
}
```

### isSorted

如果数组按升序排序，则返回 `1`，如果数组按降序排序，返回 `-1`，如果没有排序，则返回 `0`。

计算前两个元素的排序 `direction`。使用for循环对数组进行迭代，并对它们进行成对比较。如果 `direction` 发生变化，则返回 `0`，
如果到达最后一个元素，则返回 `direction`。

```java
public static <T extends Comparable<? super T>> int isSorted(T[] arr) {
    final int direction = arr[0].compareTo(arr[1]) < 0 ? 1 : -1;
    for (int i = 0; i < arr.length; i++) {
        T val = arr[i];
        if (i == arr.length - 1) return direction;
        else if ((val.compareTo(arr[i + 1]) * direction > 0)) return 0;
    }
    return direction;
}
```

### join

将数组的所有元素连接到字符串中，并返回此字符串。

使用 `IntStream.range` 创建一个指定索引的数组。然后，使用 `Stream.reduce` 将元素组合成字符串。

```java
public static <T> String join(T[] arr, String separator, String end) {
    return IntStream.range(0, arr.length)
            .mapToObj(i -> new SimpleEntry<>(i, arr[i]))
            .reduce("", (acc, val) -> val.getKey() == arr.length - 2
                    ? acc + val.getValue() + end
                    : val.getKey() == arr.length - 1 ? acc + val.getValue() : acc + val.getValue() + separator, (fst, snd) -> fst);
}
```

### nthElement

返回数组的第n个元素。

Use `Arrays.copyOfRange()` 优先得到包含第n个元素的数组。 

```Java
public static <T> T nthElement(T[] arr, int n) {
    if (n > 0) {
        return Arrays.copyOfRange(arr, n, arr.length)[0];
    }
    return Arrays.copyOfRange(arr, arr.length + n, arr.length)[0];
}
```

### pick

从对象中选择与给定键对应的键值对。

使用 `Arrays.stream` 过滤 `arr` 中存在的所有键。然后，使用 `Collectors.toMap` 将所有的key转换为Map。

```java
public static <T, R> Map<T, R> pick(Map<T, R> obj, T[] arr) {
    return Arrays.stream(arr)
            .filter(obj::containsKey)
            .collect(Collectors.toMap(k -> k, obj::get));
}
```

### reducedFilter

根据条件筛选对象数组，同时筛选出未指定的键。

使用 `Arrays.stream().filter()` 根据谓词 `fn` 过滤数组，以便返回条件为真的对象。
对于每个过滤的Map对象，创建一个新的Map，其中包含 `keys` 中的键。最后，将Map对象收集到一个数组中。

```java
public static Map<String, Object>[] reducedFilter(Map<String, Object>[] data, String[] keys, Predicate<Map<String, Object>> fn) {
    return Arrays.stream(data)
            .filter(fn)
            .map(el -> Arrays.stream(keys).filter(el::containsKey)
                    .collect(Collectors.toMap(Function.identity(), el::get)))
            .toArray((IntFunction<Map<String, Object>[]>) Map[]::new);
}
```

### sample

从数组中返回一个随机元素。

使用 `Math.Randoman()` 生成一个随机数，然后将它乘以数组的 `length`，然后使用 `Math.floor()` 获得一个最近的整数，该方法也适用于字符串。

```java
public static <T> T sample(T[] arr) {
    return arr[(int) Math.floor(Math.random() * arr.length)];
}
```

### sampleSize

从 `array` 到 `array` 大小的唯一键获取 `n` 个随机元素。

根据[Fisher-Yates算法](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)，使用 `Array.copyOfRange()` 获得优先的 `n` 个元素。

```java
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
```

### shuffle

Randomizes the order of the values of an array, returning a new array.

Uses the [Fisher-Yates algorithm](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle) to reorder the elements of the array.

```java
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
```

### similarity

Returns an array of elements that appear in both arrays.

Use `Arrays.stream().filter()` to remove values that are not part of `second`, determined using `Arrays.stream().anyMatch()`.

```java
public static <T> T[] similarity(T[] first, T[] second) {
    return Arrays.stream(first)
            .filter(a -> Arrays.stream(second).anyMatch(b -> Objects.equals(a, b)))
            // Make a new array of first's runtime type, but empty content:
            .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
}
```

### sortedIndex

Returns the lowest index at which value should be inserted into array in order to maintain its sort order.

Check if the array is sorted in descending order (loosely). Use `IntStream.range().filter()` to find the appropriate index where the element should be inserted.

```java
public static <T extends Comparable<? super T>> int sortedIndex(T[] arr, T el) {
    boolean isDescending = arr[0].compareTo(arr[arr.length - 1]) > 0;
    return IntStream.range(0, arr.length)
            .filter(i -> isDescending ? el.compareTo(arr[i]) >= 0 : el.compareTo(arr[i]) <= 0)
            .findFirst()
            .orElse(arr.length);
}
```

### symmetricDifference

Returns the symmetric difference between two arrays.

Create a `Set` from each array, then use `Arrays.stream().filter()` on each of them to only keep values not contained in the other. Finally, concatenate both arrays and create a new array and return it.

```java
public static <T> T[] symmetricDifference(T[] first, T[] second) {
    Set<T> sA = new HashSet<>(Arrays.asList(first));
    Set<T> sB = new HashSet<>(Arrays.asList(second));

    return Stream.concat(
            Arrays.stream(first).filter(a -> !sB.contains(a)),
            Arrays.stream(second).filter(b -> !sA.contains(b))
    ).toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));
}
```

### tail

Returns all elements in an array except for the first one.

Return `Arrays.copyOfRange(1)` if the array's `length` is more than `1`, otherwise, return the whole array.

```java
public static <T> T[] tail(T[] arr) {
    return arr.length > 1
            ? Arrays.copyOfRange(arr, 1, arr.length)
            : arr;
}
```

### take

Returns an array with n elements removed from the beginning.

```java
public static <T> T[] take(T[] arr, int n) {
    return Arrays.copyOfRange(arr, 0, n);
}
```

### takeRight

Returns an array with n elements removed from the end.

Use `Arrays.copyOfRange()` to create a slice of the array with `n` elements taken from the end.

```java
public static <T> T[] takeRight(T[] arr, int n) {
    return Arrays.copyOfRange(arr, arr.length - n, arr.length);
}
```

### union

Returns every element that exists in any of the two arrays once.

Create a `Set` with all values of `a` and `b` and convert to an array.

```Java
public static <T> T[] union(T[] first, T[] second) {
    Set<T> set = new HashSet<>(Arrays.asList(first));
    set.addAll(Arrays.asList(second));
    return set.toArray((T[]) Arrays.copyOf(new Object[0], 0, first.getClass()));
}
```

### without

Filters out the elements of an array, that have one of the specified values.

Use `Arrays.strean().filter()` to create an array excluding(using `!Arrays.asList(elements).contains()`) all given values.

```java
public static <T> T[] without(T[] arr, T... elements) {
    List<T> excludeElements = Arrays.asList(elements);
    return Arrays.stream(arr)
            .filter(el -> !excludeElements.contains(el))
            .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, arr.getClass()));
}
```

### zip

Creates an array of elements, grouped based on the position in the original arrays.

```java
public static List<Object[]> zip(Object[]... arrays) {
    OptionalInt max = Arrays.stream(arrays).mapToInt(arr -> arr.length).max();
    return IntStream.range(0, max.getAsInt())
            .mapToObj(i -> Arrays.stream(arrays)
                    .map(arr -> i < arr.length ? arr[i] : null)
                    .toArray())
            .collect(Collectors.toList());
}
```

### zipObject

Given an array of valid property identifiers and an array of values, return an object associating the properties to the values.

```java
public static Map<String, Object> zipObject(String[] props, Object[] values) {
    return IntStream.range(0, props.length)
            .mapToObj(i -> new SimpleEntry<>(props[i], i < values.length ? values[i] : null))
            .collect(
                    HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), HashMap::putAll);
}
```

## Maths

### average

Returns the average of an of two or more numbers.

```java
public static double average(int[] arr) {
    return IntStream.of(arr)
            .average()
            .orElseThrow(() -> new IllegalArgumentException("Array is empty"));
}
```

### gcd

Calculates the greatest common denominator (gcd) of an array of numbers.

Use Arrays.stream().reduce() and the gcd formula (uses recursion) to calculate the greatest common denominator of an array of numbers.

```java
public static OptionalInt gcd(int[] numbers) {
    return Arrays.stream(numbers)
            .reduce((a, b) -> gcd(a, b));
}

private static int gcd(int a, int b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### lcm

Calculates the lowest common multiple (lcm) of an array of numbers.

Use Arrays.stream().reduce() and the lcm formula (uses recursion) to calculate the lowest common multiple of an array of numbers.

```java
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
```

### findNextPositivePowerOfTwo

Finds the next power of two greater than or equal to the value.

This method uses left ship operator to shift 1 by the value on the right side. The right side is calculated using the `Integer.numberOfLeadingZeros` method.

The `Integer.numberOfLeadingZeros` give the number of zeros leading the value. For example, calling `Integer.numberOfLeadingZeros(3)` would give value as 30. This is because 3 is represented in binary as `11`. As integer has 32 bits, so there are 30 bits with 0.  The right side of the left shift operator becomes `32-30 = 2`. Left shifting 1 by 2 i.e. `001 << 2` would be `100`. `100` in decimal is equal to `4`.

```java
public static int findNextPositivePowerOfTwo(int value) {
    return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
}
```

### isEven

Check if the number is even.

This method uses bitwise & operator. The `0b1` is the binary representation of 1. Since, Java 7 you can write binary literals by prefixing them with either `0b` or `0B`.  The & operator will return 0 when number is even. For example, `IsEven(4)` would result in `100` `&` `001`.  The result of `&` will be `000`.

```java
public static boolean isEven(final int value) {
    return (value & 0b1) == 0;
}
```

### isPowerOfTwo

Checks if a value is positive power of two.

To understand how it works let's assume we made a call `IsPowerOfTwo(4)`.

As value is greater than 0, so right side of the `&&` operator will be evaluated. 

The result of `(~value + 1)` is equal to value itself. `~100 + 001` => `011 + 001` => `100`. This is equal to value.

The result of `(value & value)` is value. `100` & `100` => `100`.

This will value the expression to true as value is equal to value.

```Java
public static boolean isPowerOfTwo(final int value) {
    return value > 0 && ((value & (~value + 1)) == value);
}
```

### generateRandomInt

Generate a random integer between `Integer.MIN_VALUE` and `Integer.MAX_VALUE`.

```java
public static int generateRandomInt() {
    return ThreadLocalRandom.current().nextInt();
}
```

## String

### anagrams

Generates all anagrams of a string (contains duplicates).

```java
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
```

### byteSize

Returns the length of a string in bytes.

```java
public static int byteSize(String input) {
    return input.getBytes().length;
}
```

### capitalize

Capitalizes the first letter of a string.

```Java
public static String capitalize(String input, boolean lowerRest) {
    return input.substring(0, 1).toUpperCase() +
            (lowerRest
                    ? input.substring(1, input.length()).toLowerCase()
                    : input.substring(1, input.length()));
}
```

### capitalizeEveryWord

Capitalizes the first letter of every word in a string.

```java
public static String capitalizeEveryWord(final String input) {
    return Pattern.compile("\\b(?=\\w)").splitAsStream(input)
            .map(w -> capitalize(w, false))
            .collect(Collectors.joining());
}
```

### countVowels

Retuns `number` of vowels in provided string.

```java
public static int countVowels(String input) {
    return input.replaceAll("[^aeiouAEIOU]", "").length();
}
```

### escapeRegExp

Escapes a string to use in a regular expression.

```java
public static String escapeRegExp(String input) {
    return Pattern.quote(input);
}
```

### fromCamelCase

Converts a string from camelcase.

```java
public static String fromCamelCase(String input, String separator) {
    return input
            .replaceAll("([a-z\\d])([A-Z])", "$1" + separator + "$2")
            .toLowerCase();
}
```

### isAbsoluteUrl

Returns `true` if the given string is an absolute URL, `false` otherwise.

```java
public static boolean isAbsoluteUrl(String url) {
    return Pattern.compile("^[a-z][a-z0-9+.-]*:").matcher(url).find();
}
```

### isLowerCase

Checks if a string is lower case.

```java
public static boolean isLowerCase(String input) {
    return Objects.equals(input, input.toLowerCase());
}
```

### isUpperCase

Checks if a string is upper case.

```java
public static boolean isUpperCase(String input) {
    return Objects.equals(input, input.toUpperCase());
}
```

### isPalindrome

Checks if a string is palindrome.

```java
public static boolean isPalindrome(String input) {
    String s = input.toLowerCase().replaceAll("[\\W_]", "");
    return Objects.equals(
            s,
            new StringBuilder(s).reverse().toString()
    );
}
```

### isNumeric

Checks if a string is numeric.

```java
public static boolean isNumeric(final String input) {
    return IntStream.range(0, input.length())
            .allMatch(i -> Character.isDigit(input.charAt(i)));
}
```

### mask

Replaces all but the last `num` of characters with the specified mask character.

```Java
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
```

### reverseString

Reverses a string.

```java
public static String reverseString(String input) {
    return new StringBuilder(input).reverse().toString();
}
```

### sortCharactersInString

Alphabetically sorts the characters in a string.

```java
public static String sortCharactersInString(String input) {
    return Arrays.stream(input.split("")).sorted().collect(Collectors.joining());
}
```

 ### splitLines

Splits a multiline string into an array of lines.

```java
public static String[] splitLines(String input) {
    return input.split("\\r?\\n");
}
```

### toCamelCase

Converts a string to camelcase.

```java
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

```

### toKebabCase

Converts a string to kebab case.

```java
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
```

### match

```java
public static List<String> match(String input, String regex) {
    Matcher matcher = Pattern.compile(regex).matcher(input);
    List<String> matchedParts = new ArrayList<>();
    while (matcher.find()) {
        matchedParts.add(matcher.group(0));
    }
    return matchedParts;
}

```

### toSnakeCase

Converts a string to snake case.

```java
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
```

### truncateString

Truncates a string up to a specified length.

```java
public static String truncateString(String input, int num) {
    return input.length() > num
            ? input.substring(0, num > 3 ? num - 3 : num) + "..."
            : input;
}
```

### words

Converts a given string into an array of words.

```Java
public static String[] words(String input) {
    return Arrays.stream(input.split("[^a-zA-Z-]+"))
            .filter(s -> !s.isEmpty())
            .toArray(String[]::new);
}
```

### stringToIntegers

Converts a String of numbers separated by space to an array of ints.

```Java
public static int[] stringToIntegers(String numbers) {
        return Arrays.stream(numbers.split(" ")).mapToInt(Integer::parseInt).toArray();
}
```


## IO

### convertInputStreamToString

Converts InputStream to a String.

```java
public static String convertInputStreamToString(final InputStream in) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    while ((length = in.read(buffer)) != -1) {
        result.write(buffer, 0, length);
    }
    return result.toString(StandardCharsets.UTF_8.name());
}
```

### readFileAsString

Reads content of a file to a String

```java
public String readFileAsString(Path path) throws IOException {
    return new String(Files.readAllBytes(path));
}
```

### getCurrentWorkingDirectoryPath

```java
public static String getCurrentWorkingDirectoryPath() {
    return FileSystems.getDefault().getPath("").toAbsolutePath().toString();
}
```

### tmpDirName

Returns the value of `java.io.tmpdir` system property. It appends separator if not present at the end.

```java
public static String tmpDirName() {
    String tmpDirName = System.getProperty("java.io.tmpdir");
    if (!tmpDirName.endsWith(File.separator)) {
        tmpDirName += File.separator;
    }

    return tmpDirName;
}
```

## Exception

### stackTraceAsString

Converts exception stack trace to a String.

```java
public static String stackTraceAsString(final Throwable throwable) {
    final StringWriter sw = new StringWriter();
    throwable.printStackTrace(new PrintWriter(sw));
    return sw.toString();
}
```
## System

### osName

Gets the name of the operating system as a lower case String.

```java
public static String osName() {
    return System.getProperty("os.name").toLowerCase();
}
```

### isDebuggerEnabled

Checks if debugger is attached to the JVM.

```java
public static boolean isDebuggerAttached() {
    final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    return runtimeMXBean.getInputArguments()
            .stream()
            .anyMatch(arg -> arg.contains("-agentlib:jdwp"));

}
```

## Class

### getAllInterfaces

This methods returns all the interfaces implemented by the given class and its superclasses.

This method works by concatenating two streams. The first stream is recursively built by creating a stream with the interface and all the interfaces implemented by the the interface. The second stream does the same for the super classes. The result is the concatenation of the two streams after removing the duplicates.

```java
public static List<Class<?>> getAllInterfaces(Class<?> cls) {
    return Stream.concat(
            Arrays.stream(cls.getInterfaces()).flatMap(intf ->
                    Stream.concat(Stream.of(intf), getAllInterfaces(intf).stream())),
            cls.getSuperclass() == null ? Stream.empty() : getAllInterfaces(cls.getSuperclass()).stream()
    ).distinct().collect(Collectors.toList());
}
```

### isInnerClass

This method checks if the specified class is an inner class or static nested class

```Java
public static boolean isInnerClass(final Class<?> cls) {
    return cls != null && cls.getEnclosingClass() != null;
}
```

## Enum

### getEnumMap

Converts to enum to Map where key is the name and value is Enum itself.

```java
public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants())
            .collect(Collectors.toMap(Enum::name, Function.identity()));
}
```

