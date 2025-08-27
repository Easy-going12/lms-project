package service;

import aggregate.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void FindStudent() {
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("조회할 학번을 입력해 주세요.(9번 입력시 뒤로 갑니다): ");
            int chooseId = sc.nextInt();

            if(chooseId == 9){
                System.out.println("뒤로 갑니다.");
                System.out.println();
                break;
            }

            Student findstudent = sr.FindStudent(chooseId);

            if(findstudent != null){
                System.out.println(chooseId + "학번에 대한 학생 정보는 " + findstudent);
            } else{
                System.out.println(chooseId + "학번은 잘못 되었습니다.");
            }
            System.out.println();
        }

    }

    public void AddStudent(Student signup) {
        int result = sr.registerStudent(signup);     // 회원을 기존 리스트에 넣는 부분 성공했을 경우 1을 반환
                                                                    // 실패하면 0 반환
        if(result == 1) System.out.println("학생 정보가 등록되었습니다.");
        else System.out.println("학생 정보 등록에 실패했습니다. ");
        System.out.println();
    }


    public Student FindStudentID(int chooseId) {
        Student st = sr.FindStudentID(chooseId);
        Student copyMember = null;

        // repository 있는 데이터가 손상되면 안되므로 service에서 copyMember에 기존 데이터를 복사 후, 복사한 생성자에서 값을 변경
        if(st != null){
            copyMember = new  Student();
            copyMember.setStudentId(st.getStudentId());     // 학번을 추가하지 않으면 수정할 때 어느 학번에 대한 정보를 수정하는지
                                                            // 알수 없기 때문에 학번 내용도 넣어야 한다.
            copyMember.setPwd(st.getPwd());
            copyMember.setName(st.getName());
            copyMember.setAge(st.getAge());
            copyMember.setBirthday(st.getBirthday());
            copyMember.setGender(st.getGender());
        }else{
            System.out.println("그런 학생은 없습니다.");
        }
        return copyMember;
    }

    public void modityStudent(Student st) {
        int result = sr.modityStudent(st);

        if(result >0){
            System.out.println("학생 정보가 수정되었습니다.");
        }else{
            System.out.println("학생 정보 수정이 이루어지지 않았습니다.");
        }
        System.out.println();
    }

    public void removeStudentID(int chooseId) {
        int result = sr.removeStudentID(chooseId);

        if(result == 1) {
            System.out.println("회원 정보가 삭제되었습니다.");
        }
        else{
            System.out.println("회원 정보 삭제에 실패하였습니다.");
        }
        System.out.println();
    }
}
