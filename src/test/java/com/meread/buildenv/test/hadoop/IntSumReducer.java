package com.meread.buildenv.test.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable result = new IntWritable();

    protected void reduce(Text key, Iterable<IntWritable> values, Mapper.Context context) throws IOException, InterruptedException {
        //输入参数key为单个单词;
        //输入参数Iterable<IntWritable> values为各个Mapper上对应单词的计数值所组成的列表。
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        System.out.println(key.toString() + "," + sum);
        result.set(sum);
        context.write(key, result);
    }
}