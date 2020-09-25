package jorge.mongodb.firstapp.util;

import org.springframework.restdocs.payload.*;

import java.util.*;
import java.util.stream.*;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

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

    public static List<FieldDescriptor> removeElementFromFieldDescriptorArrayByLabel(FieldDescriptor[] fieldDescriptors, String label) {
        return arrayToList(fieldDescriptors).stream().filter(e -> !e.getPath().equals(label)).collect(Collectors.toList());
    }

    public static Set<FieldDescriptor> convertirASet(FieldDescriptor[]... array) {
        return Stream.of(array).flatMap(Stream::of).collect(Collectors.toSet());
    }

    public static FieldDescriptor fieldDescriptor(String path, String descripcion, JsonFieldType jsonFieldType) {
        return fieldWithPath(path).description(descripcion).type(jsonFieldType).optional();
    }
}
