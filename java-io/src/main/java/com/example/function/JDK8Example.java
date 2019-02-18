package com.example.function;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JDK8Example {
    @Test
    public void t3() {
        List<Integer> list = Stream.of(1, 4, 78, 2, 3, 61, 9).collect(Collectors.toList());
//        List<Integer> list = Collections.emptyList();
        if (list.stream().max(Integer::compareTo).isPresent()) {
            Integer max = list.stream().max(Integer::compareTo).get();
            Integer min = list.stream().min(Integer::compareTo).get();
            System.out.println(max + "-" + min);
        }

        //方法引用 Method References
        Integer res = list.stream().max((x, y) -> x.compareTo(y)).get();
        System.out.println(res);
    }

    @Test
    public void parallel() {
        List<String> list = Stream.of("A", "B", "c", "e", "D", "1", "2", "11").collect(Collectors.toList());
        int res = list.stream().parallel().map(x -> x.length()).reduce(Integer::sum).get();
        System.out.println(res);
    }

    /**
     * reduce 操作可以实现从一组值中生成一个值
     */
    @Test
    public void reduce() {
        List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
//        Integer res = list.stream().reduce(0, (x, y) -> x + y);
        Integer res = list.stream().reduce(0, Integer::sum);
        System.out.println(res);
    }

    @Test
    public void flatMap() {
        List<List<String>> collection = Stream.of(Arrays.asList("A", "b"), Arrays.asList("1", "2")).collect(Collectors.toList());
        List<String> list = collection.stream().flatMap(nums -> nums.stream()).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void filter() {
        List<String> list = Stream.of("A", "B", "c", "e", "D", "1", "2", "11").collect(Collectors.toList());
        List<String> numericList = list.stream().filter(string -> StringUtils.isNumeric(string)).collect(Collectors.toList());
        System.out.println(numericList);
    }

    @Test
    public void map() {
        List<String> list = Stream.of("A", "B", "c", "e", "D", "1", "2", "11").collect(Collectors.toList());
        List<String> uppperList = list.stream().map(string -> string.toUpperCase()).collect(Collectors.toList());
        System.out.println(uppperList);
    }

    @Test
    public void t2() {
        List<Person> list = Stream.of(new Person("jack", 15), new Person("tom", 20), new Person("marry", 10)).collect(Collectors.toList());
        new ArrayList<Person>(Arrays.asList());
    }

    @Test
    public void t1() {
        ArrayList<Person> list = new ArrayList<>(Arrays.asList(new Person("jack", 15), new Person("tom", 20), new Person("marry", 10)));
        long count = list.stream().filter((p) -> p.getAge() >= 15).count();
        System.out.println(count);
        Stream<Person> stream = list.stream().filter((p) -> p.getAge() >= 15);
    }
}