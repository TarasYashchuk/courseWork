package org.example.command.impl;

import org.example.command.Command;
import org.example.dao.GiftDao;
import org.example.model.Sweets;

public class FindCandyBySugarContentCommand implements Command {
    private final GiftDao giftDao;

    public FindCandyBySugarContentCommand(GiftDao giftDao) {
        this.giftDao = giftDao;
    }

    @Override
    public void execute() {
            giftDao.getBySugarContent();
    }

    @Override
    public String getDescription() {
        return "Знайти цукерку за певним вмістом цукру";
    }
}
