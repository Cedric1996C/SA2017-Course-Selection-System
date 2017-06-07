package hello.Service;

import com.github.pagehelper.PageInfo;
import hello.Entity.Student;

import java.util.List;

/**
 * Created by njucong on 2017/5/27.
 */
public interface StudentService {

    public List<Student> getAll();

    public Student getById(Long Id);

    public PageInfo<Student> selectStudents (Integer pageNum, Integer pageSize);

    public void deleteById(Long Id);

    public void save (Student student);

    public void updateStudent(Student student);
}
