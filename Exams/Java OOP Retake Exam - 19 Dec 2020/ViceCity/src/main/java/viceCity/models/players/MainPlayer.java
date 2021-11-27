package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public class MainPlayer extends BasePlayer{

    private static final String PLAYER_NAME = "Tommy Vercetti";
    private static final int LIFE_POINTS = 100;


    public MainPlayer() {
        super(PLAYER_NAME, LIFE_POINTS);
    }

}
