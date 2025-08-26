/* 더미 데이터를 파일로 저장 후에 다시 실제 데이터 배열에 저장한 이유는
 * 실무에서 사용되는 DB에서 정보를 가져오는 방식과 유사하게 표현하기 위해서이다.
* */
package repository;

import aggregate.Gender;
import aggregate.Student;
import stream.MyObjectOutput;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentRepository {
    private final ArrayList<Student> studentsList = new ArrayList<>();      // 실제 데이터가 저장되는 리스트
    private final File file = new File("src/main/java/db/test.dat");         // 데이터를 저장할 파일 루트 지정

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

    public int registerStudent(Student signup) {
        MyObjectOutput moo = null;
        int result = 0;
        try{
            /* 추가한 데이터를 test.dat 파일에 쓰고 flush() 메서드를 사용해서 파일에 바로 입력하고 clear로 추가되지 않은 데이터들을 삭제하고
            *  loadStudents()함수로 파일에 추가한 데이터까지 저장된 리스트를 studentsList에 넣는다. */
            moo = new MyObjectOutput(new BufferedOutputStream(new FileOutputStream(file, true)));
            moo.writeObject(signup);
            moo.flush();

            studentsList.clear(); // 기존의 정보 지우고 새로운 데이터를 받아오는 부분
            loadStudents();

            result = 1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(moo != null) moo.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
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

    public Student FindStudentID(int chooseId) {
        for(Student s: studentsList){
            if(s.getStudentId() == chooseId){
                return s;
            }
        }
        return null;
    }

    // 수정했던 학생 정보가 담겨 있는 st 배열 정보를 원본 저장소인 studentsList에 담는 메소드
    public int modityStudent(Student st) {
        for(int i = 0; i<studentsList.size(); i++){
            if(studentsList.get(i).getStudentId() == st.getStudentId()){
                studentsList.set(i, st);
                saveStudents(studentsList);
                return 1;
            }
        }
        return 0;
    }


    public int removeStudentID(int chooseId) {
        int result = 0;

        for(Student s: studentsList){
            if(s.getStudentId() == chooseId){
                studentsList.remove(s);
                result = 1;
                break;
            }
        }
        return result;
    }
}
