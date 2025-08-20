package aggregate;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private int studentId;
    private String pwd;
    private String name;
    private int age;
    private Gender gender;
    private LocalDate birthday;

    public Student() {
    }

    public Student(String pwd, String name, int age, LocalDate birthday) {
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Student(int studentId, String pwd, String name, int age, Gender gender, LocalDate birthday) {
        this.studentId = studentId;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
