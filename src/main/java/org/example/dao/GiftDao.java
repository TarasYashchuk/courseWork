package org.example.dao;

import org.example.model.Sweets;

import java.util.List;
import java.util.Optional;

public interface GiftDao {
    void add();

    void getBySugarContent();

    Optional<Sweets> get(Long id);

    List<Sweets> getAll();

    void deleteSweetById();

    void calculateGiftWeight();

    List<Sweets> getGiftComposition();

    List<Sweets> sortGiftBySugarContent();

    List<Sweets> sortGiftByName();

    List<Sweets> sortGiftByWeight();

    void printSweetsInfo();
}
