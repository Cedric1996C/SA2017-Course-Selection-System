package hello.Batch;

import hello.Entity.Student;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by njucong on 2017/5/29.
 */
@Component
public class StudentItemProcessor implements ItemProcessor<Student,Student> {

    //private static final Logger log = LoggerFactory.getLogger(StudentItemProcessor.class);

    @Override
    public Student process(final Student student) throws Exception {
        final Long id = student.getId();
        final String studentname = student.getStudentname();
        final String department = student.getDepartment();
        final String grade = student.getGrade()==null? "0" : student.getGrade();

        final Integer usual_grade = student.getUsual_grade();
        final Integer design_grade = student.getDesign_grade();
        final Integer exam_grade = student.getExam_grade();

        final Student transformedStudent = new Student(id,studentname,department,grade,usual_grade,design_grade,exam_grade);

        return transformedStudent;
    }

}
