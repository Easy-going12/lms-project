## 📂 프로젝트 구조
    lms_project
      ┃ src
      | ┣ main

lms_project/
┣ src/
┃ ┣ main/
┃ ┃ ┣ java/
┃ ┃ ┃ ┣ aggregate/ # 도메인 객체(VO/Entity) 패키지
┃ ┃ ┃ ┃ ┣ Gender.java # 성별 Enum 클래스
┃ ┃ ┃ ┃ ┗ Student.java # 학생 엔티티 클래스
┃ ┃ ┃ ┣ controller/ # 실행 및 컨트롤러 계층
┃ ┃ ┃ ┃ ┗ Run.java # main 메서드 실행 진입점
┃ ┃ ┃ ┣ repository/ # 데이터 저장소 계층
┃ ┃ ┃ ┃ ┗ StudentRepository.java # 파일을 통한 CRUD 구현
┃ ┃ ┃ ┣ service/ # 비즈니스 로직 계층
┃ ┃ ┃ ┃ ┗ StudentService.java # 학생 서비스 로직
┃ ┃ ┃ ┗ db/ # DB 대용 파일 저장 폴더
┃ ┃ ┃ ┗ test.txt
┃ ┃ ┗ resources/ # 리소스 파일 관리
┃ ┗ test/ # JUnit 테스트 패키지
┣ build.gradle # Gradle 빌드 스크립트
┗ .gitignore # Git 무시 규칙
