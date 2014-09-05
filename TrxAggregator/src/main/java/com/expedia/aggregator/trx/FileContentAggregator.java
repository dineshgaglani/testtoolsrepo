package com.expedia.aggregator.trx;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by dgaglani on 9/5/14.
 */
public class FileContentAggregator extends ParallelAggregator<File> {

    @Override
    public File performMerge(File elementToMerge1, File elementToMerge2) {
        File newFile = new File(elementToMerge1.getPath().substring(0, elementToMerge1.getPath().lastIndexOf(System.getProperty("file.separator")) + 1) + elementToMerge1.getName() + elementToMerge2.getName());
        try {
            PrintWriter out = new PrintWriter(newFile);
            List<String> file1Contents = Files.readAllLines(elementToMerge1.toPath(), Charset.defaultCharset());
            for(String line : file1Contents) {
                out.println(line);
            }
            List<String> file2Contents = Files.readAllLines(elementToMerge2.toPath(), Charset.defaultCharset());
            for(String line : file2Contents) {
                out.println(line);
            }
            return newFile;
        } catch (Exception e) {

        }
        return null;
    }

    public void aggregateFilesFromFileList(List<String> testResultFiles, String mergedFileLocation) throws Exception{
        List<File> files = getFilesFromPaths(testResultFiles);
        File mergedFile = mergeElements(files);
        System.out.print("Finished file " + mergedFile.getAbsolutePath());
    }
}
