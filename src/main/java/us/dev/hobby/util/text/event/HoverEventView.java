package us.dev.hobby.util.text.event;

import us.dev.hobby.View;
import us.dev.hobby.util.text.TextComponentView;

/**
 * @author Mark Johnson
 */
public interface HoverEventView<T> extends View<T> {
    TextComponentView<?> getValue();

    String getAction();
}
