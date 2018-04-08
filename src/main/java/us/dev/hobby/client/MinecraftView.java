package us.dev.hobby.client;

import us.dev.hobby.View;
import us.dev.hobby.client.gui.FontRendererView;
import us.dev.hobby.client.settings.GameSettingsView;
import us.dev.hobby.config.*;
import us.dev.hobby.entity.living.EntityPlayerView;
import us.dev.hobby.world.WorldView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface MinecraftView<T> extends View<T> {

    @Nonnull
    FontRendererView<?> getFontRenderer();

    @Nullable
    EntityPlayerView<?> getPlayer();

    @Nullable
    WorldView<?> getWorld();

    @Nonnull
    GameSettingsView<?> getGameSettings();

    @Nonnull
    DisplayInformationView<?> getDisplayInformation();

    @Nonnull
    FolderInformationView<?> getFolderInformation();

    @Nonnull
    GameInformationView<?> getGameInformation();

    @Nullable
    ServerInformationView<?> getServerInformation();

    @Nonnull
    UserInformationView<?> getUserInformation();
}
