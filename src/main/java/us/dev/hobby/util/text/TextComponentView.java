package us.dev.hobby.util.text;

import us.dev.hobby.View;

import java.util.Collection;

/**
 * @author Mark Johnson
 */
public interface TextComponentView<T> extends View<T> {
    TextStyleView<?> getStyle();

    TextComponentView<T> setStyle(TextStyleView<?> style);

    /**
     * Appends the given text to the end of this component.
     */
    TextComponentView<T> appendText(String text);

    /**
     * Appends the given component to the end of this one.
     */
    TextComponentView<T> appendSibling(TextComponentView<?> component);

    /**
     * Gets the text of this component, without any special formatting codes added, for chat.  TODO: why is this two
     * different methods?
     */
    String getUnformattedComponentText();

    /**
     * Get the text of this component, <em>and all child components</em>, with all special formatting codes removed.
     */
    String getUnformattedText();

    /**
     * Gets the text of this component, with formatting codes added for rendering.
     */
    String getFormattedText();

    Collection<TextComponentView<?>> getSiblings();

    /**
     * Creates a copy of this component.  Almost a deep copy, except the style is shallow-copied.
     */
    TextComponentView<T> createCopy();
}
