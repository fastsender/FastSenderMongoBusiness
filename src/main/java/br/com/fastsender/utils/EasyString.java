package br.com.fastsender.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author lucas
 */
public class EasyString {

    public String fileTxtToString(String dir) {
        try {
            StringBuilder sb = new StringBuilder();
            
            FileReader ler = new FileReader(dir);
            BufferedReader reader = new BufferedReader(ler);
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
