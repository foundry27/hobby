package us.dev.hobby.util.resource;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public interface ResourceLocationView<T> extends View<T> {
    @Nonnull
    String getResourcePath();

    @Nonnull
    String getResourceDomain();
}
