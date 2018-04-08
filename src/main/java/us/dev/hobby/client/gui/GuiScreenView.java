package us.dev.hobby.client.gui;

import us.dev.hobby.View;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author Mark Johnson
 */
public interface GuiScreenView<T> extends View<T> {
    void drawScreen(int mouseX, int mouseY, float partialTicks);

    void sendChatMessage(@Nonnull String msg);

    void sendChatMessage(@Nonnull String msg, boolean addToChat);

    void setGuiSize(int w, int h);

    void handleInput() throws IOException;

    void handleMouseInput() throws IOException;

    void handleKeyboardInput() throws IOException;

    void drawDefaultBackground();

    void drawWorldBackground(int tint);

    void drawBackground(int tint);

    boolean doesGuiPauseGame();

    void confirmClicked(boolean result, int id);

    void initGui();

    void updateScreen();

    void onGuiClosed();
}
