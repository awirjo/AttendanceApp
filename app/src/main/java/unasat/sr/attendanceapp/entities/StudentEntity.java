package unasat.sr.attendanceapp.entities;

public class StudentEntity {
    private int student_id;
    private String student_firstname;
    private String student_lastname;
    private String student_emailaddress;

    public int getStudent_id() {
        return student_id;
    }

    public String getStudent_firstname() {
        return student_firstname;
    }

    public String getStudent_lastname() {
        return student_lastname;
    }

    public String getStudent_emailaddress() {
        return student_emailaddress;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setStudent_firstname(String student_firstname) {
        this.student_firstname = student_firstname;
    }

    public void setStudent_lastname(String student_lastname) {
        this.student_lastname = student_lastname;
    }

    public void setStudent_emailaddress(String student_emailaddress) {
        this.student_emailaddress = student_emailaddress;
    }
}
