package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by yangxg on 16/2/1.
 */
public class WriteHDFS {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Configuration configuration = new Configuration();
        FileSystem hdfs = FileSystem.get(URI.create("hdfs://11.11.11.101:9000"), configuration);
        String licenseFilePath = WriteHDFS.class.getClassLoader().getResource("apache.license.txt").getFile();
        Path src = new Path(licenseFilePath);
        if (hdfs.exists(src)) {
            hdfs.delete(src, true);
        }
        Path dest = new Path("/");
        hdfs.copyFromLocalFile(src, dest);
        System.out.println("Upload to" + configuration.get("fs.default.name"));
        FileStatus files[] = hdfs.listStatus(dest);
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }
}
