package us.dev.hobby.util;

import us.dev.hobby.util.vector.BasicVector3IView;
import us.dev.hobby.util.vector.Vector3IView;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author Mark Johnson
 */
public enum DirectionFacingModel {
    DOWN(0, 1, -1, "down", AxisDirectionModel.NEGATIVE, AxisModel.Y, new BasicVector3IView(0, -1, 0)),
    UP(1, 0, -1, "up", AxisDirectionModel.POSITIVE, AxisModel.Y, new BasicVector3IView(0, 1, 0)),
    NORTH(2, 3, 2, "north", AxisDirectionModel.NEGATIVE, AxisModel.Z, new BasicVector3IView(0, 0, -1)),
    SOUTH(3, 2, 0, "south", AxisDirectionModel.POSITIVE, AxisModel.Z, new BasicVector3IView(0, 0, 1)),
    WEST(4, 5, 1, "west", AxisDirectionModel.NEGATIVE, AxisModel.X, new BasicVector3IView(-1, 0, 0)),
    EAST(5, 4, 3, "east", AxisDirectionModel.POSITIVE, AxisModel.X, new BasicVector3IView(1, 0, 0));

    /**
     * All facings in D-U-N-S-W-E order
     */
    public static final DirectionFacingModel[] VALUES = new DirectionFacingModel[6];
    /**
     * All Facings with horizontal axis in order S-W-N-E
     */
    private static final DirectionFacingModel[] HORIZONTALS = new DirectionFacingModel[4];
    private static final Map<String, DirectionFacingModel> NAME_LOOKUP = new HashMap<>();

    static {
        for (DirectionFacingModel DirectionFacingModel : values()) {
            VALUES[DirectionFacingModel.index] = DirectionFacingModel;

            if (DirectionFacingModel.getAxis().isHorizontal()) {
                HORIZONTALS[DirectionFacingModel.horizontalIndex] = DirectionFacingModel;
            }

            NAME_LOOKUP.put(DirectionFacingModel.getDirectionName().toLowerCase(), DirectionFacingModel);
        }
    }

    /**
     * Ordering index for D-U-N-S-W-E
     */
    private final int index;
    /**
     * Index of the opposite Facing in the VALUES array
     */
    private final int opposite;
    /**
     * Ordering index for the HORIZONTALS field (S-W-N-E)
     */
    private final int horizontalIndex;
    private final String name;
    private final AxisModel axis;
    private final AxisDirectionModel axisDirection;
    /**
     * Normalized Vector that points in the direction of this Facing
     */
    private final Vector3IView<?> directionVec;

    DirectionFacingModel(int indexIn, int oppositeIn, int horizontalIndexIn, String nameIn, AxisDirectionModel axisDirectionIn, AxisModel axisIn, Vector3IView<?> directionVecIn) {
        this.index = indexIn;
        this.horizontalIndex = horizontalIndexIn;
        this.opposite = oppositeIn;
        this.name = nameIn;
        this.axis = axisIn;
        this.axisDirection = axisDirectionIn;
        this.directionVec = directionVecIn;
    }

    /**
     * Get the facing specified by the given name
     */
    @Nullable
    public static DirectionFacingModel byName(String name) {
        return name == null ? null : NAME_LOOKUP.get(name.toLowerCase());
    }

    /**
     * Get a Facing by it's index (0-5). The order is D-U-N-S-W-E. Named getFront for legacy reasons.
     */
    public static DirectionFacingModel getFront(int index) {
        return VALUES[Math.abs(index % VALUES.length)];
    }

    /**
     * Get a Facing by it's horizontal index (0-3). The order is S-W-N-E.
     */
    public static DirectionFacingModel getHorizontal(int index) {
        return HORIZONTALS[Math.abs(index % HORIZONTALS.length)];
    }

    /**
     * Get the Facing corresponding to the given angle (0-360). An angle of 0 is SOUTH, an angle of 90 would be WEST.
     */
    public static DirectionFacingModel fromAngle(double angle) {
        return getHorizontal((int) Math.floor(angle / 90.0D + 0.5D) & 3);
    }

    /**
     * Choose a random Facing using the given Random
     */
    public static DirectionFacingModel random(Random rand) {
        return values()[rand.nextInt(values().length)];
    }

    public static DirectionFacingModel getFacingFromVector(float x, float y, float z) {
        DirectionFacingModel DirectionFacingModel = NORTH;
        float f = Float.MIN_VALUE;

        for (DirectionFacingModel enumfacing1 : values()) {
            float f1 = x * (float) enumfacing1.directionVec.getX() + y * (float) enumfacing1.directionVec.getY() + z * (float) enumfacing1.directionVec.getZ();

            if (f1 > f) {
                f = f1;
                DirectionFacingModel = enumfacing1;
            }
        }

        return DirectionFacingModel;
    }

    public static DirectionFacingModel getFacingFromAxis(AxisDirectionModel axisDirectionIn, AxisModel axisIn) {
        for (DirectionFacingModel DirectionFacingModel : values()) {
            if (DirectionFacingModel.getAxisDirection() == axisDirectionIn && DirectionFacingModel.getAxis() == axisIn) {
                return DirectionFacingModel;
            }
        }

        throw new IllegalArgumentException("No such direction: " + axisDirectionIn + " " + axisIn);
    }

    /**
     * Get the Index of this Facing (0-5). The order is D-U-N-S-W-E
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Get the index of this horizontal facing (0-3). The order is S-W-N-E
     */
    public int getHorizontalIndex() {
        return this.horizontalIndex;
    }

    /**
     * Get the AxisDirection of this Facing.
     */
    public AxisDirectionModel getAxisDirection() {
        return this.axisDirection;
    }

    /**
     * Get the opposite Facing (e.g. DOWN => UP)
     */
    public DirectionFacingModel getOpposite() {
        return VALUES[this.opposite];
    }

    /**
     * Rotate this Facing around the given axis clockwise. If this facing cannot be rotated around the given axis,
     * returns this facing without rotating.
     */
    public DirectionFacingModel rotateAround(AxisModel axis) {
        switch (axis) {
            case X:
                if (this != WEST && this != EAST) {
                    return this.rotateX();
                }

                return this;

            case Y:
                if (this != UP && this != DOWN) {
                    return this.rotateY();
                }

                return this;

            case Z:
                if (this != NORTH && this != SOUTH) {
                    return this.rotateZ();
                }

                return this;

            default:
                throw new IllegalStateException("Unable to get CW facing for axis " + axis);
        }
    }

    /**
     * Rotate this Facing around the Y axis clockwise (NORTH => EAST => SOUTH => WEST => NORTH)
     */
    public DirectionFacingModel rotateY() {
        switch (this) {
            case NORTH:
                return EAST;

            case EAST:
                return SOUTH;

            case SOUTH:
                return WEST;

            case WEST:
                return NORTH;

            default:
                throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
        }
    }

    /**
     * Rotate this Facing around the X axis (NORTH => DOWN => SOUTH => UP => NORTH)
     */
    private DirectionFacingModel rotateX() {
        switch (this) {
            case NORTH:
                return DOWN;

            case EAST:
            case WEST:
            default:
                throw new IllegalStateException("Unable to get X-rotated facing of " + this);

            case SOUTH:
                return UP;

            case UP:
                return NORTH;

            case DOWN:
                return SOUTH;
        }
    }

    /**
     * Rotate this Facing around the Z axis (EAST => DOWN => WEST => UP => EAST)
     */
    private DirectionFacingModel rotateZ() {
        switch (this) {
            case EAST:
                return DOWN;

            case SOUTH:
            default:
                throw new IllegalStateException("Unable to get Z-rotated facing of " + this);

            case WEST:
                return UP;

            case UP:
                return EAST;

            case DOWN:
                return WEST;
        }
    }

    /**
     * Rotate this Facing around the Y axis counter-clockwise (NORTH => WEST => SOUTH => EAST => NORTH)
     */
    public DirectionFacingModel rotateYCCW() {
        switch (this) {
            case NORTH:
                return WEST;

            case EAST:
                return NORTH;

            case SOUTH:
                return EAST;

            case WEST:
                return SOUTH;

            default:
                throw new IllegalStateException("Unable to get CCW facing of " + this);
        }
    }

    /**
     * Returns a offset that addresses the block in front of this facing.
     */
    public int getFrontOffsetX() {
        return this.axis == AxisModel.X ? this.axisDirection.getOffset() : 0;
    }

    public int getFrontOffsetY() {
        return this.axis == AxisModel.Y ? this.axisDirection.getOffset() : 0;
    }

    /**
     * Returns a offset that addresses the block in front of this facing.
     */
    public int getFrontOffsetZ() {
        return this.axis == AxisModel.Z ? this.axisDirection.getOffset() : 0;
    }

    /**
     * Same as getName, but does not override the method from Enum.
     */
    public String getDirectionName() {
        return this.name;
    }

    public AxisModel getAxis() {
        return this.axis;
    }

    public float getHorizontalAngle() {
        return (float) ((this.horizontalIndex & 3) * 90);
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get a normalized Vector that points in the direction of this Facing.
     */
    public Vector3IView<?> getDirectionVec() {
        return this.directionVec;
    }

    public enum AxisModel implements Predicate<DirectionFacingModel> {
        X("x", PlaneModel.HORIZONTAL),
        Y("y", PlaneModel.VERTICAL),
        Z("z", PlaneModel.HORIZONTAL);

        private static final Map<String, AxisModel> NAME_LOOKUP = new HashMap<>();

        static {
            for (AxisModel enumfacing$axis : values()) {
                NAME_LOOKUP.put(enumfacing$axis.getName2().toLowerCase(), enumfacing$axis);
            }
        }

        private final String name;
        private final PlaneModel plane;

        AxisModel(String name, PlaneModel plane) {
            this.name = name;
            this.plane = plane;
        }

        @Nullable
        public static AxisModel byName(String name) {
            return name == null ? null : NAME_LOOKUP.get(name.toLowerCase());
        }

        public String getName2() {
            return this.name;
        }

        public boolean isVertical() {
            return this.plane == PlaneModel.VERTICAL;
        }

        public boolean isHorizontal() {
            return this.plane == PlaneModel.HORIZONTAL;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean test(@Nullable DirectionFacingModel t) {
            return t != null && t.getAxis() == this;
        }

        public PlaneModel getPlane() {
            return this.plane;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum AxisDirectionModel {
        POSITIVE(1, "Towards positive"),
        NEGATIVE(-1, "Towards negative");

        private final int offset;
        private final String description;

        AxisDirectionModel(int offset, String description) {
            this.offset = offset;
            this.description = description;
        }

        public int getOffset() {
            return this.offset;
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    public enum PlaneModel implements Predicate<DirectionFacingModel>, Iterable<DirectionFacingModel> {
        HORIZONTAL,
        VERTICAL;

        public DirectionFacingModel[] facings() {
            switch (this) {
                case HORIZONTAL:
                    return new DirectionFacingModel[]{DirectionFacingModel.NORTH, DirectionFacingModel.EAST, DirectionFacingModel.SOUTH, DirectionFacingModel.WEST};
                case VERTICAL:
                    return new DirectionFacingModel[]{DirectionFacingModel.UP, DirectionFacingModel.DOWN};
                default:
                    throw new Error("Someone\'s been tampering with the universe!");
            }
        }

        public DirectionFacingModel random(Random rand) {
            final DirectionFacingModel[] facings = this.facings();
            return facings[rand.nextInt(facings.length)];
        }

        @Override
        public boolean test(@Nullable DirectionFacingModel t) {
            return t != null && t.getAxis().getPlane() == this;
        }

        public Iterator<DirectionFacingModel> iterator() {
            return Arrays.asList(this.facings()).iterator();
        }
    }
}
