package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.network.packet.PacketView;

/**
 * @author Mark Johnson
 */
public interface CPacketAnimationView<T> extends PacketView<T> {
    String getHand();
}
