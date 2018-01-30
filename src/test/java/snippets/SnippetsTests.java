package snippets;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SnippetsTests {

    @Test
    public void gcd_of_array_containing_1_to_5_is_1() throws Exception {
        OptionalInt gcd = Snippets.gcd(new int[]{1, 2, 3, 4, 5});
        assertThat(gcd).isNotEmpty();
        assertThat(gcd).hasValue(1);
    }

    @Test
    public void gcd_of_array_containing_4_8_and_12_is_4() throws Exception {
        OptionalInt gcd = Snippets.gcd(new int[]{4, 8, 12});
        assertThat(gcd).isNotEmpty();
        assertThat(gcd).hasValue(4);
    }

    @Test
    public void lcm_of_array_containing_1_to_5_is_60() throws Exception {
        OptionalInt lcm = Snippets.lcm(new int[]{1, 2, 3, 4, 5});
        assertThat(lcm).isNotEmpty();
        assertThat(lcm).hasValue(60);
    }

    @Test
    public void lcm_of_array_containing_4_8_and_12_is_24() throws Exception {
        OptionalInt lcm = Snippets.lcm(new int[]{4, 8, 12});
        assertThat(lcm).isNotEmpty();
        assertThat(lcm).hasValue(24);
    }

    @Test
    public void max_of_array_containing_10_1_and_5_is_10() throws Exception {
        OptionalInt max = Snippets.arrayMax(new int[]{10, 1, 5});
        assertThat(max).hasValue(10);
    }

    @Test
    public void min_of_array_containing_10_1_and_5_is_10() throws Exception {
        OptionalInt min = Snippets.arrayMin(new int[]{10, 1, 5});
        assertThat(min).hasValue(1);
    }

    @Test
    public void chunk_breaks_input_array__with_odd_length() throws Exception {
        int[][] chunks = Snippets.chunk(new int[]{1, 2, 3, 4, 5}, 2);
        assertThat(chunks)
                .containsExactly(
                        new int[]{1, 2},
                        new int[]{3, 4},
                        new int[]{5}
                );
    }

    @Test
    public void chunk_breaks_input_array__with_event_length() throws Exception {
        int[][] chunks = Snippets.chunk(new int[]{1, 2, 3, 4, 5, 6}, 2);
        assertThat(chunks)
                .containsExactly(
                        new int[]{1, 2},
                        new int[]{3, 4},
                        new int[]{5, 6}
                );
    }

    @Test
    public void countOccurrences_counts_occurrences_of_a_value() throws Exception {
        long count = Snippets.countOccurrences(new int[]{1, 1, 2, 1, 2, 3}, 1);
        assertThat(count).isEqualTo(3);
    }

    @Test
    public void deepFlatten_flatten_a_deeply_nested_array() throws Exception {
        int[] flatten = Snippets.deepFlatten(
                new Object[]{1, new Object[]{2}, new Object[]{3, 4, 5}}
        );

        assertThat(flatten).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void difference_between_array_with_1_2_3_and_array_with_1_2_4_is_3() throws Exception {
        int[] difference = Snippets.difference(new int[]{1, 2, 3}, new int[]{1, 2, 4});
        assertThat(difference).isEqualTo(new int[]{3});
    }

    @Test
    public void difference_between_array_with_1_2_3_and_array_with_1_2_3_is_empty_array() throws Exception {
        int[] difference = Snippets.difference(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertThat(difference).isEmpty();
    }

    @Test
    public void differenceWith_return_all_squares_that_do_not_exist_in_second() throws Exception {
        int[] difference = Snippets.differenceWith(
                new int[]{1, 4, 9, 16, 25},
                new int[]{1, 2, 3, 6, 7},
                (o1, o2) -> o1 - (o2 * o2)
        );

        assertThat(difference).isEqualTo(new int[]{16, 25});
    }

    @Test
    public void differenceWith_returns_empty_array_when_two_arrays_are_equal_as_per_comparison_operation() throws Exception {
        int[] difference = Snippets.differenceWith(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3},
                (o1, o2) -> o1 - o2
        );

        assertThat(difference).isEmpty();
    }

    @Test
    public void differenceWith_returns_first_array_when_elements_in_second_array_are_not_comparable_as_per_comparison_operation() throws Exception {
        int[] difference = Snippets.differenceWith(
                new int[]{1, 2, 3},
                new int[]{10, 11, 12},
                (o1, o2) -> o1 - o2
        );

        assertThat(difference).isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    public void distinct_remove_all_duplicate_values_from_an_array() throws Exception {
        int[] distinct = Snippets.distinctValuesOfArray(new int[]{1, 2, 2, 3, 4, 4, 5});
        assertThat(distinct).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void drop_elements_less_than_3() throws Exception {
        int[] elements = Snippets.dropElements(new int[]{1, 2, 3, 4}, i -> i >= 3);
        assertThat(elements).isEqualTo(new int[]{3, 4});
    }

    @Test
    public void drop_elements_returns_empty_array_when_no_element_match_the_condition() throws Exception {
        int[] elements = Snippets.dropElements(new int[]{1, 2, 3, 4}, i -> i < 1);
        assertThat(elements).isEmpty();
    }

    @Test
    public void drop_elements__return_all_elements_when_all_elements_match_the_condition() throws Exception {
        int[] elements = Snippets.dropElements(new int[]{1, 2, 3, 4}, i -> i <= 4);
        assertThat(elements).isEqualTo(new int[]{1, 2, 3, 4});
    }

    @Test
    public void dropRight_remove_n_elements_from_right() throws Exception {
        int[] elements = Snippets.dropRight(new int[]{1, 2, 3}, 1);
        assertThat(elements).isEqualTo(new int[]{1, 2});

        elements = Snippets.dropRight(new int[]{1, 2, 3}, 2);
        assertThat(elements).isEqualTo(new int[]{1});

        elements = Snippets.dropRight(new int[]{1, 2, 3}, 3);
        assertThat(elements).isEmpty();

        elements = Snippets.dropRight(new int[]{1, 2, 3}, 42);
        assertThat(elements).isEmpty();
    }

    @Test
    public void everyNth_return_every_2nd_element() throws Exception {
        int[] elements = Snippets.everyNth(new int[]{1, 2, 3, 4, 5, 6}, 2);
        assertThat(elements).isEqualTo(new int[]{2, 4, 6});
    }

    @Test
    public void filterNonUnique_return_unique_elements() throws Exception {
        int[] elements = Snippets.filterNonUnique(new int[]{1, 2, 2, 3, 4, 4, 5});
        assertThat(elements).isEqualTo(new int[]{1, 3, 5});
    }

    @Test
    public void filterNonUnique_return_same_array_when_all_unique() throws Exception {
        int[] elements = Snippets.filterNonUnique(new int[]{1, 2, 3, 4, 5});
        assertThat(elements).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void filterNonUnique_return_empty_array_when_all_duplicated() throws Exception {
        int[] elements = Snippets.filterNonUnique(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5});
        assertThat(elements).isEmpty();
    }

    @Test
    public void flatten_flat_one_level_array() throws Exception {
        int[] flatten = Snippets.flatten(new Object[]{1, new int[]{2}, 3, 4});
        assertThat(flatten).isEqualTo(new int[]{1, 2, 3, 4});
    }

    @Test
    public void flattenDepth_flatten_to_specified_depth() throws Exception {
        Object[] input = {
                1,
                new Object[]{2},
                new Object[]{
                        new Object[]{
                                new Object[]{
                                        3
                                },
                                4
                        }, 5
                }
        };

        Object[] flatten = Snippets.flattenDepth(input, 2);
        assertThat(flatten).isEqualTo(new Object[]{1, 2, new Object[]{3}, 4, 5});
    }

    @Test
    public void group_elements_by_length() throws Exception {
        Map<Integer, List<String>> groups = Snippets.groupBy(new String[]{"one", "two", "three"}, String::length);
        assertThat(groups)
                .containsExactly(
                        new SimpleEntry<>(3, Arrays.asList("one", "two")),
                        new SimpleEntry<>(5, Collections.singletonList("three"))
                );
    }

    @Test
    public void initial_return_array_except_last_element() throws Exception {
        Integer[] initial = Snippets.initial(new Integer[]{1, 2, 3});
        assertThat(initial).isEqualTo(new Integer[]{1, 2});
    }

    @Test
    public void initializeArrayWithRange_from_1_to_5() throws Exception {
        int[] numbers = Snippets.initializeArrayWithRange(5, 1);
        assertThat(numbers).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void initializeArrayWithValues() throws Exception {
        int[] elements = Snippets.initializeArrayWithValues(5, 2);
        assertThat(elements).isEqualTo(new int[]{2, 2, 2, 2, 2});
    }

    @Test
    public void intersection_between_two_arrays() throws Exception {
        int[] elements = Snippets.intersection(new int[]{1, 2, 3}, new int[]{4, 3, 2});
        assertThat(elements).isEqualTo(new int[]{2, 3});
    }

    @Test
    public void isSorted_return_1_when_array_sorted_is_ascending_order() throws Exception {
        int sorted = Snippets.isSorted(new Integer[]{0, 1, 2, 3});
        assertThat(sorted).isEqualTo(1);

        sorted = Snippets.isSorted(new Integer[]{0, 1, 2, 2});
        assertThat(sorted).isEqualTo(1);
    }

    @Test
    public void isSorted_return_minus_1_when_array_sorted_in_descending_order() throws Exception {
        int sorted = Snippets.isSorted(new Integer[]{3, 2, 1, 0});
        assertThat(sorted).isEqualTo(-1);

        sorted = Snippets.isSorted(new Integer[]{3, 3, 2, 1, 0});
        assertThat(sorted).isEqualTo(-1);
    }

    @Test
    public void isSorted_returns_0_when_array_is_not_sorted() throws Exception {
        int sorted = Snippets.isSorted(new Integer[]{3, 4, 1, 0});
        assertThat(sorted).isEqualTo(0);
    }

    @Test
    public void join_should_create_string_from_an_array_with_different_sep_and_end() throws Exception {
        String joined = Snippets.join(new String[]{"pen", "pineapple", "apple", "pen"}, ",", "&");
        assertThat(joined).isEqualTo("pen,pineapple,apple&pen");
    }

    @Test
    public void join_should_create_string_from_an_array_with_sep_only() throws Exception {
        String joined = Snippets.join(new String[]{"pen", "pineapple", "apple", "pen"}, ",");
        assertThat(joined).isEqualTo("pen,pineapple,apple,pen");
    }

    @Test
    public void join_should_create_string_from_an_array_with_default_sep() throws Exception {
        String joined = Snippets.join(new String[]{"pen", "pineapple", "apple", "pen"});
        assertThat(joined).isEqualTo("pen,pineapple,apple,pen");
    }

    @Test
    public void join_should_create_empty_string_with_empty_array() throws Exception {
        String joined = Snippets.join(new String[]{});
        assertThat(joined).isEqualTo("");
    }

    @Test
    public void nthElement_return_nth_element_from_start_when_n_is_greater_than_0() throws Exception {
        String nthElement = Snippets.nthElement(new String[]{"a", "b", "c"}, 1);
        assertThat(nthElement).isEqualTo("b");
    }


    @Test
    public void nthElement_return_nth_element_from_end_when_n_is_less_than_0() throws Exception {
        String nthElement = Snippets.nthElement(new String[]{"a", "b", "c"}, -3);
        assertThat(nthElement).isEqualTo("a");
    }

    @Test
    public void pick_should_pick_key_pairs_corresponding_to_keys() throws Exception {
        Map<String, Integer> obj = new HashMap<>();
        obj.put("a", 1);
        obj.put("b", 2);
        obj.put("c", 3);

        Map<String, Integer> picked = Snippets.pick(obj, new String[]{"a", "c"});
        assertThat(picked).containsExactly(new SimpleEntry<>("a", 1), new SimpleEntry<>("c", 3));
    }

    @Test
    public void reducedFilter_Test() throws Exception {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("id", 1);
        item1.put("name", "john");
        item1.put("age", 24);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("id", 2);
        item2.put("name", "mike");
        item2.put("age", 50);

        Map<String, Object>[] filtered = Snippets.reducedFilter((Map<String, Object>[]) new Map[]{item1, item2}, new String[]{"id", "name"}, item -> (Integer) item.get("age") > 24);
        assertThat(filtered).hasSize(1);
        assertThat(filtered[0])
                .containsOnly(
                        new SimpleEntry<String, Object>("id", 2),
                        new SimpleEntry<String, Object>("name", "mike"));
    }

    @Test
    public void sample_should_return_random_element() throws Exception {
        Integer sample = Snippets.sample(new Integer[]{3, 7, 9, 11});
        assertThat(sample).isIn(Arrays.asList(3, 7, 9, 11));
    }

    @Test
    public void sampleSize_should_return_sample_of_size_array_length_when_sample_size_is_greater_than_array_size() throws Exception {
        Integer[] sample = Snippets.sampleSize(new Integer[]{1, 2, 3}, 4);
        assertThat(sample).hasSize(3);
    }

    @Test
    public void similarity_test() throws Exception {
        Integer[] arr = Snippets.similarity(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 4});
        assertThat(arr).isEqualTo(new Integer[]{1, 2});
    }

    @Test
    public void empty_array() throws Exception {
        String[] empty = Snippets.emptyArray(String.class);
        assertThat(empty).isEmpty();
    }

    @Test
    public void sortedIndex_descending() throws Exception {
        int index = Snippets.sortedIndex(new Integer[]{5, 3, 2, 1}, 4);
        assertThat(index).isEqualTo(1);
    }

    @Test
    public void sortedIndex_ascending() throws Exception {
        int index = Snippets.sortedIndex(new Integer[]{30, 50}, 40);
        assertThat(index).isEqualTo(1);
    }

    @Test
    public void sortedIndex_ascending_at_end() throws Exception {
        int index = Snippets.sortedIndex(new Integer[]{30, 50}, 60);
        assertThat(index).isEqualTo(2);
    }

    @Test
    public void symmetricDifference_test() throws Exception {
        Integer[] diff = Snippets.symmetricDifference(
                new Integer[]{1, 2, 3},
                new Integer[]{1, 2, 4}
        );
        assertThat(diff).isEqualTo(new Integer[]{3, 4});
    }

    @Test
    public void union_test() throws Exception {
        Integer[] union = Snippets.union(
                new Integer[]{1, 2, 3},
                new Integer[]{1, 2, 4}
        );

        assertThat(union).isEqualTo(new Integer[]{1, 2, 3, 4});
    }

    @Test
    public void without_test() throws Exception {
        Integer[] without = Snippets.without(
                new Integer[]{2, 1, 2, 3},
                1, 2

        );

        assertThat(without).isEqualTo(new Integer[]{3});
    }

    @Test
    public void zip_test() throws Exception {
        List<Object[]> zipped = Snippets.zip(
                new String[]{"a", "b"},
                new Integer[]{1, 2},
                new Boolean[]{true, false}
        );

        assertThat(zipped).hasSize(2);
        assertThat(zipped.get(0)).isEqualTo(new Object[]{"a", 1, true});
        assertThat(zipped.get(1)).isEqualTo(new Object[]{"b", 2, false});
    }

    @Test
    public void zip_test_2() throws Exception {
        List<Object[]> zipped = Snippets.zip(
                new String[]{"a"},
                new Integer[]{1, 2},
                new Boolean[]{true, false}
        );

        assertThat(zipped).hasSize(2);
        assertThat(zipped.get(0)).isEqualTo(new Object[]{"a", 1, true});
        assertThat(zipped.get(1)).isEqualTo(new Object[]{null, 2, false});
    }

    @Test
    public void zipObject_test_1() throws Exception {
        Map<String, Object> map = Snippets.zipObject(
                new String[]{"a", "b", "c"},
                new Integer[]{1, 2}
        );

        assertThat(map).containsOnly(
                new SimpleEntry<>("a", 1),
                new SimpleEntry<>("b", 2),
                new SimpleEntry<>("c", null)
        );
    }

    @Test
    public void zipObject_test_2() throws Exception {
        Map<String, Object> map = Snippets.zipObject(
                new String[]{"a", "b"},
                new Integer[]{1, 2, 3}
        );

        assertThat(map).containsOnly(
                new SimpleEntry<>("a", 1),
                new SimpleEntry<>("b", 2)
        );
    }

    @Test
    public void average_of_1_to_10_is_5_dot_5() throws Exception {
        double average = Snippets.average(IntStream.rangeClosed(1, 10).toArray());
        assertThat(average).isEqualTo(5.5);
    }

    @Test
    public void capitalize_test() throws Exception {
        assertThat(Snippets.capitalize("fooBar", false)).isEqualTo("FooBar");
        assertThat(Snippets.capitalize("fooBar", true)).isEqualTo("Foobar");
    }

    @Test
    public void capitalizeEveryWord_test() throws Exception {
        assertThat(Snippets.capitalizeEveryWord("hello world!")).isEqualTo("Hello World!");
    }

    @Test
    public void anagrams_test() throws Exception {
        List<String> anagrams = Snippets.anagrams("abc");
        assertThat(anagrams)
                .containsOnly("abc", "acb", "bac", "bca", "cab", "cba");
    }

    @Test
    public void byteSize_of_smiley_is_4() throws Exception {
        int length = Snippets.byteSize("\uD83D\uDE00");
        assertThat(length).isEqualTo(4);
    }

    @Test
    public void byteSize_of_hello_world_is_11() throws Exception {
        assertThat(Snippets.byteSize("Hello World")).isEqualTo(11);
    }

    @Test
    public void countVowels_test() throws Exception {
        assertThat(Snippets.countVowels("foobar")).isEqualTo(3);
    }

    @Test
    public void escapeRegex_test() throws Exception {
        assertThat(Snippets.escapeRegExp("(test)")).isEqualTo("\\Q(test)\\E");
    }

    @Test
    public void fromCamelCase_test() throws Exception {
        assertThat(Snippets.fromCamelCase("someJavaProperty", "_"))
                .isEqualTo("some_java_property");
        assertThat(Snippets.fromCamelCase("someDatabaseFieldName", " "))
                .isEqualTo("some database field name");
        assertThat(Snippets.fromCamelCase("someLabelThatNeedsToBeCamelized", "-"))
                .isEqualTo("some-label-that-needs-to-be-camelized");
    }

    @Test
    public void isAbsoluteUrl_test() throws Exception {
        assertThat(Snippets.isAbsoluteUrl("https://google.com")).isTrue();
        assertThat(Snippets.isAbsoluteUrl("ftp://www.myserver.net")).isTrue();
        assertThat(Snippets.isAbsoluteUrl("/foo/bar")).isFalse();
    }

    @Test
    public void isLowerCase_test() throws Exception {
        assertThat(Snippets.isLowerCase("abc")).isTrue();
        assertThat(Snippets.isLowerCase("a3@$")).isTrue();
        assertThat(Snippets.isLowerCase("Ab4")).isFalse();
    }

    @Test
    public void mask_test() throws Exception {
        assertThat(Snippets.mask("1234567890", 4, "*")).isEqualTo("******7890");
        assertThat(Snippets.mask("1234567890", 3, "*")).isEqualTo("*******890");
        assertThat(Snippets.mask("1234567890", -4, "*")).isEqualTo("1234******");
    }

    @Test
    public void palindrome_test() throws Exception {
        assertThat(Snippets.isPalindrome("taco cat")).isTrue();
        assertThat(Snippets.isPalindrome("abc")).isFalse();
    }

    @Test
    public void reverseString_test() throws Exception {
        assertThat(Snippets.reverseString("foobar")).isEqualTo("raboof");
    }

    @Test
    public void sortCharactersInString_test() throws Exception {
        assertThat(Snippets.sortCharactersInString("cabbage")).isEqualTo("aabbceg");
    }

    @Test
    public void splitLines_test() throws Exception {
        assertThat(Snippets.splitLines("This\nis a\nmultiline\nstring.\n"))
                .isEqualTo(new String[]{
                        "This",
                        "is a",
                        "multiline",
                        "string."
                });
    }

    @Test
    public void toCamelCase_test() throws Exception {
        assertThat(Snippets.toCamelCase("some_database_field_name")).isEqualTo("someDatabaseFieldName");
        assertThat(Snippets.toCamelCase("Some label that needs to be camelized")).isEqualTo("someLabelThatNeedsToBeCamelized");
        assertThat(Snippets.toCamelCase("some-java-property")).isEqualTo("someJavaProperty");
        assertThat(Snippets.toCamelCase("some-mixed_string with spaces_underscores-and-hyphens")).isEqualTo("someMixedStringWithSpacesUnderscoresAndHyphens");
    }

    @Test
    public void toKebabCase_test() throws Exception {
        assertThat(Snippets.toKebabCase("camelCase")).isEqualTo("camel-case");
        assertThat(Snippets.toKebabCase("some text")).isEqualTo("some-text");
        assertThat(Snippets.toKebabCase("some-mixed_string With spaces_underscores-and-hyphens")).isEqualTo("some-mixed-string-with-spaces-underscores-and-hyphens");
        assertThat(Snippets.toKebabCase("AllThe-small Things")).isEqualTo("all-the-small-things");
        assertThat(Snippets.toKebabCase("IAmListeningToFMWhileLoadingDifferentURLOnMyBrowserAndAlsoEditingXMLAndHTML")).isEqualTo("i-am-listening-to-fm-while-loading-different-url-on-my-browser-and-also-editing-xml-and-html");
    }

    @Test
    public void toSnakeCase_test() throws Exception {
        assertThat(Snippets.toSnakeCase("camelCase")).isEqualTo("camel_case");
        assertThat(Snippets.toSnakeCase("some text")).isEqualTo("some_text");
        assertThat(Snippets.toSnakeCase("some-mixed_string With spaces_underscores-and-hyphens")).isEqualTo("some_mixed_string_with_spaces_underscores_and_hyphens");
        assertThat(Snippets.toSnakeCase("AllThe-small Things")).isEqualTo("all_the_small_things");
        assertThat(Snippets.toSnakeCase("IAmListeningToFMWhileLoadingDifferentURLOnMyBrowserAndAlsoEditingXMLAndHTML")).isEqualTo("i_am_listening_to_fm_while_loading_different_url_on_my_browser_and_also_editing_xml_and_html");
    }

    @Test
    public void truncateString_test() throws Exception {
        assertThat(Snippets.truncateString("boomerang", 7)).isEqualTo("boom...");
    }

    @Test
    public void words_test() throws Exception {
        assertThat(Snippets.words("I love java!!")).isEqualTo(new String[]{"I", "love", "java"});
        assertThat(Snippets.words("Kotlin, Java & LemonTea")).isEqualTo(new String[]{"Kotlin", "Java", "LemonTea"});
    }

    @Test
    public void randomInts_test() throws Exception {
        assertThat(Snippets.randomInts(5, 100, 200)).hasSize(5);
    }

    @Test
    public void concat_test() throws Exception {
        String[] first = {"a", "b"};
        String[] second = {"c", "d"};
        assertThat(Snippets.concat(first, second))
                .isEqualTo(new String[]{"a", "b", "c", "d"});
    }

    @Test
    public void getCurrentWorkingDirectoryPath_test() throws Exception {
        assertThat(Snippets.getCurrentWorkingDirectoryPath()).isNotBlank();
    }

    @Test
    public void isNumeric_test() throws Exception {
        assertThat(Snippets.isNumeric("123")).isTrue();
        assertThat(Snippets.isNumeric("abc")).isFalse();
        assertThat(Snippets.isNumeric("")).isFalse();
    }

    @Test
    public void findNextPositivePowerOfTwo_test() throws Exception {
        assertThat(Snippets.findNextPositivePowerOfTwo(-1)).isEqualTo(1);
        assertThat(Snippets.findNextPositivePowerOfTwo(3)).isEqualTo(4);
        assertThat(Snippets.findNextPositivePowerOfTwo(31)).isEqualTo(32);
        assertThat(Snippets.findNextPositivePowerOfTwo(32)).isEqualTo(32);
    }

    @Test
    public void isEven_test() throws Exception {
        assertThat(Snippets.isEven(1)).isFalse();
        assertThat(Snippets.isEven(2)).isTrue();
        assertThat(Snippets.isEven(3)).isFalse();
        assertThat(Snippets.isEven(4)).isTrue();
        assertThat(Snippets.isEven(-1)).isFalse();
    }

    @Test
    public void stringToIntegers_test() throws Exception {
        int[] intArray = Snippets.stringToIntegers("1 2 3 4 5");
        assertThat(intArray).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    private static class Class1 implements I2, I1, I5 {
        // empty
    }

    private static class Class2 extends Class1 implements I2, I3 {
        // empty
    }

    private interface I1 {
        // empty
    }

    private interface I2 {
        // empty
    }

    private interface I3 extends I4, I5 {
        // empty
    }

    private interface I4 {
        // empty
    }

    private interface I5 extends I6 {
        // empty
    }

    private interface I6 {
        // empty
    }

    @Test
    public void getAllInterfaces_shouldFindAllInterfacesImplementedByAClass() {
        final List<Class<?>> list = Snippets.getAllInterfaces(Class2.class);
        assertThat(list).hasSize(6);
        assertThat(list).containsExactly(I2.class, I3.class, I4.class, I5.class, I6.class, I1.class);
    }

    enum Priority {
        High, Medium, Low
    }

    @Test
    public void getEnumMap_convert_enum_to_map() throws Exception {
        Map<String, Priority> map = Snippets.getEnumMap(Priority.class);
        assertThat(map).hasSize(3);
        assertThat(map)
                .containsOnly(
                        new SimpleEntry<>("High", Priority.High),
                        new SimpleEntry<>("Medium", Priority.Medium),
                        new SimpleEntry<>("Low", Priority.Low)
                );
    }
}