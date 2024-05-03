package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

import java.util.List;

public class SortGiftBySugarContentCommand implements Command {
    private final GiftDao giftDao;

    public SortGiftBySugarContentCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
        List<Sweets> sortedGift = giftDao.sortGiftBySugarContent();
        sortedGift.forEach(sweet -> System.out.println(sweet.getName() + " - Sugar Content: " + sweet.getSugarContent() + "%"));
    }

    @Override
    public String getDescription() {
        return null;
    }
}
