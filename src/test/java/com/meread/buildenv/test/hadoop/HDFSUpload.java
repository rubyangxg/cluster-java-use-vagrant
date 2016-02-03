package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSClient;

import java.io.*;
import java.net.URISyntaxException;

public class HDFSUpload {

    private static String hdfsUrl = "hdfs://11.11.11.101:9000";

    private String sourceFilename;
    private String destinationFilename;

    public String getSourceFilename() {
        return sourceFilename;
    }

    public void setSourceFilename(String sourceFilename) {
        this.sourceFilename = sourceFilename;
    }

    public String getDestinationFilename() {
        return destinationFilename;
    }

    public void setDestinationFilename(String destinationFilename) {
        this.destinationFilename = destinationFilename;
    }

    public void uploadFile()
            throws IOException, URISyntaxException {

    }
}