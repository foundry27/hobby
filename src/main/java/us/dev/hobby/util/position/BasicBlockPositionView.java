package us.dev.hobby.util.position;

import us.dev.hobby.entity.EntityView;
import us.dev.hobby.util.DirectionFacingModel;
import us.dev.hobby.util.vector.BasicVector3IView;
import us.dev.hobby.util.vector.Vector3IView;

import javax.annotation.Nonnull;

/**
 * @author Mark Johnson
 */
public class BasicBlockPositionView extends BasicVector3IView implements BlockPositionView<Void> {

    public BasicBlockPositionView(int x, int y, int z) {
        super(x, y, z);
    }

    public BasicBlockPositionView(double x, double y, double z) {
        super(x, y, z);
    }

    public BasicBlockPositionView(EntityView<?> source) {
        this(source.getX(), source.getY(), source.getZ());
    }

    public BasicBlockPositionView(Vector3IView<?> source) {
        this(source.getX(), source.getY(), source.getZ());
    }

    /**
     * Create a BlockPositionModel<Void> from a serialized long value (created by toLong)
     */
    public static BlockPositionView<?> fromLong(long serialized) {
        int i = (int) (serialized << 64 - X_SHIFT - NUM_X_BITS >> 64 - NUM_X_BITS);
        int j = (int) (serialized << 64 - Y_SHIFT - NUM_Y_BITS >> 64 - NUM_Y_BITS);
        int k = (int) (serialized << 64 - NUM_Z_BITS >> 64 - NUM_Z_BITS);
        return new BasicBlockPositionView(i, j, k);
    }

    /**
     * Add the given coordinates to the coordinates of this BlockPositionModel<Void>
     */
    public BlockPositionView<Void> add(double x, double y, double z) {
        return x == 0.0D && y == 0.0D && z == 0.0D ? this : new BasicBlockPositionView((double) this.getX() + x, (double) this.getY() + y, (double) this.getZ() + z);
    }

    /**
     * Add the given coordinates to the coordinates of this BlockPositionModel<Void>
     */
    public BlockPositionView<Void> add(int x, int y, int z) {
        return x == 0 && y == 0 && z == 0 ? this : new BasicBlockPositionView(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    /**
     * Add the given Vector to this BlockPositionModel<Void>
     */
    public BlockPositionView<Void> add(Vector3IView<?> vec) {
        return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0 ? this : new BasicBlockPositionView(this.getX() + vec.getX(), this.getY() + vec.getY(), this.getZ() + vec.getZ());
    }

    /**
     * Subtract the given Vector from this BlockPositionModel<Void>
     */
    public BlockPositionView<Void> subtract(Vector3IView<?> vec) {
        return vec.getX() == 0 && vec.getY() == 0 && vec.getZ() == 0 ? this : new BasicBlockPositionView(this.getX() - vec.getX(), this.getY() - vec.getY(), this.getZ() - vec.getZ());
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block up
     */
    public BlockPositionView<Void> up() {
        return this.up(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks up
     */
    public BlockPositionView<Void> up(int n) {
        return this.offset(DirectionFacingModel.UP, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block down
     */
    public BlockPositionView<Void> down() {
        return this.down(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks down
     */
    public BlockPositionView<Void> down(int n) {
        return this.offset(DirectionFacingModel.DOWN, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block in northern direction
     */
    public BlockPositionView<Void> north() {
        return this.north(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks in northern direction
     */
    public BlockPositionView<Void> north(int n) {
        return this.offset(DirectionFacingModel.NORTH, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block in southern direction
     */
    public BlockPositionView<Void> south() {
        return this.south(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks in southern direction
     */
    public BlockPositionView<Void> south(int n) {
        return this.offset(DirectionFacingModel.SOUTH, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block in western direction
     */
    public BlockPositionView<Void> west() {
        return this.west(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks in western direction
     */
    public BlockPositionView<Void> west(int n) {
        return this.offset(DirectionFacingModel.WEST, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block in eastern direction
     */
    public BlockPositionView<Void> east() {
        return this.east(1);
    }

    /**
     * Offset this BlockPositionModel<Void> n blocks in eastern direction
     */
    public BlockPositionView<Void> east(int n) {
        return this.offset(DirectionFacingModel.EAST, n);
    }

    /**
     * Offset this BlockPositionModel<Void> 1 block in the given direction
     */
    public BlockPositionView<Void> offset(DirectionFacingModel facing) {
        return this.offset(facing, 1);
    }

    /**
     * Offsets this BlockPositionModel<Void> n blocks in the given direction
     */
    public BlockPositionView<Void> offset(DirectionFacingModel facing, int n) {
        return n == 0 ? this : new BasicBlockPositionView(this.getX() + facing.getFrontOffsetX() * n, this.getY() + facing.getFrontOffsetY() * n, this.getZ() + facing.getFrontOffsetZ() * n);
    }

    /**
     * Calculate the cross product of this and the given Vector
     */
    @Nonnull
    public BlockPositionView<Void> crossProduct(@Nonnull Vector3IView<?> vec) {
        return new BasicBlockPositionView(this.getY() * vec.getZ() - this.getZ() * vec.getY(), this.getZ() * vec.getX() - this.getX() * vec.getZ(), this.getX() * vec.getY() - this.getY() * vec.getX());
    }
}
