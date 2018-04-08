package us.dev.hobby.config;

import us.dev.hobby.View;
import us.dev.hobby.client.resources.ResourceIndexView;

import javax.annotation.Nonnull;
import java.io.File;

/**
 * @author Mark Johnson
 */
public interface FolderInformationView<T> extends View<T> {
    @Nonnull
    File getDataDirectory();

    @Nonnull
    File getResourcePackDirectory();

    @Nonnull
    File getAssetsDirectory();

    @Nonnull
    ResourceIndexView<?> getAssetsIndex();
}
