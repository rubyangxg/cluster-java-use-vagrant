package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

import java.net.URI;
import java.security.PrivilegedExceptionAction;

/**
 * Created by yangxg on 16/2/2.
 */
public class LocalTest {

    @Test
    public void hadoopTest() {
        UserGroupInformation ugi
                = UserGroupInformation.createRemoteUser("root");
        final String hdfsPath = "hdfs://node1:9000";
        try {
            ugi.doAs(new PrivilegedExceptionAction<Void>() {

                public Void run() throws Exception {

                    Configuration conf = new Configuration();
                    //fs.default.name should match the corresponding value
                    // in your core-site.xml in hadoop cluster
                    conf.set("fs.defaultFS", hdfsPath);
                    conf.set("hadoop.job.ugi", "hadoop");
                    // in case you are running mapreduce job , need to set
                    // 'mapred.job.tracker' as you did
                    conf.set("mapred.job.tracker", "node1:50030");
                    // do your code here.
                    FileSystem dfs = FileSystem.get(conf);
                    Path path = new Path("/");
                    FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
                    FileStatus[] list = fs.listStatus(path);
                    System.out.println("ls: /");
                    System.out.println("==========================================================");
                    for (FileStatus f : list) {
                        System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDir(), f.getLen());
                    }
                    System.out.println("==========================================================");
                    fs.close();
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
