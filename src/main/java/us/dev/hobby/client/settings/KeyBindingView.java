package us.dev.hobby.client.settings;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public interface KeyBindingView<T> extends View<T>, Comparable<KeyBindingView<?>> {
    boolean isKeyDown();

    @Nonnull
    String getKeyCategory();

    boolean isPressed();

    @Nonnull
    String getKeyDescription();

    int getKeyCodeDefault();

    int getKeyCode();

    int setKeyCode(int keyCode);

    default int compareTo(@Nonnull KeyBindingView<?> keyBindingModel) {
        int i = getKeyCategory().compareTo(keyBindingModel.getKeyCategory());

        if (i == 0) {
            i = getKeyDescription().compareTo(keyBindingModel.getKeyDescription());
        }

        return i;
    }
}
