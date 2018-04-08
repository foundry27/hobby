package us.dev.hobby.config;

import us.dev.hobby.View;

/**
 * @author Mark Johnson
 */
public interface DisplayInformationView<T> extends View<T> {
    int getDisplayWidth();

    int getDisplayHeight();

    boolean isFullscreen();
}
