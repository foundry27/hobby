package us.dev.hobby.entity;

import us.dev.hobby.View;
import us.dev.hobby.util.position.AxisAlignedBBView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public interface EntityView<T> extends View<T> {
    int getEntityID();

    @Nonnull
    UUID getUniqueID();

    @Nonnull
    DataWatcherView<?> getDataWatcher();

    double getX();
    double getY();
    double getZ();

    void setX(double x);
    void setY(double y);
    void setZ(double z);

    double getPrevX();
    double getPrevY();
    double getPrevZ();

    double getMotionX();
    double getMotionY();
    double getMotionZ();

    void setMotionX(double x);
    void setMotionY(double y);
    void setMotionZ(double z);

    float getYaw();
    void setYaw(float yaw);

    float getPitch();
    void setPitch(float pitch);

    @Nonnull
    AxisAlignedBBView<?> getBoundingBox();

    void setBoundingBox(@Nullable AxisAlignedBBView<?> boundingBox);

    boolean isOnGround();
    void setOnGround(boolean flag);

    boolean isDead();

    float getWidth();
    float getHeight();

    boolean isNoClipping();
    void setNoClipping(boolean flag);

    int getTicksExisted();

    double getDistanceToEntity(@Nonnull EntityView<?> other);
}
