package FindWife;

import FindWife.Repository.ParentRepository;
import FindWife.Repository.PersonInterface;

import java.util.List;
import java.util.Scanner;

public class RunFindWife implements PersonInterface , ParentRepository {
    public static void main(String[] args) {

        RunFindWife runFindWife=new RunFindWife();
        Scanner sc;
        if(ScannerObject.getScanner()==null)
        {
            ScannerObject.setScanner(new Scanner(System.in));
        }
        sc=ScannerObject.getScanner();
        int cont=0;
        do {
            String input = sc.nextLine();
            String[] data = input.split(",");
            Gender gender;
            if (data[1].toLowerCase().equals(Gender.male + ""))
                gender = Gender.male;
            else
                gender = Gender.female;

            Person person = new Person(data[0], gender);
            runFindWife.addPerson(person);
            Parent parent = runFindWife.isAvailableParent(data[2],data[3]);
            if (parent == null) {
                Person father=runFindWife.isAvailablePerson(data[2]);
                if(father==null) {
                    father = new Person(data[2], Gender.male);
                    persons.add(father);
                }
                Person mother =runFindWife.isAvailablePerson(data[3]);
                if(mother==null) {
                    mother = new Person(data[3], Gender.female);
                    persons.add(mother);
                }
                parent = new Parent(father, mother);
                runFindWife.addParent(parent);
            }
            else {
                for (Person p : parent.getSons()) {
                    person.addBrother(p);
                    if (person.getGender().equals(Gender.male))
                        p.addBrother(person);
                    else
                        p.addSister(person);
                }
                for (Person p : parent.getDaughters())
                {
                    person.addSister(p);
                    if(person.getGender().equals(Gender.male))
                        p.addBrother(person);
                    else
                        p.addSister(person);

                }
            }
            // set parent to son
            person.setParent(parent);
            // set son to parent
            runFindWife.setChildToParent(person, parent);
            System.out.println("enter 1 to continue..");
            cont=sc.nextInt();
            sc.nextLine();
        }
        while (cont==1);

//        System.out.println(runFindWife.getParents().size());
/*
        for(Parent parent: runFindWife.getParents())
        {
            System.out.println("Parent "+parent +" "+parent.getFather().getName()+" "+
                    parent.getMother().getName());
            System.out.println("Child : ");
            for(Person son:parent.getSons())
                System.out.print(son.getName()+" ");
            System.out.println();
            for(Person daughter:parent.getDaughters())
                System.out.print(daughter.getName()+" ");
        }
*/

/*
        int ch=0;
        do {
            String father=sc.next();
            String mother=sc.next();
            runFindWife.getChild(father,mother);
            System.out.println("enter 1 to continue..");
            ch=sc.nextInt();
        }
        while (ch==1);
*/
        System.out.println("Enter Name to Marry ");
        String name_want_to_marry=sc.next();
//        for(Person p: runFindWife.getPersons())
//            System.out.println(p.getName());
        for(Parent parent:runFindWife.getParents())
        {
            for(Person person:parent.getSons())
            {
                if(person.getName().equals(name_want_to_marry))
                {
                    runFindWife.getWife(person,parent);
                    return;
                }
            }
        }
//        Person grad=runFindWife.getPersons().stream().filter(
//                i -> i.getName().equals(name_want_to_marry)
//        ).findAny().orElse(null);
//        if(grad==null)
//            System.out.println("No person found ...");
//        else
//            runFindWife.getWife(grad);
    }
    void getChild(String father,String mother)
    {
        Parent parent=isAvailableParent(father,mother);
        if(parent==null)
        {
            System.out.println("No parent");
            return;
        }
        System.out.println("Sons===>"+parent.getSons().size());
        for(Person son:parent.getSons())
            System.out.println(son.getName());
        System.out.println("Daughters===>"+parent.getDaughters().size());
        for(Person daughter:parent.getDaughters())
            System.out.println(daughter.getName());
    }
    void setChildToParent(Person child,Parent parent)
    {
/*
        for(Parent p:getParents())
        {
            System.out.println((p.getSons().size()+p.getDaughters().size()));
        }
        System.out.println(parent.getFather().getName()+" "
                +parent.getMother().getName());
        for(Parent p:getParents())
            System.out.println(p);
        System.out.println(parent);
*/
        if(child.getGender().equals(Gender.male))
            parent.addSons(child);
        else
            parent.addDaughters(child);
//        System.out.println("Child for this Parent : "+(parent.getSons().size()+parent.getDaughters().size()));
//        System.out.println("Sons "+parent.getSons());
//        System.out.println("Daughter "+parent.getDaughters());
    }
    void getWife(Person person,Parent parent)
    {
//        System.out.println("Hello");
//        Parent parent=person.getParent();
        Person father =parent.getFather();
//        System.out.println("Person "+father);
        Person mother=parent.getMother();

//        System.out.println(father.getName()+" "+mother.getName());
//        System.out.println(father.getSisters().size()+" "+mother.getBrothers().size());
        if(!father.getSisters().isEmpty())
        {
//            System.out.println(father.getSisters().size());
            for(Person sister_of_father: father.getSisters())
            {
//                System.out.println(sister_of_father.getName());
                Parent parent_of_future_wife=getParent(sister_of_father);
                for(Person wife:parent_of_future_wife.getDaughters())
                    System.out.println(wife.getName());
            }
        }
        if(!mother.getBrothers().isEmpty())
        {
//            System.out.println(mother.getBrothers().size());
            for(Person brother_of_mother: mother.getBrothers())
            {
                Parent parent_of_future_wife=getParent(brother_of_mother);
                for(Person wife:parent_of_future_wife.getDaughters())
                    System.out.println(wife.getName());
            }
        }
    }
    Parent getParent(Person father_or_mother)
    {
        return getParents().stream().filter(i->
                i.getFather().equals(father_or_mother)||
                i.getMother().equals(father_or_mother)).
                findAny().get();
    }
    public Parent isAvailableParent(String father, String mother)
    {
        return getParents().stream().filter(i->i.getFather().getName().equals(father) &&
                i.getMother().getName().equals(mother)).findAny().orElse(null);
    }
    Person isAvailablePerson(String person)
    {
        return getPersons().stream().filter(i->i.getName().equals(person)
                ).findAny().orElse(null);
    }
    @Override
    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void addParent(Parent parent) {
        parents.add(parent);
    }

    @Override
    public List<Parent> getParents() {
        return parents;
    }
}
