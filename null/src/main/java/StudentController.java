package src//main//java;
import cn.intellif.create.po.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.intellif.create.service.*;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("student")
public class StudentController{

	@Autowired
	private IStudentService  studentService;

	@RequestMapping("test")
	public Object test(){
		return "success";
	}
}