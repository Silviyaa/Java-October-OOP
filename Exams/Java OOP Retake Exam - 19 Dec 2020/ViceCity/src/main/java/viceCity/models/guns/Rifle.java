package viceCity.models.guns;

public class Rifle extends BaseGun{

    private static final int BULLETS_PER_BARREL = 50;
    private static final int TOTAL_BULLETS = 500;


    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if (canFire()) {
            super.setTotalBullets(super.getTotalBullets() - 5);
        } else {
            return 0;
        }
        if (super.getTotalBullets() == 0) {
            super.setCanFire(false);
        }
        return 5;
    }
}
