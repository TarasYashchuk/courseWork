package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

import java.util.List;

public class SortGiftByWeightCommand implements Command {
    private final GiftDao giftDao;

    public SortGiftByWeightCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
        List<Sweets> sortedGift = giftDao.sortGiftByWeight();
        sortedGift.forEach(sweet -> System.out.println(sweet.getName() + " - Weight: " + sweet.getWeight() + " grams"));
    }

    @Override
    public String getDescription() {
        return null;
    }
}
