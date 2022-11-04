package lab.five.stream.api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MethodStream {

    public double averageList(List<Integer> list) {
        return list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    public List<String> addNewAndUpperCase(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .map(s -> "_new_" + s)
                .collect(Collectors.toList());
    }

    /*Переделать по условию*/
    public List<Double> squareInt(List<Integer> list) {
        return list.stream()
                .filter(i -> Collections.frequency(list, i) == 1)
                .map(s -> Math.pow(s, 2))
                .collect(Collectors.toList());
    }

    public List<String> sortStrings(Collection<String> list, char firstLetter) {
        return list.stream()
                .sorted()
                .filter(s -> s.startsWith(String.valueOf(firstLetter)))
                .collect(Collectors.toList());

    }

    public int getLastElement(Collection<Integer> list) throws NoSuchElementException {
        return list.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Пустая коллекция"));
    }

    public int sum(int[] ar) {
        return Stream.of(ar)
                .flatMapToInt(IntStream::of)
                .filter(s -> s % 2 == 0)
                .sum();
    }

    public Map<Character, String> makeMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(k -> k.charAt(0), v -> v.substring(1),
                        (prev, curr) -> prev));
    }

    public static void main(String[] args) {
        MethodStream methodStream = new MethodStream();
        int[] arEmpty = {1, 3};
        int[] ar = {1, 2, 3, 4, 5};
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> listInt = Arrays.asList(5, 2, 5, 4, 5, 1, 1);
        List<String> listStr = Arrays.asList("one", "two", "three", "tennessy", "texas");
        System.out.println("Average: " + methodStream.averageList(listInt));
        System.out.println("To upper case: " + methodStream.addNewAndUpperCase(listStr));
        System.out.println("Square non-repeated: " + methodStream.squareInt(listInt));
        System.out.println("Sorted strings: " + methodStream.sortStrings(listStr, 't'));
        System.out.println("Last element: " + methodStream.getLastElement(listInt));
        System.out.println("Сумма четных чисел в массиве: " + methodStream.sum(ar));
        System.out.println("Сумма в массиве, четных чисел не оказалось: " + methodStream.sum(arEmpty));
        System.out.println("Из списка в карту: " + methodStream.makeMap(listStr));
        System.out.println("Last element of emptyColl - get exception: " + methodStream.getLastElement(emptyList));
    }
}
