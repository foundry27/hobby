package us.dev.hobby.network.packet;

import us.dev.hobby.AbstractInspectableView;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public abstract class AbstractPacketView<T> extends AbstractInspectableView<T> implements PacketView<T> {
    public AbstractPacketView(@Nonnull T packet) {
        super(packet);
    }

    @Override
    public T reify() {
        return model;
    }
}
