package us.dev.hobby.client.settings;

import us.dev.hobby.View;
import us.dev.hobby.world.GameDifficultyModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author Mark Johnson
 */
public interface GameSettingsView<T> extends View<T> {
    @Nullable
    KeyBindingView<?> getKeybind(@Nonnull String keyIdentifier);

    @Nonnull
    GameDifficultyModel getGameDifficulty();

    @Nonnull
    Map<String, KeyBindingView<?>> getKeybinds();

    void resetSettings();
}
