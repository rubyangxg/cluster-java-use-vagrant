package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by yangxg on 16/2/1.
 */
public class HadoopTest {

    private static final String hdfsUrl = "hdfs://node1:9000";

    private FileSystem fs;

    @Before
    public void getConf() throws URISyntaxException, IOException {
        Configuration conf = new Configuration();
        URI uri = new URI(hdfsUrl);
        fs = FileSystem.get(uri, conf);
    }

    @After
    public void closeClient() throws IOException {
        fs.close();
    }

    @Test
    public void uploadFromLocal() throws URISyntaxException, IOException {
        String file = getClass().getClassLoader().getResource("apache.license.txt").getFile();
        Path localFile = new Path(file);
        Path destFile = new Path("/apache.license.txt");
        fs.copyFromLocalFile(false, true, localFile, destFile);
    }

    @Test
    public void del() throws URISyntaxException, IOException {
        Path destFile = new Path("/apache.license.txt");
        fs.delete(destFile, false);
    }

    @Test
    public void ls() throws URISyntaxException, IOException {
        Path dir = new Path("/");

        FileStatus[] list = fs.listStatus(dir);
        System.out.println("ls: /");
        System.out.println("==========================================================");
        for (FileStatus f : list) {
            System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDirectory(), f.getLen());
        }
        System.out.println("==========================================================");
    }

}
