package us.dev.hobby.world;

import us.dev.hobby.View;
import us.dev.hobby.entity.EntityView;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface WorldView<T> extends View<T> {
    @Nonnull
    Collection<EntityView<?>> getLoadedEntities();

    boolean isHardcore();
}
