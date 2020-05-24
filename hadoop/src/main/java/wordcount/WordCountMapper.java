package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author: mirror_huang
 * @qq: 1755496180
 * @description:
 * @create: 2020-05-07 01:13
 **/
public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

    //自定义计数器
    private enum Counters {LINES, WORDS}

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter(Counters.LINES).increment(1);
        final String line = value.toString();
        String[] words = line.split("\\s+");
        for (String word : words) {
            context.getCounter(Counters.WORDS).increment(1);
            System.out.println(word);
            context.write(new Text(word), new IntWritable(1));
        }
    }
}
