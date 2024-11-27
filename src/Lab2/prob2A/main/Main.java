package Lab2.prob2A.main;

import Lab2.prob2A.Student;

public class Main {
  public static void main(String[] args){
    Student studentA = new Student("John M");
    studentA.getGradeReport().setGrade("A");
    System.out.println(studentA.getGradeReport());
    Student studentB = new Student("John N");
    studentB.getGradeReport().setGrade("B");
    System.out.println(studentB.getGradeReport());
    
//    GradeReport gradeReport = new GradeReport(studentA);// compiler error
  }
}
