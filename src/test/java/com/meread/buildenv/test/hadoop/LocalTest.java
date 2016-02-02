package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

import java.security.PrivilegedExceptionAction;

/**
 * Created by yangxg on 16/2/2.
 */
public class LocalTest {

    @Test
    public void hadoopTest() {
        UserGroupInformation ugi
                = UserGroupInformation.createRemoteUser("root");
        try {
            ugi.doAs(new PrivilegedExceptionAction<Void>() {

                public Void run() throws Exception {

                    Configuration conf = new Configuration();
                    //fs.default.name should match the corresponding value
                    // in your core-site.xml in hadoop cluster
                    conf.set("fs.default.name", "hdfs://11.11.11.101:9000");
                    conf.set("hadoop.job.ugi", "root");
                    // in case you are running mapreduce job , need to set
                    // 'mapred.job.tracker' as you did
                    conf.set("mapred.job.tracker", "11.11.11.101:port");
                    // do your code here.
                    FileSystem hdfs = FileSystem.get(conf);
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
