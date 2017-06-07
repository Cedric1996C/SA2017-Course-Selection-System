package hello.Batch;

import hello.Entity.Student;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by njucong on 2017/5/29.
 */
@Service
@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    @Bean
    public ItemReader<Student> reader() {
        PoiItemReader<Student> reader = new PoiItemReader<>();
        reader.setLinesToSkip(4);
        reader.setResource(new ClassPathResource("students.xlsx"));
        reader.setRowMapper(studentRowMapper());
        return reader;
    }

    private RowMapper<Student> studentRowMapper(){
        return new StudentExcelRowMapper();
    }


    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }


    @Bean
    public MyBatisBatchItemWriter<Student> writer() {
        MyBatisBatchItemWriter<Student> writer = new MyBatisBatchItemWriter<Student>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("hello.Repository.Mapper.StudentsMapper.insertStudent");
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        List<ItemWriter> delegates = new ArrayList<>();
        delegates.add(writer);
        compositeItemWriter.setDelegates(delegates);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<Student,Student>chunk(4)//3次处理提交一次事务
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }
    // end::jobstep[]
}