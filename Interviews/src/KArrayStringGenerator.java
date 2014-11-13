/**
 * Created by dgaglani on 4/6/14.
 */
public class KArrayStringGenerator {

    int[] kArray;
    int[] nArray;

    KArrayStringGenerator(int[] kArray, int nArraySize) {
        this.kArray = kArray;
        nArray = new int[nArraySize];
    }

    public void generateKArray(int kArrayIndex, int nArrayIndex) {

    }

    public  static void main(String args[]) {
        int[] kArray = new int[] {1};
        KArrayStringGenerator kArrayGen = new KArrayStringGenerator(kArray, 2);
        kArrayGen.generateKArray(kArray.length, 2);
    }

}
