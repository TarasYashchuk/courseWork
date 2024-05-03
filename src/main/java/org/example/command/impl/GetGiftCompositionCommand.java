package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

import java.util.List;

public class GetGiftCompositionCommand implements Command {
    private GiftDao giftDao;

    public GetGiftCompositionCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
        List<Sweets> giftComposition = giftDao.getGiftComposition();
        if (giftComposition.isEmpty()) {
            System.out.println("Склад подарунка порожній.");
        } else {
            System.out.println("Склад вашого подарунку:");
            for (Sweets sweet : giftComposition) {
                System.out.println(sweet.getName());
            }
        }
    }

    @Override
    public String getDescription() {
        return "Вміст подарунка";
    }
}
