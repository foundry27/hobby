package us.dev.hobby.util.position;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

public interface AxisAlignedBBView<T> extends View<T> {

    double getMinX();

    double getMaxX();

    double getMaxY();

    double getMinY();

    double getMaxZ();

    double getMinZ();

    @Nonnull
    AxisAlignedBBView<T> fromBounds(double x1, double y1, double z1, double x2, double y2, double z2);

    @Nonnull
    AxisAlignedBBView<T> add(double x, double y, double z);

    @Nonnull
    AxisAlignedBBView<T> offset(double x, double y, double z);

    @Nonnull
    AxisAlignedBBView<T> expand(double x, double y, double z);

    @Nonnull
    AxisAlignedBBView<T> contract(double x, double y, double z);

    double calculateXOffset(@Nonnull AxisAlignedBBView<?> other, double distance);

    double calculateYOffset(@Nonnull AxisAlignedBBView<?> other, double distance);

    double calculateZOffset(@Nonnull AxisAlignedBBView<?> other, double distance);
}
