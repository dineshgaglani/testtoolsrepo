package com.expedia.eapid;

import com.sun.media.jfxmedia.track.Track;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by dgaglani on 7/18/14.
 */
public class EapidsForAA {

    public static void main(String args[]) throws Exception {
        File allEapidsFile = new File("/Users/dgaglani/Desktop/tasks/allEapidsForAA-Airline/allEapidsForAAairlinetext.rtf");
        File toMatchFile = new File("/Users/dgaglani/Desktop/tasks/allEapidsForAA-Airline/toMatchFile.txt");
        List<String> allEapidsList = getEapidsFromAllEapidsFile(allEapidsFile);
        List<String> toMatchEapids = getEapidsFromToMatchFile(toMatchFile);
        toMatchEapids.removeAll(allEapidsList);
        for(String missingEapid : toMatchEapids) {
            System.out.println(missingEapid);
        }
    }

    public static List<String> getEapidsFromAllEapidsFile(File allEapidsFile) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(allEapidsFile.getAbsolutePath()), StandardCharsets.UTF_8);
        List<String> lines2 = new ArrayList<String>();
        for(String line : lines) {
            if(line.length() > 0) {
                line = line.substring(0, line.length() - 1);
                lines2.add(line);
            }
        }
        return lines2;
    }

    public static List<String> getEapidsFromToMatchFile(File toMatchFile) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(toMatchFile.getAbsolutePath()), StandardCharsets.UTF_8);
        List<String> lines2 = new ArrayList<String>();
        for(String line : lines) {
            if(!line.trim().equals("") ) {
               lines2.add(line);
            }
        }
        return lines2;
    }


}
