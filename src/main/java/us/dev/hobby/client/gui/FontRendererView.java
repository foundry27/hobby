package us.dev.hobby.client.gui;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public interface FontRendererView<T> extends View<T> {
    int drawStringWithShadow(@Nonnull String text, float x, float y, int color);

    int drawString(@Nonnull String text, int x, int y, int color);

    int drawString(@Nonnull String text, float x, float y, int color, boolean dropShadow);

    int getStringWidth(@Nonnull String text);

    int getCharWidth(char character);

    @Nonnull
    String trimStringToWidth(@Nonnull String text, int width, boolean reverse);

    @Nonnull
    String trimStringToWidth(@Nonnull String text, int width);

    void drawSplitString(@Nonnull String str, int x, int y, int wrapWidth, int textColor);

    int splitStringWidth(@Nonnull String str, int maxLength);
}
