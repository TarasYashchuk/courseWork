package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

public class CalculateGiftWeightCommand implements Command {
    private final GiftDao giftDao;

    public CalculateGiftWeightCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
        giftDao.calculateGiftWeight();
    }

    @Override
    public String getDescription() {
        return "Вага всього подарунка";
    }
}
