package controller;

import aggregate.Gender;
import aggregate.Student;
import service.StudentService;

import java.time.LocalDate;
import java.util.Scanner;

public class Run {

    private static final StudentService ss = new StudentService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("===== 학사 관리 시스템 =====");
            System.out.println("1. 모든 학생 정보 조회");
            System.out.println("2. 학번으로 학생 정보 조회");
            System.out.println("3. 학생 정보 추가");
            System.out.println("4. 학생 정보 수정");
            System.out.println("5. 학생 정보 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.println();
            System.out.print("메뉴를 선택해주세요: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: ss.AllFindStudent(); break;
                case 2: ss.FindStudent(chooseId()); break;
                case 3: ss.AddStudent(signup()); break;
                case 4: break;
                case 5: break;
                case 9:
                    System.out.println("프로그램을 종료합니다. bye bye~~~~~~");
                    return;
                default:
                    System.out.println("잘못 입력하였습니다.");
                    System.out.println();
            }
        }
    }

    private static Student signup() {
        Student student = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("학번을 입력하세요: ");
        int studentId = sc.nextInt();

        System.out.print("패스워드를 입력하세요: ");
        String pwd  = sc.next();

        System.out.print("이름을 입력하세요: ");
        String name = sc.next();

        System.out.print("나이을 입력하세요: ");
        int age = sc.nextInt();

        System.out.print("(남/여) 중에 고르세요: ");
        Gender gender = null;
        switch (sc.next()) {
            case "남":
                gender = Gender.Male;
                break;
            case "여":
                gender = Gender.Female;
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
        }

        System.out.print("생일(YYYY-MM-DD 형식)을 입력해주세요: ");
        LocalDate brithday = LocalDate.parse(sc.next());

        student = new Student(studentId, pwd, name, age, gender, brithday);

        return student;

    }

    private static int chooseId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("학번을 입력해 주세요: ");
        return sc.nextInt();
    }
}
