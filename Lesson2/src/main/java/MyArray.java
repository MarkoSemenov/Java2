
public class MyArray  {

    private static final int SIZE = 4;

    public MyArray() {

    }

    public static int workWithArray(String [][] array){
        int result = 0;

        for (int i=0; i <= array.length-1; i++) {

            if ((array[i].length != SIZE) || (array.length != SIZE)) {
                    throw new MyArraySizeException();
            }

            for (int j = 0; j <= array.length-1; j++) {

                try {
                    result += Integer.parseInt(array[i][j]);

                } catch (NumberFormatException  e) {
                    e.printStackTrace();
                    throw new MyArrayDataException(i, j);

                }

            }
        }

        return result;
    }

}






