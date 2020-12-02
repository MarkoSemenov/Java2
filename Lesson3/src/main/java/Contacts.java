import java.util.*;

public class Contacts {

    private final Map<String, Set<String>> phonebook;

    public Contacts() {
        phonebook = new TreeMap<>();
    }

    public Contacts(Map<String, Set<String>> phonebook) {
        this.phonebook = phonebook;
    }

    public void add (String surname, String phoneNumber){

        if (phonebook.containsKey(surname) && phonebook.get(surname).contains(phoneNumber)){
            System.out.println("Контакт: " + surname + " с номером: " + phoneNumber + " уже существует");

        } else if (!phonebook.containsKey(surname)){
            this.phonebook.put(surname, new LinkedHashSet<>());
            phonebook.get(surname).add(phoneNumber);

        } else if (phonebook.containsKey(surname)){
            phonebook.get(surname).add(phoneNumber);
        }

    }

    public void get (String surname){
        if (!phonebook.isEmpty() && phonebook.containsKey(surname)) {
            System.out.println("Фамилия: " + surname + ", Номер телефона: " + phonebook.get(surname));
        } else System.out.println("Контакт не найден");
    }
}
