package hello.Entity;

import hello.Page.PageHelperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * Created by njucong on 2017/5/25.
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student extends PageHelperEntity {

    private Long  id;
    private String studentname;
    private String department;
    private String grade;

    private Integer usual_grade;
    private Integer design_grade;
    private Integer exam_grade;

    public Student( Long id, String name, String department, String grade , Integer usual_grade,Integer design_grade,Integer exam_grade){
        this.studentname = name;
        this.id  = id;
        this.department = department;
        this.grade = grade;

        this.usual_grade = usual_grade!=null? usual_grade:0;
        this.design_grade = design_grade!=null? design_grade:0;
        this.exam_grade = exam_grade!=null? usual_grade:0;
    }

}

