package us.dev.hobby.util.text;

import us.dev.hobby.View;
import us.dev.hobby.util.text.event.ClickEventView;
import us.dev.hobby.util.text.event.HoverEventView;

import javax.annotation.Nullable;

/**
 * @author Mark Johnson
 */
public interface TextStyleView<T> extends View<T> {
    TextStyleView<?> ROOT = new TextStyleView<Void>() {
        @Nullable
        public String getColor() {
            return null;
        }

        public boolean hasStyle(String style) {
            return false;
        }

        @Nullable
        public ClickEventView<?> getClickEvent() {
            return null;
        }

        @Nullable
        public HoverEventView<?> getHoverEvent() {
            return null;
        }

        @Nullable
        public String getInsertion() {
            return null;
        }

        @Override
        public TextStyleView<Void> setInsertion(String insertion) {
            return null;
        }

        public TextStyleView<Void> setColor(String color) {
            throw new UnsupportedOperationException();
        }

        public TextStyleView<Void> setStyle(String style, boolean state) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        public TextStyleView<Void> setClickEvent(ClickEventView<?> event) {
            throw new UnsupportedOperationException();
        }

        public TextStyleView<Void> setHoverEvent(HoverEventView<?> event) {
            throw new UnsupportedOperationException();
        }

        public TextStyleView<Void> setParentStyle(TextStyleView<?> parent) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return "Style.ROOT";
        }

        public TextStyleView<Void> createShallowCopy() {
            return this;
        }

        public TextStyleView<Void> createDeepCopy() {
            return this;
        }

        public String getFormattingCode() {
            return "";
        }

        @Override
        public TextStyleView<?> getParent() {
            return null;
        }

        @Override
        public Void reify() {
            return null;
        }
    };

    /**
     * Gets the effective color of this ChatStyle.
     */
    @Nullable
    String getColor();

    /**
     * Sets the color for this ChatStyle to the given value.  Only use color values for this; set other values using the
     * specific methods.
     */
    TextStyleView<T> setColor(String color);

    boolean hasStyle(String style);

    TextStyleView<T> setStyle(String style, boolean state);

    /**
     * Whether or not this style is empty (inherits everything from the parent).
     */
    boolean isEmpty();

    /**
     * The effective chat click event.
     */
    @Nullable
    ClickEventView<?> getClickEvent();

    /**
     * Sets the event that should be run when text of this ChatStyle is clicked on.
     */
    TextStyleView<T> setClickEvent(ClickEventView<?> event);

    /**
     * The effective chat hover event.
     */
    @Nullable
    HoverEventView<?> getHoverEvent();

    /**
     * Sets the event that should be run when text of this ChatStyle is hovered over.
     */
    TextStyleView<T> setHoverEvent(HoverEventView<?> event);

    /**
     * Get the text to be inserted into Chat when the component is shift-clicked
     */
    @Nullable
    String getInsertion();

    /**
     * Set a text to be inserted into Chat when the component is shift-clicked
     */
    TextStyleView<T> setInsertion(String insertion);

    /**
     * Sets the fallback ChatStyle to use if this ChatStyle does not override some value.  Without a parent, obvious
     * defaults are used (bold: false, underlined: false, etc).
     */
    TextStyleView<T> setParentStyle(TextStyleView<?> parent);

    /**
     * Gets the equivalent text formatting code for this style, without the initial section sign (U+00A7) character.
     */
    String getFormattingCode();

    /**
     * Gets the immediate parent of this ChatStyle.
     */
    TextStyleView<?> getParent();

    /**
     * Creates a shallow copy of this style.  Changes to this instance's values will not be reflected in the copy, but
     * changes to the parent style's values WILL be reflected in both this instance and the copy, wherever either does
     * not override a value.
     */
    TextStyleView<T> createShallowCopy();

    /**
     * Creates a deep copy of this style.  No changes to this instance or its parent style will be reflected in the
     * copy.
     */
    TextStyleView<T> createDeepCopy();
}
