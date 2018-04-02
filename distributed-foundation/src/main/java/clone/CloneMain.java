package clone;

import java.io.IOException;

import entity.Student;
import entity.Teacher;

public class CloneMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Teacher teacher = new Teacher();
		teacher.setName("mic");

		Student student = new Student();
		student.setName("沐风");
		student.setAge(35);
		student.setTeacher(teacher);
		System.out.println(student);

		Student cloneStu = (Student) student.deepClone(); // 克隆一个对象
		cloneStu.getTeacher().setName("james");
		System.out.println(cloneStu);

	}

}
