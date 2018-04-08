package us.dev.hobby.util.position;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public class BasicAxisAlignedBBView implements AxisAlignedBBView<Void> {
    @Override
    public Void reify() {
        return null;
    }

    @Override
    public double getMinX() {
        return 0;
    }

    @Override
    public double getMaxX() {
        return 0;
    }

    @Override
    public double getMaxY() {
        return 0;
    }

    @Override
    public double getMinY() {
        return 0;
    }

    @Override
    public double getMaxZ() {
        return 0;
    }

    @Override
    public double getMinZ() {
        return 0;
    }

    @Nonnull
    @Override
    public AxisAlignedBBView<Void> fromBounds(double x1, double y1, double z1, double x2, double y2, double z2) {
        return null;
    }

    @Nonnull
    @Override
    public AxisAlignedBBView<Void> add(double x, double y, double z) {
        return null;
    }

    @Nonnull
    @Override
    public AxisAlignedBBView<Void> offset(double x, double y, double z) {
        return null;
    }

    @Nonnull
    @Override
    public AxisAlignedBBView<Void> expand(double x, double y, double z) {
        return null;
    }

    @Nonnull
    @Override
    public AxisAlignedBBView<Void> contract(double x, double y, double z) {
        return null;
    }

    @Override
    public double calculateXOffset(@Nonnull AxisAlignedBBView<?> other, double distance) {
        return 0;
    }

    @Override
    public double calculateYOffset(@Nonnull AxisAlignedBBView<?> other, double distance) {
        return 0;
    }

    @Override
    public double calculateZOffset(@Nonnull AxisAlignedBBView<?> other, double distance) {
        return 0;
    }
}
