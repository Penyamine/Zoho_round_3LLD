package FindWife;

import java.util.ArrayList;
import java.util.List;

public class Parent {
    private  Person father,mother;
    private  List<Person> sons=new ArrayList<>();
    private  List<Person> daughters=new ArrayList<>();

    Parent(Person father,Person mother)
    {
        this.father=father;
        this.mother=mother;
    }
    public Person getFather() {
        return father;
    }
    public Person getMother() {
        return mother;
    }
    public void addSons(Person son)
    {
        sons.add(son);
    }
    public void addDaughters(Person daughter)
    {
        daughters.add(daughter);
    }

    public  List<Person> getSons() {
        return sons;
    }

    public  List<Person> getDaughters() {
        return daughters;
    }
}
