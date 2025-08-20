package service;

import aggregate.Student;
import repository.StudentRepository;

import java.util.ArrayList;

public class StudentService {
    private static final StudentRepository sr = new StudentRepository();

    public void AllFindStudent() {
        ArrayList<Student> studentsList = sr.AllFindStudent();

        if(!studentsList.isEmpty()){
            for(int i =0; i<studentsList.size();i++){
                System.out.println((i+1) + "번째 학생 정보는: " + studentsList.get(i));
            }
        }else{
            System.out.println("학생에 대한 정보가 존재하지 않습니다.");
        }
        System.out.println();
    }

    public void FindStudent(int studentId) {
        Student findstudent = sr.FindStudent(studentId);

        if(findstudent != null){
            System.out.println(studentId + "학번에 대한 학생 정보는 " + findstudent);
        } else{
            System.out.println(studentId + "학번은 잘못 되었습니다.");
        }
        System.out.println();
    }
}
