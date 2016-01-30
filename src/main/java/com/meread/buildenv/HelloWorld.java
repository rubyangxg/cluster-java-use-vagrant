package com.meread.buildenv;

import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * Created by yangxg on 16/1/27.
 */
public class HelloWorld {

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(SystemUtils.IS_OS_WINDOWS);
        String result = executeCommand("vagrant -v");
        System.out.println(result);
    }

}
