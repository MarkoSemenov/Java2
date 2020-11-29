public class MyArrayDataException extends NumberFormatException{

    public MyArrayDataException(int i, int j) {
        super("Преобразование массива прервано. Несоответствие типов данных в ячейке y:" + i + " x:" + j);
    }
}
