package FindWife;

import java.util.List;
import java.util.ArrayList;

public class Person {

    private String name;
    private Gender gender;
    private Parent parent;
    private List<Person>  sisters=new ArrayList<>();
    private List<Person>  brothers=new ArrayList<>();

    Person(String name,Gender gender)
    {
        this.name=name;
        this.gender=gender;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Parent getParent() {
        return parent;
    }

    public  List<Person> getSisters() {
        return sisters;
    }

    public  List<Person> getBrothers() {
        return brothers;
    }
    public void addBrother(Person brother)
    {
        brothers.add(brother);
    }
    public void addSister(Person sister)
    {
        sisters.add(sister);
    }
}