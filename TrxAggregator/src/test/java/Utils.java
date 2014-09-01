import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/28/14.
 */
public class Utils {

    public static List<File> getTrxFilesFromFolder(String folderPath) {
        File folder = new File(folderPath);
        List<File> files = new ArrayList<File>();
        for (final File fileEntry : folder.listFiles()) {
            files.add(fileEntry);
        }
        return files;
    }

    public static List<File> getTrxFilesFromPaths(List<String> filePaths) {
        ArrayList<File> files = new ArrayList<File>();
        for (String filePath : filePaths) {
            files.add(new File(filePath));
        }
        return files;
    }
}
