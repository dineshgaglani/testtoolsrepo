/**
 * Created by dgaglani on 4/6/14.
 */
public class BinaryStringGenerator {

    int[] binaryNos;
    int start = 0;

    BinaryStringGenerator(int size) {
        binaryNos = new int[size];
    }

    public void generateBinaryString(int start) {
        if(start == binaryNos.length) {
            Utils.displayArray(binaryNos);
        }
        else {
            binaryNos[start] = 0;
            generateBinaryString(start + 1);
            binaryNos[start]=1;
            generateBinaryString(start + 1);
        }
    }

    public static void main(String args[]) {
        BinaryStringGenerator binary = new BinaryStringGenerator(4);
        binary.generateBinaryString(binary.start);
    }
}
