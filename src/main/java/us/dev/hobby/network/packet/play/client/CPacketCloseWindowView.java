package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.network.packet.PacketView;

/**
 * @author Mark Johnson
 */
public interface CPacketCloseWindowView<T> extends PacketView<T> {
    int getWindowId();
}
