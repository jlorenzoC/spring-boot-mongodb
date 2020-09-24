package jorge.mongodb.firstapp.util;

import java.util.List;
import java.util.stream.*;

public class ToolBox {
    public static <T> List<T> removeElementsFromArray(T[] fromArray, T[] elementsToRemove) {
        List<T> from = arrayToList(fromArray);
        List<T> elements = arrayToList(elementsToRemove);
        for (T e : elements) from.remove(e);
        return from;
    }

    static <T> List<T> arrayToList(T[] array) {
        return Stream.of(array).collect(Collectors.toList());
    }
}
