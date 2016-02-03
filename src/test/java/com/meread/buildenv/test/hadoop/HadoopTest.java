package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
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

//    private DFSClient client;

    private FileSystem fileSystem;

    @Before
    public void getConf() throws URISyntaxException, IOException {
        Configuration conf = new Configuration();
        URI uri = new URI(hdfsUrl);
//        client = new DFSClient(uri, conf);
        fileSystem = FileSystem.get(uri, conf);
    }

    @After
    public void closeClient() throws IOException {
        fileSystem.close();
    }

    @Test
    public void uploadFromLocal() throws URISyntaxException, IOException {
        Path localFile = new Path("/Users/yangxg/cluster-java-use-vagrant/src/test/resources/apache.license.txt");
        Path destFile = new Path("/apache.license.txt");
        fileSystem.copyFromLocalFile(false, true, localFile, destFile);
    }
}
