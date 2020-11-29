
public class Lesson2 {
    public static void main(String[] args) {

        int result;

        String[][] array = {
                {"1", "5", "3", "5"},
                {"5", "4", "7", "8"},
                {"11", "105", "13", "14"},
                {"16", "18", "78", "3"},
//                {"2","3","3","5","55"},
        };

        try {
           result = MyArray.workWithArray(array);
           System.out.println("Сумма всех элементов массива: " + result);

        } catch (MyArraySizeException | MyArrayDataException e) {
           System.out.println(e.getMessage());
        }

    }
}




