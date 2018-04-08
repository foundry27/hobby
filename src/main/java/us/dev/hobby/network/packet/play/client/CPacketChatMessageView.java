package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.network.packet.PacketView;

/**
 * @author Mark Johnson
 */
public interface CPacketChatMessageView<T> extends PacketView<T> {
    String getMessage();

    void setMessage(String message);
}
