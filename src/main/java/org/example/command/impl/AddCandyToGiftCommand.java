package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;
import org.example.model.SweetsList;

import java.util.List;

public class AddCandyToGiftCommand implements Command {
    private final GiftDao giftDao;

    public AddCandyToGiftCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
            giftDao.add();
        }

    @Override
    public String getDescription() {
        return "Додати цукерку до подарунка";
    }
}
