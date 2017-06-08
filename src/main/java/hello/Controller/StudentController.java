package hello.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hello.Entity.Student;
import hello.Repository.Mapper.StudentsMapper;
import hello.Repository.StudentRepository;
import hello.Service.StudentService;
import hello.Service.implement.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by njucong on 2017/5/25.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

   /* public Student getStudent() {
        return new Student( "141220045", "cong", "computer Science", "2014");
    }
    */
   @Autowired
   StudentServiceImpl studentService;

   @RequestMapping
   public PageInfo<Student> getAll() {
       List<Student> studentList = studentService.getAll();
       return new PageInfo<Student>(studentList);
   }

    @RequestMapping(value = "/pageInfo", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> getPage(Integer pageNum, Integer pageSize) {
        PageInfo<Student> list = studentService.selectStudents(pageNum,pageSize);
        return  list;
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Student> addStudent(Long id, String name, String department, String grade , Integer usual_grade,Integer design_grade,Integer exam_grade){
        Student student = new Student(id,name,department,grade,usual_grade,design_grade,exam_grade);
        studentService.save(student);
        List<Student> studentList = studentService.getAll();
        return new PageInfo<Student>(studentList);
    }

    @RequestMapping(value =  "/delete/{id}")
    public PageInfo<Student> deleteStudent (@PathVariable Long id){
        studentService.deleteById(id);
        List<Student> studentList = studentService.getAll();
        return new PageInfo<Student>(studentList);
    }

    @RequestMapping( value = "/update",method = RequestMethod.POST)
    public PageInfo<Student> updateStudent (Long id, String name, String department, String grade , Integer usual_grade,Integer design_grade,Integer exam_grade){
        Student student = new Student(id,name,department,grade,usual_grade,design_grade,exam_grade);
        System.out.print(student);
        studentService.updateStudent(student);
        List<Student> studentList = studentService.getAll();
        return new PageInfo<Student>(studentList);
    }
}
