package hello.Repository;

import hello.Entity.Student;
import java.util.List;

/**
 * Created by njucong on 2017/5/27.
 */
public interface StudentRepository {

    Student selectStudentById(Long Id);

    Student selectStudentByName(String name);

    List<Student> selectAllStudents();

    Integer insertStudent(Student student);

    Integer updateStudent(Student student);

    Integer deleteStudentById(Long id);
}
