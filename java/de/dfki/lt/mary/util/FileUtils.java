package de.dfki.lt.mary.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;


/**
 * A collection of public static utility methods, doing
 * file operations.
 * @author schroed
 *
 */
public class FileUtils
{
    /**
     * List the basenames of all files in directory that end in suffix, without that suffix.
     * For example, if suffix is ".wav", return the names of all .wav files in the
     * directory, but without the .wav extension. The file names
     * are sorted in alphabetical order, according to java's string search.
     * @param directory
     * @param suffix
     * @return
     */
    public static String[] listBasenames(File directory, String suffix)
    {
        final String theSuffix = suffix;
        String[] filenames = directory.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(theSuffix);
            }
        });
        
        /* Sort the file names alphabetically */
        Arrays.sort(filenames);
        
        for (int i = 0; i < filenames.length; i++) {
            filenames[i] = filenames[i].substring(0, filenames[i].length()-suffix.length());
        }
        return filenames;
    }

    /**
     * Read a file into a string, using the given encoding, and return that string.
     * @param file
     * @param encoding
     * @return
     */
    public static String getFileAsString(File file, String encoding) throws IOException
    {
        StringWriter sw = new StringWriter();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        char[] buf = new char[8192];
        int n;
        while ((n=in.read(buf))> 0) {
            sw.write(buf, 0, n);
        }
        return sw.toString();
    }
}
