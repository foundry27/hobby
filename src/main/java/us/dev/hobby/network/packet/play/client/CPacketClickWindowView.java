package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.item.ItemStackView;
import us.dev.hobby.network.packet.PacketView;

/**
 * @author Mark Johnson
 */
public interface CPacketClickWindowView<T> extends PacketView<T> {

    int getWindowId();

    int getSlotId();

    int getUsedButton();

    short getActionNumber();

    ItemStackView<?> getClickedItem();

    String getClickType();
}
