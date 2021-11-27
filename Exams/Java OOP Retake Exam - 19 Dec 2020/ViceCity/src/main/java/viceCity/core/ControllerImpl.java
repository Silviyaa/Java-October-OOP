package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private MainPlayer mainPlayer;
    private List<Player> civilPlayersList;
    private ArrayDeque<Gun> gunRepository;
    private GangNeighbourhood gangNeighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayersList = new ArrayList<>();
        this.gangNeighbourhood = new GangNeighbourhood();
        this.gunRepository = new ArrayDeque<>();
    }

    @Override
    public String addPlayer(String name) {
        this.civilPlayersList.add(new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        switch (type) {
            case "Pistol":
                this.gunRepository.offer(new Pistol(name));
                return String.format(GUN_ADDED, name, type);
            case "Rifle":
                this.gunRepository.offer(new Rifle(name));
                return String.format(GUN_ADDED, name, type);
            default:
                return GUN_TYPE_INVALID;
        }
    }

    @Override
    public String addGunToPlayer(String name) {
        if (gunRepository.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }
        String gunName = this.gunRepository.peekFirst().getName();
        if (name.equals("Vercetti")) {
            mainPlayer.getGunRepository().add(this.gunRepository.poll());
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gunName, "Tommy Vercetti");
        } else {
            for (Player civilPlayer : civilPlayersList) {
                if (civilPlayer.getName().equals(name)) {
                    civilPlayer.getGunRepository().add(this.gunRepository.poll());
                    return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gunName, name);
                }
            }
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }
    }

    @Override
    public String fight() {
        gangNeighbourhood.action(mainPlayer, civilPlayersList);
        boolean noFightTookPlace = true;
        if (!mainPlayer.getGunRepository().getModels().isEmpty()) {
            noFightTookPlace = false;
        }
        for (Player player : civilPlayersList) {
            if (!player.getGunRepository().getModels().isEmpty()) {
                noFightTookPlace = false;
            }
        }

        if (noFightTookPlace) {
            return FIGHT_HOT_HAPPENED;
        }
        long deadCivilPlayersCount = civilPlayersList.stream().filter(e -> e.getLifePoints() <= 0).count();
        long aliveCivilPlayersCount = civilPlayersList.size() - deadCivilPlayersCount;

        StringBuilder sb = new StringBuilder();
        sb.append(FIGHT_HAPPENED).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                .append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadCivilPlayersCount))
                .append(System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, aliveCivilPlayersCount));
        return sb.toString();
    }
}
