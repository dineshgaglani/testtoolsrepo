package north;

import java.util.*;

/**
 * Created by Dinesh on 9/30/2019.
 */
public class NorthQuestion {

    /*
     * Complete the 'extractor' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY input_content as parameter.
     */

    public static String extractor(List<String> input_content) {
        // Preprocess:
        // convert list items to a map of maps
        // Map key is anything before the -----
        // Map values is another map whose key is everything before the ':' and value is everything after it
        HashMap<String, HashMap<String, String>> fullMap = getMap(input_content.subList(1, input_content.size()));
        String[] valueToGet = input_content.get(0).split("\\.");
        String component = valueToGet[0];
        String item = valueToGet[1];

        String returnString = fullMap.get(component).get(item);
        if (returnString.contains(" ")) {
            String[] returnStringArr = returnString.split(" ");
            return getNumericalValueInReturnString(returnStringArr);
        }

        return returnString;
    }

    public static HashMap<String, HashMap<String, String>> getMap(List<String> input_content) {
        HashMap<String, HashMap<String, String>> ret = new HashMap<>();
        String prevString = "";
        for (int i = 0; i < input_content.size(); i++) {
            if(input_content.get(i).startsWith("--")) {
                String componentKey = input_content.get(i - 1);
                Integer nextDashIndex = getNextDashIndex(input_content.subList(i + 1, input_content.size()));
                if (nextDashIndex > 0) {
                    nextDashIndex = nextDashIndex + (i + 1);
                    HashMap<String, String> itemsMap = convertToItemMap(input_content.subList(i + 1, nextDashIndex - 1));

                    ret.put(componentKey, itemsMap);
                    i = nextDashIndex - 1;
                }
                else {
                    //Reached end of file , this is the last component
                    HashMap<String, String> itemsMap = convertToItemMap(input_content.subList(i + 1, input_content.size()));

                    ret.put(componentKey, itemsMap);
                }

            }
        }
        return ret;
    }

    public static String getNumericalValueInReturnString(String[] stringWithValue) {
        String ret = "";

        for (String s : stringWithValue) {
            try {
                Double.parseDouble(s);
                ret = s;
            }
            catch (NumberFormatException e) {
                continue;
            }
        }

        return ret;
    }

    public static HashMap<String, String> convertToItemMap(List<String> items) {
        HashMap<String, String> itemMap = new HashMap<String, String>();
        for (String item : items) {
            System.out.println(item);
            String[] splitItem = item.split(":");
            itemMap.put(splitItem[0].trim(), splitItem[1].trim());
        }
        return itemMap;
    }

    public static Integer getNextDashIndex(List<String> items) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).startsWith("--"))
                return i;
        }

        //Something went wrong
        return -1;
    }

    public static void main (String args[]) {
        String[] string = { "Motion Detector.delay", "Sensor", "-----", "delay:3000 ms", "input: bad", "output: good",
                "Motion Detector", "--------", "direction: 94", "delay: 35 seconds", "FizzBuzzer", "-----", "status: fizzing", "delay: 42 s" };
        System.out.println(extractor(Arrays.asList(string)));
    }
}
