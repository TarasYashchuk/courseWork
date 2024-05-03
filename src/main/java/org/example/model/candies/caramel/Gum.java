package org.example.model.candies.caramel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.model.Sweets;

@Entity
@DiscriminatorValue("Gum")
public class Gum extends Sweets {

    private double sugarContent;
    public Gum(String name, double weight, double sugarContent){
        super(name,weight,sugarContent);
        this.sugarContent = sugarContent;
    }

    public Gum() {

    }

    @Override

    public double getSugarContent() {
        return sugarContent;
    }
    @Override
    public String toString(){
        return "------------------------------------------------------------------" + "\n" +
                "Назва цукерки: " + getName() + "\n" +
                "Тип: жувальна гумка " + "\n" +
                "Вага: " + getWeight() + "\n" +
                "Вміст цукру: " + getSugarContent() * 100 + "%";
    }
}