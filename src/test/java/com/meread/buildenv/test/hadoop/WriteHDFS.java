package com.meread.buildenv.test.hadoop;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxg on 16/2/1.
 */
public class WriteHDFS {

    @Test
    public void hadoopTest() throws URISyntaxException, IOException {
        HDFSUpload upload = new HDFSUpload();
        upload.setSourceFilename("/Users/yangxg/cluster-java-use-vagrant/src/test/resources/apache.license.txt");
        upload.setDestinationFilename("/");
        upload.uploadFile();
    }
}
