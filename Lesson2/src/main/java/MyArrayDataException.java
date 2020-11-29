public class MyArrayDataException extends NumberFormatException{

    public MyArrayDataException(int i, int j) {
        super("Преобразование массива прервано. Несоответстиве типов данных в ячейке y:" + i + " x:" + j);
    }
}
