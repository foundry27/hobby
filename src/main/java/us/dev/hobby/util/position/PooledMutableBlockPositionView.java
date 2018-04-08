package us.dev.hobby.util.position;

import us.dev.hobby.entity.EntityView;
import us.dev.hobby.util.DirectionFacingModel;
import us.dev.hobby.util.vector.Vector3IView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark Johnson
 */
public class PooledMutableBlockPositionView extends MutableBlockPositionView {
    private static final List<PooledMutableBlockPositionView> POOL = new ArrayList<>();
    private boolean released;

    private PooledMutableBlockPositionView(int xIn, int yIn, int zIn) {
        super(xIn, yIn, zIn);
    }

    public static PooledMutableBlockPositionView retain() {
        return retain(0, 0, 0);
    }

    public static PooledMutableBlockPositionView retain(double xIn, double yIn, double zIn) {
        return retain(Math.floor(xIn), Math.floor(yIn), Math.floor(zIn));
    }

    public static PooledMutableBlockPositionView retain(Vector3IView<?> vec) {
        return retain(vec.getX(), vec.getY(), vec.getZ());
    }

    public static PooledMutableBlockPositionView retain(int xIn, int yIn, int zIn) {
        synchronized (POOL) {
            if (!POOL.isEmpty()) {
                PooledMutableBlockPositionView blockpos$pooledmutableblockpos = POOL.remove(POOL.size() - 1);

                if (blockpos$pooledmutableblockpos != null && blockpos$pooledmutableblockpos.released) {
                    blockpos$pooledmutableblockpos.released = false;
                    blockpos$pooledmutableblockpos.setPos(xIn, yIn, zIn);
                    return blockpos$pooledmutableblockpos;
                }
            }
        }

        return new PooledMutableBlockPositionView(xIn, yIn, zIn);
    }

    public void release() {
        synchronized (POOL) {
            if (POOL.size() < 100) {
                POOL.add(this);
            }

            this.released = true;
        }
    }

    public PooledMutableBlockPositionView setPos(int xIn, int yIn, int zIn) {
        if (this.released) {
            System.err.println("PooledMutableBlockPosition modified after it was released.");
            this.released = false;
        }

        return (PooledMutableBlockPositionView) super.setPos(xIn, yIn, zIn);
    }

    public PooledMutableBlockPositionView setPos(EntityView<?> entityIn) {
        return (PooledMutableBlockPositionView) super.setPos(entityIn);
    }

    public PooledMutableBlockPositionView setPos(double xIn, double yIn, double zIn) {
        return (PooledMutableBlockPositionView) super.setPos(xIn, yIn, zIn);
    }

    public PooledMutableBlockPositionView setPos(Vector3IView<?> vec) {
        return (PooledMutableBlockPositionView) super.setPos(vec);
    }

    public PooledMutableBlockPositionView move(DirectionFacingModel facing) {
        return (PooledMutableBlockPositionView) super.move(facing);
    }

    public PooledMutableBlockPositionView move(DirectionFacingModel facing, int p_189534_2_) {
        return (PooledMutableBlockPositionView) super.move(facing, p_189534_2_);
    }
}
