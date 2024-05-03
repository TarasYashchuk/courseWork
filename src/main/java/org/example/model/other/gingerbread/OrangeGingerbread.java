package org.example.model.other.gingerbread;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.example.model.Sweets;

@Entity
@DiscriminatorValue("OrangeGingerbread")
public class OrangeGingerbread extends Sweets {
    private double sugarContent;

    public OrangeGingerbread(String name, double weight, double sugarContent){
        super(name,weight,sugarContent);
        this.sugarContent = sugarContent;
    }

    public OrangeGingerbread() {

    }

    @Override
    public double getSugarContent() {
        return  sugarContent;
    }

    @Override
    public String toString(){
        return "------------------------------------------------------------------" + "\n" +
                "Назва цукерки: " + getName() + "\n" +
                "Тип: пряник " + "\n" +
                "Вага: " + getWeight() + "\n" +
                "Вміст цукру: " + getSugarContent() * 100 + "%" + "\n";
    }
}