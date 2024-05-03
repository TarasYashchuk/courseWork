package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

public class RemoveCandyFromGiftCommand implements Command {
    private final GiftDao giftDao;

    public RemoveCandyFromGiftCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
            giftDao.deleteSweetById();
        }

    @Override
    public String getDescription() {
        return "Вилучити цукерку з подарунка";
    }
}
