package us.dev.hobby.util.vector;

import us.dev.hobby.View;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public interface Vector3IView<T> extends View<T>, Comparable<Vector3IView<?>> {

    Vector3IView<?> NULL_VECTOR = new BasicVector3IView(0, 0, 0);

    int getX();

    int getY();

    int getZ();

    @Nonnull
    Vector3IView<T> crossProduct(@Nonnull Vector3IView<?> vec);

    double getDistance(int xIn, int yIn, int zIn);

    double distanceSq(double toX, double toY, double toZ);

    double distanceSqToCenter(double xIn, double yIn, double zIn);

    double distanceSq(@Nonnull Vector3IView<?> to);
}
