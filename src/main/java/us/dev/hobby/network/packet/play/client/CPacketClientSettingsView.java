package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.network.packet.PacketView;

/**
 * @author Mark Johnson
 */
public interface CPacketClientSettingsView<T> extends PacketView<T> {
    <E> E getSetting(String name, Class<E> type);
}
