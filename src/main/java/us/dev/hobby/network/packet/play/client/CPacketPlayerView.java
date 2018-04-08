package us.dev.hobby.network.packet.play.client;

import us.dev.hobby.network.packet.PacketView;

public interface CPacketPlayerView<T> extends PacketView<T> {

    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    double getZ();

    void setZ(double z);

    boolean isOnGround();

    void setOnGround(boolean flag);

    boolean isMoving();

    void setMoving(boolean flag);

    float getYaw();

    void setYaw(float yaw);

    float getPitch();

    void setPitch(float pitch);

    boolean isRotating();

    void setRotating(boolean flag);
}
