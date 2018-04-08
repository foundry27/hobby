package us.dev.hobby.config;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

public interface GameInformationView<T> extends View<T> {
    @Nonnull
    String getGameVersion();

    @Nonnull
    String getGameName();

    boolean isDemo();
}
