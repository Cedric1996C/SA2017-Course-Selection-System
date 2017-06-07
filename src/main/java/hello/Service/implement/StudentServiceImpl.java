package hello.Service.implement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hello.Entity.Student;
import hello.Repository.StudentRepository;
import hello.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by njucong on 2017/5/27.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        PageHelper.startPage(1, 10);
        return studentRepository.selectAllStudents();
    }

    public PageInfo<Student> selectStudents(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> student = studentRepository.selectAllStudents();
        PageInfo<Student> pageInfo = new PageInfo<>(student);
        return pageInfo;
    }


    public Student getById(Long Id) {
        return studentRepository.selectStudentById(Id);
    }

    public void deleteById(Long Id) {
        studentRepository.deleteStudentById(Id);
    }

    public void save(Student student) {
        studentRepository.insertStudent(student);
    }

    public void updateStudent(Student student){
     //   System.out.println(student);
        studentRepository.updateStudent(student);
    }
}
