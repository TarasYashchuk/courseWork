package org.example.dao.impl;

import org.example.dao.GiftDao;
import org.example.exception.DataProcessingException;
import org.example.model.Sweets;
import org.example.model.SweetsList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.example.runner.Main.logger;

public class GiftDaoImpl implements GiftDao {
    private static final List<Sweets> SWEETS_LIST = SweetsList.getSweetsList();

    @Override
    public void add() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            logger.info("Команда 'addCandyToGift' почала роботу.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Виберіть цукерки (exit для завершення) ");

            for (int i = 0; i < SWEETS_LIST.size(); i++) {
                System.out.println((i + 1) + ". " + SWEETS_LIST.get(i).getName());
            }
            while (true) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    logger.info("Команда 'addCandyToGift' закінчила роботу.");
                    break;
                }

                try {
                    int candyIndex = Integer.parseInt(input) - 1;
                    if (candyIndex >= 0 && candyIndex < SWEETS_LIST.size()) {
                        Sweets selectedCandy = SWEETS_LIST.get(candyIndex);
                        session.save(selectedCandy);
                        System.out.println("Цукерка " + "'" + selectedCandy.getName() + "'" + " додана до подарунку");
                        logger.info("Додана цукерка до подарунку: " + selectedCandy.getName());
                    } else {
                        System.out.println("Неправильний номер. Спробуйте ще раз");
                        logger.warn("Спроба додати цукерку за неправильним номером: " + input);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некоректне введення. Спробуйте ще раз");
                    logger.warn("Помилка при спробі додати цукерку: " + e.getMessage());
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add candy", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void getBySugarContent() {
        List<Sweets> candyBySugar;
        Transaction transaction = null;
        Session session = null;

        logger.info("Команда 'findCandyBySugarContent' почала роботу.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть діапазон цукру (у відсотках):");

        try {
            System.out.print("Від: ");
            double choice1 = scanner.nextDouble();
            System.out.print("До: ");
            double choice2 = scanner.nextDouble();

            logger.info("Введено діапазон цукру: Від " + choice1 + " До " + choice2);

            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query<Sweets> query = session.createQuery("FROM Sweets WHERE sugarContent BETWEEN :choice1 AND :choice2");
            query.setParameter("choice1", choice1);
            query.setParameter("choice2", choice2);
            candyBySugar = query.list();

            transaction.commit();

            if (candyBySugar.isEmpty()) {
                System.out.println("Таких цукерок немає");

                logger.info("Таких цукерок немає у діапазоні [" + choice1 + ";" + choice2 + "]");

            } else {
                System.out.println("Цукерки за вмістом цукру в діапазоні [" + choice1 + ";" + choice2 + "]:");
                for (Sweets candy : candyBySugar) {
                    System.out.println(candy.getName() + " - Вміст цукру: " + candy.getSugarContent() + "%");
                }

                logger.info("Знайдено цукерок у діапазоні [" + choice1 + ";" + choice2 + "]");
            }
        } catch (InputMismatchException e) {
            System.out.println("Невірний ввід. Введіть числове значення.");

            logger.warn("Невірний ввід при пошуку цукерок за вмістом цукру: " + e.getMessage());
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to find candy by sugar content", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Sweets> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Sweets.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie by id: " + id, e);
        }
    }

    @Override
    public List<Sweets> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT s FROM Sweets s", Sweets.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteSweetById() {
        Transaction transaction = null;
        Scanner scanner = new Scanner(System.in);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            List<Sweets> giftComposition = session.createQuery("FROM Sweets", Sweets.class).getResultList();
            if (giftComposition.isEmpty()) {
                System.out.println("Склад подарунка порожній.");
                return;
            } else {
                System.out.println("Склад вашого подарунку:");
                for (Sweets sweet : giftComposition) {
                    System.out.println("ID: " + sweet.getId() + ", " + sweet.getName());
                }
            }

            System.out.println("Введіть ID цукерки, яку бажаєте вилучити, або 'exit' для виходу:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Вилучення цукерки скасовано.");
                return;
            }

            try {
                Long candyId = Long.parseLong(input);
                Sweets sweetToDelete = session.get(Sweets.class, candyId);
                if (sweetToDelete != null) {
                    session.remove(sweetToDelete);
                    System.out.println("Цукерка " + sweetToDelete.getName() + " вилучена із вашого подарунку");

                    giftComposition = session.createQuery("FROM Sweets", Sweets.class).getResultList();
                    if (!giftComposition.isEmpty()) {
                        System.out.println("Оновлений склад вашого подарунку:");
                        for (Sweets sweet : giftComposition) {
                            System.out.println("ID: " + sweet.getId() + ", " + sweet.getName());
                        }
                    } else {
                        System.out.println("Склад подарунка порожній.");
                    }
                } else {
                    System.out.println("Цукерка з таким ID не знайдена.");
                    logger.error("Помилка видалення цукерки: цукерка з ID " + candyId + " не знайдена");
                    logger.error("Видалення цукерки: цукерка з ID " + candyId + " не знайдена");
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірний формат ID. Спробуйте ще раз.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void calculateGiftWeight() {
        logger.info("Команда 'calculateGiftWeight' почала роботу.");
        double totalWeight = 0.0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Sweets> sweetsList = session.createQuery("SELECT s FROM Sweets s", Sweets.class).getResultList();
            for (Sweets sweets : sweetsList) {
                totalWeight += sweets.getWeight();
            }
            System.out.println("Загальна вага подарунка = " + totalWeight + " грам");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sweets> getGiftComposition() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Sweets", Sweets.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Sweets> sortGiftBySugarContent() {
        List<Sweets> giftComposition = getGiftComposition();
        giftComposition.sort(Comparator.comparingDouble(Sweets::getSugarContent));
        return giftComposition;
    }

    @Override
    public List<Sweets> sortGiftByName() {
        List<Sweets> giftComposition = getGiftComposition();
        giftComposition.sort(Comparator.comparing(Sweets::getName));
        return giftComposition;
    }

    @Override
    public List<Sweets> sortGiftByWeight() {
        List<Sweets> giftComposition = getGiftComposition();
        giftComposition.sort(Comparator.comparingDouble(Sweets::getWeight));
        return giftComposition;
    }

    @Override
    public void printSweetsInfo() {
        List<Sweets> gift = getGiftComposition();
        for (Sweets sweets : gift) {
            System.out.println(sweets.toString());
        }
    }
}
