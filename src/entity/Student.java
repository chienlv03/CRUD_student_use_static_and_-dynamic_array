package entity;

import java.time.LocalDate;

public class Student extends Person{
    private String studentCode;
    private String university;
    private int startYear;
    private double gpa;
    private AcademicAchievement academicAchievement;

    public Student(Long id, String studentCode, String name, LocalDate dob, String address,
                   double height, double weight, String university, int startCourse, double gpa) {
        super(id, name, dob, address, height, weight);
        this.studentCode = studentCode;
        this.university = university;
        this.startYear = startCourse;
        setGpa(gpa);
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public AcademicAchievement getAcademicAchievement() {
        return academicAchievement;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        this.academicAchievement = setAcademicAchievementByGpa(gpa);
    }

    public AcademicAchievement setAcademicAchievementByGpa(double gpa) {
        if (gpa < 3) return AcademicAchievement.POOR;
        else if (gpa < 5) return AcademicAchievement.WEEK;
        else if (gpa < 6.5) return AcademicAchievement.AVERAGE;
        else if (gpa < 7.5) return AcademicAchievement.GOOD;
        else if (gpa < 9) return AcademicAchievement.EXCELLENT;
        else return AcademicAchievement.OUTSTANDING;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + super.getId() +
                ", studentCode='" + studentCode + '\'' +
                ", name='" + super.getName() + '\'' +
                ", dob='" + super.getDob() + '\'' +
                ", address='" + super.getAddress() + '\'' +
                ", height=" + super.getHeight() +
                ", weight=" + super.getWeight() +
                ", university='" + university + '\'' +
                ", startYear=" + startYear +
                ", gpa=" + gpa +
                ", academicAchievement='" + academicAchievement.getValue() + '\'' +
                '}';
    }
}
