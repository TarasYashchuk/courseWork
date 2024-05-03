package org.example.model.candies.caramel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.model.Sweets;

@Entity
@DiscriminatorValue("CaramelCandy")
public class CaramelCandy extends Sweets {

    private double sugarContent;

    public CaramelCandy(String name, double weight, double sugarContent){
        super(name,weight,sugarContent);
        this.sugarContent = sugarContent;
    }

    public CaramelCandy() {

    }

    @Override
    public double getSugarContent() {
        return sugarContent;
    }

    @Override
    public String toString(){
        return "------------------------------------------------------------------" + "\n" +
                "Назва цукерки: " + getName() + "\n" +
                "Тип: карамель " + "\n" +
                "Вага: " + getWeight() + "\n" +
                "Вміст цукру: " + getSugarContent() * 100  + "%";
    }
}