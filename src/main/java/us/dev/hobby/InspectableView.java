package us.dev.hobby;

import java.util.Map;

/**
 * @author Mark Johnson
 */
public interface InspectableView<T> extends View<T>, Map<String, Object> {
    <E> E inspect(String key, Class<E> type);
}
