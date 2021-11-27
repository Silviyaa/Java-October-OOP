package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    public GangNeighbourhood() {
    }

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        for (Player civilPlayer : civilPlayers) { // взимаме първото NPC

            for (Gun model : mainPlayer.getGunRepository().getModels()) { //вадим пистолет
                if (!civilPlayer.isAlive()) {
                    break;
                }
                while (model.canFire()) { //повтаряме докато можем да стреляме
                    civilPlayer.takeLifePoints(model.fire());    // взимаме живот на NPC-то
                    if (!civilPlayer.isAlive()) {
                        break;
                    }
                }
            }
        }


        for (Player civilPlayer : civilPlayers) {
            if (!civilPlayer.isAlive()) {
                continue;
            }
            for (Gun model : civilPlayer.getGunRepository().getModels()) {

                while (model.canFire()) {
                    mainPlayer.takeLifePoints(model.fire());
                    if (!mainPlayer.isAlive()) {
                        return;
                    }

                }


            }


        }
    }
}
