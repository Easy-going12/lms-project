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
                case 2: ss.FindStudent(); break;
                case 3: ss.AddStudent(signup()); break;
                case 4:
                    // 해당 학번에 대한 복사본 정보를 가져온다.
                    Student st = ss.FindStudentID(chooseId());

                    // reform 메서드가 Object 타입으로 선언되었지만 필요한 필드만 사용하기 위해서 Student 타입으로 다운캐스팅을 하였다
                    ss.modityStudent(reform(st));

                    break;
                case 5:
                    ss.removeStudentID(chooseId());
                    break;
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
        System.out.print("조회할 학번을 입력해 주세요(9번을 누르면 뒤로 갑니다): ");
        return sc.nextInt();
    }

    private static Student reform(Student st) {

        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while(flag){
            System.out.println("=====수정할 정보를 입력해 주세요.=====");
            System.out.println("1. 회원 비밀번호 ");
            System.out.println("2. 회원 이름 ");
            System.out.println("3. 회원 나이 ");
            System.out.println("4. 회원 생일 ");
            System.out.println("9. 메인 메뉴로 돌아가기");
            System.out.print("수정 할 번호를 입력하세요: ");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("수정할 회원의 비밀번호를 입력해주세요: ");
                    st.setPwd(sc.nextLine());
                    break;
                case 2:
                    System.out.print("수정할 회원의 이름을 입력해주세요: ");
                    st.setName(sc.nextLine());
                    break;
                case 3:
                    System.out.print("수정할 회원의 나이를 입력해주세요: ");
                    st.setAge(sc.nextInt());
                    break;
                case 4:
                    System.out.print("수정할 회원의 생일(YYYY-MM-DD 형식)을 입력해주세요: ");
                    st.setBirthday(LocalDate.parse(sc.next()));
                    break;
                case 9:
                    System.out.println("메인 메뉴로 돌아갑니다");
                    flag = false;
                    break;
                default:
                    System.out.println("숫자를 잘못 입력했습니다.");
                    break;
            }
        }
        return st;

    }
}
