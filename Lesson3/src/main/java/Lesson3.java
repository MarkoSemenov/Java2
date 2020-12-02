import java.util.*;

public class Lesson3 {
    public static void main(String[] args) {
//ЗАДАНИЕ № 1

        String [] words = {"Планета", "Ракета", "Монополия",
                "Конструкция", "Дерево", "Апельсин",
                "Монополия", "Ракета", "Слово", "Прожектор",
                "Компьютер", "Дом", "Производство", "Машина",
                "Продукт", "Прожектор", "Дерево", "Дерево", "Дерево"};

        List<String> wordsCollection = Arrays.asList(words) ;

        Set<String> fromWordsCollection = new TreeSet<>(wordsCollection); //Способ 1


        System.out.println("Все слова коллекции: " + wordsCollection.size());
        System.out.println(wordsCollection);

        System.out.println("\nУникальные слова без повторов: " + fromWordsCollection.size() + " слов");
        System.out.println(fromWordsCollection);

        Collections.sort(wordsCollection);
        
        System.out.println("\nУникальные слова в коллекции: "); //Способ 2
        List<String> save = new ArrayList<>();
        for (int i = 0; i < wordsCollection.size(); i++) {
            if (Collections.frequency(wordsCollection, wordsCollection.get(i)) == 1) {
                System.out.print(wordsCollection.get(i) + " (" + Collections.frequency(wordsCollection, wordsCollection.get(i)) + "), ");
            } else {
                if (!save.contains(wordsCollection.get(i))) {
                    System.out.print(wordsCollection.get(i) + " (" + Collections.frequency(wordsCollection, wordsCollection.get(i)) + "), ");
                    save.add(wordsCollection.get(i));
                }
            }
        }



        //ЗАДАНИЕ № 2

        Contacts contacts = new Contacts();

        System.out.println("\n");

        contacts.add("Zhdanova", "89629487000");
        contacts.add("Ryazanova", "84950005511");
        contacts.add("Lebedev", "89629487113");
        contacts.add("Semenov", "89629487110");
        contacts.add("Semenov", "89629487188");
        contacts.add("Levin", "84950005566");
        contacts.add("Ryazanova", "84950005222");
        contacts.add("Ryazanova", "84950005333");
        contacts.add("Ryazanova", "84950005333");

        System.out.println();
        contacts.get("Semenov");
        contacts.get("Ryazanova");
        contacts.get("Zhdanova");
        contacts.get("Lebedev");
        contacts.get("Levin");


    }
}

