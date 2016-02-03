package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
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
    private Configuration conf;

    @Before
    public void getConf() throws URISyntaxException, IOException {
        conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUrl);
        URI uri = new URI(hdfsUrl);
        fs = FileSystem.get(uri, conf);
    }

    @After
    public void closeClient() throws IOException {
        fs.close();
    }

    @Test
    public void del() throws URISyntaxException, IOException {
        Path destFile = new Path("/test");
        fs.delete(destFile, true);
    }

    @Test
    public void mkdir() throws URISyntaxException, IOException {
        Path destFile = new Path("/test");
        fs.mkdirs(destFile);
    }

    @Test
    public void uploadFromLocal() throws URISyntaxException, IOException {
        String file = getClass().getClassLoader().getResource("apache.license.txt").getFile();
        Path localFile = new Path(file);
        Path destFile = new Path("/test/apache.license.txt");
        fs.copyFromLocalFile(false, true, localFile, destFile);
    }

    @Test
    public void ls() throws URISyntaxException, IOException {
        Path dir = new Path("/test");

        FileStatus[] list = fs.listStatus(dir);
        System.out.println("ls: /");
        System.out.println("==========================================================");
        for (FileStatus f : list) {
            System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDirectory(), f.getLen());
        }
        System.out.println("==========================================================");
    }

    @Test
    public void wordCount() throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(conf, "wordcount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("/test/apache.license.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/test/output01"));
        job.waitForCompletion(true);
    }

}
