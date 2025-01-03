package FindWife.Repository;

import FindWife.Parent;

import java.util.ArrayList;
import java.util.List;

public interface ParentRepository {
    List<Parent> parents=new ArrayList<>();
    void addParent(Parent parent);
    List<Parent> getParents();
    Parent isAvailableParent(String father,String mother);
}
