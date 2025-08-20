package controller;

import service.StudentService;

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
            System.out.print("메뉴를 선택해주세요: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: break;
                case 2: break;
                case 3: break;
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
}
