/* 더미 데이터를 파일로 저장 후에 다시 실제 데이터 배열에 저장한 이유는
 * 실무에서 사용되는 DB에서 정보를 가져오는 방식과 유사하게 표현하기 위해서이다.
* */
package repository;

import aggregate.Gender;
import aggregate.Student;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentRepository {
    private final ArrayList<Student> studentsList = new ArrayList<>();      // 실제 데이터가 저장되는 리스트
    private final File file = new File("src/main/java/db/test.txt");         // 데이터를 저장할 파일 루트 지정

    /* 처음 시작할 때, 생성되는 데이터 입력 */
    public StudentRepository() {
        if(!file.exists()) {
            ArrayList<Student> defaultStudentsList = new ArrayList<>();     // 더미 데이터를 저장할 리스트 선언

            /* 더미 데이터 생성 */
            defaultStudentsList.add(new Student(201905006, "pass01",
                    "이지윤", 26,Gender.Male,LocalDate.of(2000,9,15)));
            defaultStudentsList.add(new Student(201905007, "pass02",
                    "삼지윤", 27,Gender.Female,LocalDate.of(2000,9,16)));
            defaultStudentsList.add(new Student(201905008, "pass03",
                    "사지윤", 24,Gender.Male,LocalDate.of(2000,9,11)));
            defaultStudentsList.add(new Student(201905009, "pass04",
                    "오지윤", 21,Gender.Male,LocalDate.of(2000,8,15)));
            defaultStudentsList.add(new Student(201905032, "pass055",
                    "이현수", 26,Gender.Male,LocalDate.of(2000,6,20)));

            /* 더미 데이터를 저장한 defaultStudentsList 값을 file에 저장 */
            saveStudents(defaultStudentsList);
        }
        /* 더미 데이터가 저장된 defaultStudentsList 값을 studentsList으로 옮기는 과정 */
        loadStudents();
    }

    private void saveStudents(ArrayList<Student> defaultStudentsList) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

            for(Student student : defaultStudentsList){
                oos.writeObject(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                if(oos != null)oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadStudents() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            while(true){
                studentsList.add((Student)ois.readObject());    // readObject의 반환형은 Object이고 studentsList는
            }                                                   // Student 타입이기 때문에 Student 타입으로 다운캐스팅하였다.
        } catch(EOFException e){                        // 파일이 처음부터 끝까지 다 읽었다는 의미이다.
            System.out.println("회원 정보 읽어오기 완료");
        } catch(FileNotFoundException e) {              // 파일이 없을 때 생기는 오류
            throw new RuntimeException(e);
        } catch (IOException e) {                       // 입출력 시에 발생하는 오류
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {            // 클래스를 찾지 못했을 때 발견하는 오류
            throw new RuntimeException(e);              // FileInputStream을 커버하기 위해 존재한다.
        }
    }

    public ArrayList<Student> AllFindStudent() {
        return studentsList;
    }

    public Student FindStudent(int studentId) {
        for(Student s:studentsList){
            if(s.getStudentId() == studentId){
                return s;
            }
        }
        return null;
    }
}
