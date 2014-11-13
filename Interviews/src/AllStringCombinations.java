/**
 * Created by dgaglani on 5/11/14.
 */
public class AllStringCombinations {

    public static void printCombinations(String str){
        printCombinations(str, 0, str.length()-1);
    }

    public static void printCombinations(String str,int startIndex,int endIndex){
        if(startIndex == endIndex)
            System.out.println(str);
        else {
            for(int i=startIndex;i<=endIndex;i++){
                String tmp=modifyString(str,i,startIndex);
                printCombinations(tmp,startIndex+1,endIndex);
               // modifyString(str,i,startIndex);
            }
        }
    }

    public static String modifyString(String str,int x,int y){

        // for swapping characters inside a string
        char arr[]=str.toCharArray();
        char t= arr[x];
        arr[x]=arr[y];
        arr[y]=t;

        String s= new String(arr);
        return s;
    }

    public static void main(String args[]) {
        printCombinations("abc");
    }


}
