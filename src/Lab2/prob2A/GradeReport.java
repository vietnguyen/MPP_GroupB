package Lab2.prob2A;

public class GradeReport {
  private Student student;
  private String grade;
  GradeReport(Student student) {
    this.student = student;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Grade of Student: " + student.getName() + " is " + (grade == null ? "Not Available" : grade);
  }
}
