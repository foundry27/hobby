package us.dev.hobby.entity.living;

import us.dev.hobby.entity.EntityView;

public interface EntityLivingView<T> extends EntityView<T> {

    float getHealth();

    void setHealth(float health);
}
