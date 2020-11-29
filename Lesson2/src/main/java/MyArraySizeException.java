public class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    public MyArraySizeException() {
       super("Размеры массивов не совпадают");
       printStackTrace();
    }
}
