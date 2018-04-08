package us.dev.hobby;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Mark Johnson
 */
public abstract class AbstractInspectableView<T> implements InspectableView<T> {

    private final Map<String, Field> packetData;

    protected final T model;

    private Collection<Object> values;

    private Set<Entry<String, Object>> entries;

    protected AbstractInspectableView(@Nonnull T model) {
        final Map<String, Field> packetData = new HashMap<>();
        for (Field f : model.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            packetData.put(f.getName(), f);
        }
        this.packetData = packetData;
        this.model = model;
    }

    @Override
    @Nullable
    public <E> E inspect(String key, Class<E> type) {
        final Field lookup = packetData.get(key);
        if (lookup != null) {
            if (type.isAssignableFrom(lookup.getType())) {
                try {
                    return type.cast(lookup.get(model));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", key));
                }
            } else {
                throw new IllegalArgumentException(String.format("Data field '%s' in object %s does not have a compatible type (Given %s, requires ? <: %s)",
                        key, model.getClass().getCanonicalName(), type.getCanonicalName(), lookup.getType().getCanonicalName()));
            }
        } else {
            throw new NullPointerException(String.format("object %s does not have a data field named '%s'", model.getClass().getCanonicalName(), key));
        }
    }

    @Override
    public int size() {
        return packetData.size();
    }

    @Override
    public boolean isEmpty() {
        return packetData.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return key instanceof String && packetData.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Field f : packetData.values()) {
            try {
                if (f.get(model).equals(value)) return true;
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", f.getName()));
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        if (key instanceof String) {
            final Field lookup = packetData.get(key);
            if (lookup != null) {
                try {
                    return lookup.get(model);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", lookup.getName()));
                }
            } else {
                throw new NullPointerException(String.format("object %s does not have a data field named '%s'", model.getClass().getCanonicalName(), key));
            }
        } else {
            return null;
        }
    }

    @Override
    public Object put(String key, Object value) {
        final Field lookup = packetData.get(key);
        if (lookup != null) {
            if (lookup.getType().isInstance(value)) {
                try {
                    final Object old = lookup.get(model);
                    lookup.set(model, value);
                    return old;
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", lookup.getName()));
                }
            } else {
                throw new IllegalArgumentException(String.format("Data field '%s' in object %s does not have a compatible type (Given %s, requires ? <: %s)",
                        key, model.getClass().getCanonicalName(), value.getClass().getCanonicalName(), lookup.getType().getCanonicalName()));
            }
        } else {
            throw new IllegalArgumentException(String.format("object %s does not have a backing data field named '%s'", model.getClass().getCanonicalName(), key));
        }
    }

    @Override
    public Object remove(Object key) {
        if (key instanceof String) {
            final Field lookup = packetData.get(key);
            if (lookup != null) {
                try {
                    packetData.remove(key);
                    return lookup.get(model);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", lookup.getName()));
                }
            } else {
                throw new IllegalArgumentException(String.format("object %s does not have a backing data field named '%s'", model.getClass().getCanonicalName(), key));
            }
        } else {
            return null;
        }
    }

    @Override
    public void putAll(@Nonnull Map<? extends String, ?> m) {
        List<Entry<? extends String, ?>> successes = new ArrayList<>(m.size());
        for (Entry<? extends String, ?> entry : m.entrySet()) {
            final Field lookup = packetData.get(entry.getKey());
            if (lookup != null) {
                if (lookup.getType().isInstance(entry.getValue())) {
                    successes.add(entry);
                } else {
                    throw new IllegalArgumentException(String.format("Data field '%s' in object %s does not have a compatible type (Given %s, requires ? <: %s)",
                            entry.getKey(), model.getClass().getCanonicalName(), entry.getValue().getClass().getCanonicalName(), lookup.getType().getCanonicalName()));
                }
            } else {
                throw new NullPointerException(String.format("object %s does not have a backing field named '%s'", model.getClass().getCanonicalName(), entry.getKey()));
            }
        }
        for (Entry<? extends String, ?> entry : successes) {
            Field lookup = null;
            try {
                (lookup = packetData.get(entry.getKey())).set(model, entry.getValue());
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", lookup.getName()));
            }
        }
    }

    @Override
    public void clear() {
        for (Field f : packetData.values()) {
            try {
                f.set(model, null);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", f.getName()));
            }
        }
    }

    @Nonnull
    @Override
    public Set<String> keySet() {
        return packetData.keySet();
    }

    @Nonnull
    @Override
    public Collection<Object> values() {
        if (values == null) {
            values = new Values();
        }
        return values;
    }

    @Nonnull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        if (entries == null) {
            entries = new EntrySet();
        }
        return entries;
    }

    private final class Values extends AbstractCollection<Object> {

        @Override
        public final int size() {
            return AbstractInspectableView.this.size();
        }

        @Override
        public final void clear() {
            AbstractInspectableView.this.clear();
        }

        @Nonnull
        @Override
        public final Iterator<Object> iterator() {
            return new FieldValueIterator(AbstractInspectableView.this.packetData.values().iterator(), model);
        }

        @Override
        public final boolean contains(Object o) {
            return containsValue(o);
        }
    }

    private final class EntrySet extends AbstractSet<Entry<String, Object>> {

        @Override
        public final int size() {
            return AbstractInspectableView.this.size();
        }

        @Override
        public final void clear() {
            AbstractInspectableView.this.clear();
        }

        @Nonnull
        @Override
        public final Iterator<Entry<String, Object>> iterator() {
            return new FieldViewEntryIterator(packetData.entrySet().iterator(), model);
        }

        public final boolean contains(Object o) {
            if (o instanceof Map.Entry) {
                final Entry<?, ?> e = (Entry<?, ?>) o;
                return e.getKey() instanceof String && packetData.containsKey(e.getKey());
            } else {
                return false;
            }
        }

        public final boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                final Entry<?,?> e = (Entry<?,?>) o;
                return e.getKey() instanceof String && packetData.remove(e.getKey()) != null;
            } else {
                return false;
            }
        }
    }

    private static final class FieldValueIterator implements Iterator<Object> {

        private final Iterator<Field> it;

        private final Object parent;

        private Field prev;

        FieldValueIterator(Iterator<Field> it, Object parent) {
            this.it = it;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Object next() {
            Field next = null;
            try {
                return (prev = next = it.next()).get(parent);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", next.getName()));
            }
        }

        @Override
        public void remove() {
            try {
                prev.set(parent, null);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", prev.getName()));
            }
        }
    }

    private static final class FieldViewEntryIterator implements Iterator<Entry<String, Object>> {

        private final Iterator<Entry<String, Field>> it;

        private final Object parent;

        private Entry<String, Field> prev;

        FieldViewEntryIterator(Iterator<Entry<String, Field>> it, Object parent) {
            this.it = it;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Entry<String, Object> next() {
            final Entry<String, Field> next = prev = it.next();
            return new FieldViewEntry(next.getKey(), next.getValue(), parent);
        }

        @Override
        public void remove() {
            try {
                prev.getValue().set(parent, null);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", prev.getValue().getName()));
            }
        }
    }

    private static final class FieldViewEntry implements Entry<String, Object> {

        private final String key;

        private final Field value;

        private final Object parent;

        FieldViewEntry(String key, Field value, Object parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            try {
                return value.get(parent);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", value.getName()));
            }
        }

        @Override
        public Object setValue(Object value) {
            if (this.value.getType().isInstance(value)) {
                try {
                    final Object old = this.value.get(parent);
                    this.value.set(parent, value);
                    return old;
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(String.format("Unable to acquire privileged access to data field '%s'", this.value.getName()));
                }
            } else {
                throw new IllegalArgumentException(String.format("Data field '%s' in object %s does not have a compatible type (Given %s, requires ? <: %s)",
                        key, parent.getClass().getCanonicalName(), value.getClass().getCanonicalName(), this.value.getType().getCanonicalName()));
            }
        }
    }
}