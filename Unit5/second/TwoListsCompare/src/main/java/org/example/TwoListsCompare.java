package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TwoListsCompare {

    private List<Double> firstList = new ArrayList<>();
    private List<Double> secondList = new ArrayList<>();

    final private Function<List<Double>, Double> avrFn;
    private String resultMsg = null;

//    public static void main(String[] args) {
//        List[] data = new List[]{List.of(-1, 124.3, 2, 9, 213.2), List.of(23.3, 124.3, -10, 10.3, 213.2)};
//
//        TwoListsCompare twoListsCompare = new TwoListsCompare(data[0], data[1], true);
//        twoListsCompare.print();
//    }

    public TwoListsCompare(List<Double> firstList, List<Double> secondList) {
        if (firstList != null) this.firstList.addAll(firstList);
        if (secondList != null) this.secondList.addAll(secondList);

        this.firstList = this.firstList.stream().map(x -> x != null ? x : Double.valueOf(0)).toList();
        this.secondList = this.secondList.stream().map(x -> x != null ? x : Double.valueOf(0)).toList();

        avrFn = x -> x.stream().mapToDouble(y -> y.doubleValue()).average().getAsDouble();
    }

    public TwoListsCompare(List<Object> firstListAnyType, List<Object> secondListAnyType, boolean useAnyTypeAtList) {
        List<Double> firstList = recognizeList(firstListAnyType);
        List<Double> secondList = recognizeList(secondListAnyType);

        if (firstList != null) this.firstList.addAll(firstList);
        if (secondList != null) this.secondList.addAll(secondList);

        this.firstList = this.firstList.stream().map(x -> x != null ? x : Double.valueOf(0)).toList();
        this.secondList = this.secondList.stream().map(x -> x != null ? x : Double.valueOf(0)).toList();

        avrFn = x -> x.stream().mapToDouble(y -> y.doubleValue()).average().getAsDouble();
    }

    private List<Double> recognizeList(List<Object> firstList) {
        List<Double> result = firstList.stream().map(x -> {
            Double val = null;
            if (x instanceof String) val = Double.parseDouble((String) x);
            if (x instanceof Integer) val = Double.valueOf((Integer) x);
            if (x instanceof Double) val = (double) x;
            if (x instanceof Float)
                throw new IllegalArgumentException("Программа не работает с типом данных " + "float в данной версии");
            if (x instanceof Long)
                throw new IllegalArgumentException("Программа не работает с типом данных " + "long в данной версии");

            if (val == null)
                throw new IllegalArgumentException("Программа не работает с данным типом данных - " + (x).getClass().getName());

            return Double.valueOf(val);
        }).toList();

        return result;
    }

    String compare() {
        if (firstList.size() == 0 || secondList.size() == 0)
            throw new IllegalArgumentException("Передан пустой " + (firstList == null ? "первый" : "второй") + " список");

        int compRes = compareEngine();

        if (compRes == 0) resultMsg = "Средние значения равны";
        if (compRes < 0) resultMsg = "Второй список имеет большее среднее значение";
        if (compRes > 0) resultMsg = "Первый список имеет большее среднее значение";

        return resultMsg;
    }

    void print() {
        if (resultMsg == null) compare();
        System.out.println(resultMsg);
    }

    private int compareEngine() {
        return avrFn.apply(firstList).compareTo(avrFn.apply(secondList));
    }
}