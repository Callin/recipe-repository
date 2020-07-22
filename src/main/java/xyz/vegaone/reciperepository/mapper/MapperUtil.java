package xyz.vegaone.reciperepository.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapperUtil {
    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private MapperUtil() {
    }

    public static <T, U> U map(T source, Class<U> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public static <T, U> List<U> mapList(List<T> source, Class<U> destinationClass) {
        return source
                .stream()
                .map(item -> mapper.map(item, destinationClass))
                .collect(Collectors.toList());
    }

    public static <T, U> List<U> mapIterable(Iterable<T> source, Class<U> destinationClass) {
        return Stream.ofNullable(source)
                .map(item -> mapper.map(item, destinationClass))
                .collect(Collectors.toList());
    }
}
