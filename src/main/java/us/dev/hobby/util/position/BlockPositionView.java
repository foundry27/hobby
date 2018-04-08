package us.dev.hobby.util.position;

import us.dev.hobby.View;
import us.dev.hobby.util.DirectionFacingModel;
import us.dev.hobby.util.vector.Vector3IView;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public interface BlockPositionView<T> extends View<T>, Vector3IView<T> {

    /**
     * An immutable block pos with zero as all coordinates.
     */
    BlockPositionView<?> ORIGIN = new BasicBlockPositionView(0, 0, 0);

    int NUM_X_BITS = 25;
    int NUM_Z_BITS = NUM_X_BITS;
    int NUM_Y_BITS = 64 - NUM_X_BITS - NUM_Z_BITS;

    int Y_SHIFT = NUM_Z_BITS;
    int X_SHIFT = Y_SHIFT + NUM_Y_BITS;

    long X_MASK = (1L << NUM_X_BITS) - 1L;
    long Y_MASK = (1L << NUM_Y_BITS) - 1L;
    long Z_MASK = (1L << NUM_Z_BITS) - 1L;

    BlockPositionView<T> add(double x, double y, double z);

    BlockPositionView<T> add(int x, int y, int z);

    BlockPositionView<T> add(Vector3IView<?> vec);

    BlockPositionView<T> subtract(Vector3IView<?> vec);

    BlockPositionView<T> up();

    BlockPositionView<T> up(int n);

    BlockPositionView<T> down();

    BlockPositionView<T> down(int n);

    BlockPositionView<T> north();

    BlockPositionView<T> north(int n);

    BlockPositionView<T> south();

    BlockPositionView<T> south(int n);

    BlockPositionView<T> west();

    BlockPositionView<T> west(int n);

    BlockPositionView<T> east();

    BlockPositionView<T> east(int n);

    BlockPositionView<T> offset(DirectionFacingModel facing);

    BlockPositionView<T> offset(DirectionFacingModel facing, int n);

    @Nonnull
    BlockPositionView<T> crossProduct(@Nonnull Vector3IView<?> vec);

    /**
     * Serialize this BlockPositionModel<T> into a long value
     */
    default long toLong() {
        return ((long) this.getX() & X_MASK) << X_SHIFT | ((long) this.getY() & Y_MASK) << Y_SHIFT | ((long) this.getZ() & Z_MASK);
    }

    /**
     * Returns a version of this BlockPositionModel<T> that is guaranteed to be immutable.
     * <p>
     * <p>When storing a BlockPositionModel<T> given to you for an extended period of time, make sure you
     * use this in case the value is changed internally.</p>
     */
    default BlockPositionView<T> toImmutable() {
        return this;
    }
}
