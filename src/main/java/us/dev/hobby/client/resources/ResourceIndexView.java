package us.dev.hobby.client.resources;

import us.dev.hobby.View;
import us.dev.hobby.util.resource.ResourceLocationView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

/**
 * @author Mark Johnson
 */
public interface ResourceIndexView<T> extends View<T> {
    @Nullable
    File getFile(@Nonnull ResourceLocationView<?> location);

    boolean isFileExisting(@Nonnull ResourceLocationView<?> location);

    @Nullable
    File getPackMetadata();
}
