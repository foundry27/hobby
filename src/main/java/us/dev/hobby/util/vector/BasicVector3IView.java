package us.dev.hobby.util.vector;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * @author Mark Johnson
 */
@Immutable
public class BasicVector3IView implements Vector3IView<Void> {

    private int x;

    private int y;

    private int z;

    public BasicVector3IView(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BasicVector3IView(double x, double y, double z) {
        this.x = (int) x;
        this.y = (int) y;
        this.z = (int) z;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public double getDistance(int xIn, int yIn, int zIn) {
        double d0 = (double) (this.getX() - xIn);
        double d1 = (double) (this.getY() - yIn);
        double d2 = (double) (this.getZ() - zIn);
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    @Override
    public double distanceSq(double toX, double toY, double toZ) {
        double d0 = (double) this.getX() - toX;
        double d1 = (double) this.getY() - toY;
        double d2 = (double) this.getZ() - toZ;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    @Override
    public double distanceSqToCenter(double xIn, double yIn, double zIn) {
        double d0 = (double) this.getX() + 0.5D - xIn;
        double d1 = (double) this.getY() + 0.5D - yIn;
        double d2 = (double) this.getZ() + 0.5D - zIn;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    @Override
    public double distanceSq(@Nonnull Vector3IView<?> to) {
        return this.distanceSq((double) to.getX(), (double) to.getY(), (double) to.getZ());
    }

    @Nonnull
    @Override
    public Vector3IView<Void> crossProduct(@Nonnull Vector3IView<?> vec) {
        return new BasicVector3IView(this.getY() * vec.getZ() - this.getZ() * vec.getY(), this.getZ() * vec.getX() - this.getX() * vec.getZ(), this.getX() * vec.getY() - this.getY() * vec.getX());
    }

    @Override
    public Void reify() {
        return null;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (!(that instanceof Vector3IView)) {
            return false;
        } else {
            Vector3IView<?> vec3i = (Vector3IView<?>) that;
            return this.getX() == vec3i.getX() && (this.getY() == vec3i.getY() && this.getZ() == vec3i.getZ());
        }
    }

    @Override
    public int hashCode() {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }

    @Override
    public int compareTo(@Nonnull Vector3IView<?> vec) {
        return this.getY() == vec.getY() ? (this.getZ() == vec.getZ() ? this.getX() - vec.getX() : this.getZ() - vec.getZ()) : this.getY() - vec.getY();
    }
}
