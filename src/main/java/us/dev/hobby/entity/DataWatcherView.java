package us.dev.hobby.entity;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

public interface DataWatcherView<T> extends View<T> {
    @Nonnull
    EntityView<?> getOwner();

    void addObject(int id, @Nonnull Object object);

    interface WatchableObject<T> extends View<T> {
        int getDataValueId();
        int getObjectType();

        @Nonnull
        Object getObject();

        void setObject(@Nonnull Object object);

        boolean isWatched();
        void setWatched(boolean watched);
    }
}
