package FindWife.Repository;

import FindWife.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonInterface {
    List<Person> persons=new ArrayList<>();
    void addPerson(Person person);
    List<Person> getPersons();
}
