package us.dev.hobby.util.text.event;

import us.dev.hobby.View;

/**
 * @author Mark Johnson
 */
public interface ClickEventView<T> extends View<T> {
    String getValue();

    String getAction();
}
