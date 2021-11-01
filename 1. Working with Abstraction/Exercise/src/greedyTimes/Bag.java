package greedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentWeight;

    //gold
    private long totalGold;
    private boolean isAdGold;
    //gems
    private long totalGems;
    private Map<String, Long> gems;
    //cash
    private long totalCash;
    private Map<String, Long> cash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.currentWeight = 0;
        this.totalGems = 0;
        this.gems = new HashMap<>();
        this.totalGold = 0;
        this.totalCash = 0;
        this.cash = new HashMap<>();
        this.isAdGold = false;
    }

    public void addCash(String currency, long quantity) {
        //1. проверка дали има място
        //2. добавяме си парите в map-a
        //3. увеличаваме обшата сума на парите
        //4. увеличаваме текущото тегло
        if (hasFreeSpace(quantity) && this.totalGems >= this.totalCash + quantity) {
            // мога да добавям пари
            if (!this.cash.containsKey(currency)) {
                this.cash.put(currency, quantity);
            } else {
                this.cash.put(currency, this.cash.get(currency) + quantity);
            }
            this.totalCash += quantity;
            this.currentWeight += quantity;
        }
//        if (!cash.containsKey(currency)) {
//            if (cash.containsKey("Gem")) {
//                if (quantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//                    continue;
//                }
//            } else {
//                continue;
//            }
//        } else if (bag.get(item).values().stream().mapToLong(e -> e).sum() + number > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
//            continue;
//        }
//        break;
    }

    public void addGems(String gem, long quantity) {
        if (hasFreeSpace(quantity) && this.totalGems + quantity <= this.totalGold) {
            // мога да добавям пари
            if (!this.gems.containsKey(gem)) {
                this.gems.put(gem, quantity);
            } else {
                this.gems.put(gem, this.cash.get(gem) + quantity);
            }
            this.totalGems += quantity;
            this.currentWeight += quantity;
        }

//        if (!bag.containsKey(item)) {
//            if (bag.containsKey("Gold")) {
//                if (quantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//                    continue;
//                }
//            } else {
//                continue;
//            }
//        } else if (bag.get(item).values().stream().mapToLong(e -> e).sum() + weight > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
//            continue;
//        }
    }

    public void addGold(long quantity) {
        if (this.hasFreeSpace(quantity)) {
            this.totalGold += quantity;
            this.currentWeight += quantity;
            this.isAdGold = true;
        }
    }

    private boolean hasFreeSpace(long quantity) {
        return this.currentWeight + quantity <= this.capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isAdGold){
            sb.append("<Gold> $").append(this.totalGold).append(System.lineSeparator());
            sb.append("##Gold - ").append(this.totalGold).append(System.lineSeparator());
        }

        if (this.gems.size() > 0) {
            sb.append("<Gem> $").append(this.totalGems).append(System.lineSeparator());
            this.gems.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey()); // descender order - от второто(s) с първото(f)
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue()); // ascender order - от първото(f) с второто(s)
                }
                return result;
            }).forEach(entry -> {
                sb.append("##")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue())
                        .append(System.lineSeparator());
            });
        }


        if (this.cash.size() > 0) {
            sb.append("<Cash> $").append(this.totalCash).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(entry -> {
                sb.append("##")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue())
                        .append(System.lineSeparator());

            });
        }
        return sb.toString();
    }
}
