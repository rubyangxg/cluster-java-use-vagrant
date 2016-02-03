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
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUrl);
        conf.set("mapred.job.tracker", "node1");
        conf.set("mapreduce.jobtracker.http.address", "node1:50030");
        DFSClient client = new DFSClient(conf);
        OutputStream out = null;
        InputStream in = null;
        try {
            if (client.exists(destinationFilename)) {
                System.out.println("File already exists in hdfs: " + destinationFilename);
                return;
            }
            out = new BufferedOutputStream(client.create(destinationFilename, false));
            in = new BufferedInputStream(new FileInputStream(sourceFilename));
            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            client.close();
        }
    }
}