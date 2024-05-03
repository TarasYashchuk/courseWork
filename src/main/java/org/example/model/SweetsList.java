package org.example.model;

import org.example.model.candies.caramel.CaramelCandy;
import org.example.model.candies.caramel.Gum;
import org.example.model.candies.chocolatecandy.DarkChocolate;
import org.example.model.candies.chocolatecandy.MilkChocolate;
import org.example.model.candies.marmalade.FruitMarmalade;
import org.example.model.candies.marmalade.JellyMarmalade;
import org.example.model.candies.popsicles.FruitPopsicles;
import org.example.model.candies.popsicles.MilkPopsicles;
import org.example.model.other.chocolatebars.CaramelChocolateBar;
import org.example.model.other.chocolatebars.NutChocolateBar;
import org.example.model.other.cookies.ButterCookie;
import org.example.model.other.cookies.ChocolateCookie;
import org.example.model.other.gingerbread.HoneyGingerbread;
import org.example.model.other.gingerbread.OrangeGingerbread;

import java.util.ArrayList;
import java.util.List;

public class SweetsList {
    public static List<Sweets> getSweetsList() {

        List<Sweets> sweets = new ArrayList<>();

        sweets.add(new CaramelCandy("Карамельна мелодія", 25.0, 0.2));
        sweets.add(new Gum("Золотий шарм", 15.0, 0.4));

        sweets.add(new DarkChocolate("Шоколадний розкіш", 40.0, 0.25));
        sweets.add(new MilkChocolate("Кокосова мрія", 35.0, 0.5));

        sweets.add(new FruitMarmalade("Фруктовий експрес", 10.0, 0.3));
        sweets.add(new JellyMarmalade("Сонячні мармелади", 10.0, 0.4));

        sweets.add(new FruitPopsicles("Апельсиновий відпочинок", 15.0, 0.6));
        sweets.add(new MilkPopsicles("Молочний куш", 10.0, 0.6));

        sweets.add(new CaramelChocolateBar("Шоколадний спокусник", 70.0, 0.5));
        sweets.add(new NutChocolateBar("Горішкова феєрія", 65.0, 0.4));

        sweets.add(new ButterCookie("Вишукана ніжність", 30.0, 0.6));
        sweets.add(new ChocolateCookie("Шоколадний трепет", 45.0, 0.7));

        sweets.add(new HoneyGingerbread("Медова кульбаба", 55.0, 0.3));
        sweets.add(new OrangeGingerbread("Мандаринова історія", 50.0, 0.4));

        return sweets;
    }
}
