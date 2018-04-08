package us.dev.hobby.util.position;

import us.dev.hobby.entity.EntityView;
import us.dev.hobby.util.DirectionFacingModel;
import us.dev.hobby.util.vector.Vector3IView;

/**
 * @author Mark Johnson
 */
public class MutableBlockPositionView extends BasicBlockPositionView {
    protected int x;
    protected int y;
    protected int z;

    public MutableBlockPositionView() {
        this(0, 0, 0);
    }

    public MutableBlockPositionView(BlockPositionView<?> pos) {
        this(pos.getX(), pos.getY(), pos.getZ());
    }

    public MutableBlockPositionView(int x_, int y_, int z_) {
        super(0, 0, 0);
        this.x = x_;
        this.y = y_;
        this.z = z_;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int yIn) {
        this.y = yIn;
    }

    public int getZ() {
        return this.z;
    }

    public MutableBlockPositionView setPos(int xIn, int yIn, int zIn) {
        this.x = xIn;
        this.y = yIn;
        this.z = zIn;
        return this;
    }

    public MutableBlockPositionView setPos(EntityView<?> entityIn) {
        return this.setPos(entityIn.getX(), entityIn.getY(), entityIn.getZ());
    }

    public MutableBlockPositionView setPos(double xIn, double yIn, double zIn) {
        return this.setPos((int) Math.floor(xIn), (int) Math.floor(yIn), (int) Math.floor(zIn));
    }

    public MutableBlockPositionView setPos(Vector3IView<?> vec) {
        return this.setPos(vec.getX(), vec.getY(), vec.getZ());
    }

    public MutableBlockPositionView move(DirectionFacingModel facing) {
        return this.move(facing, 1);
    }

    public MutableBlockPositionView move(DirectionFacingModel facing, int multiplier) {
        return this.setPos(this.x + facing.getFrontOffsetX() * multiplier, this.y + facing.getFrontOffsetY() * multiplier, this.z + facing.getFrontOffsetZ() * multiplier);
    }

    public BlockPositionView<Void> toImmutable() {
        return new BasicBlockPositionView(this);
    }
}