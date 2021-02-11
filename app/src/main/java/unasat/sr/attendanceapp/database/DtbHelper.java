package unasat.sr.attendanceapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import unasat.sr.attendanceapp.entities.StudentEntity;

public class DtbHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = " Attendance Database";
        private static final int DB_VERSION = 1;
        //Table names
        private static final String STUDENT_TABLE = "student_table";
        private static final String ATTENDANCE_TABLE ="attendance_table";
        private static final String ATTENDANCE_STATUS = "attendance_status";
        //Table colums names
        private static final String STUDENT_ID = "student_id";
        private static final String STUDENT_FIRSTNAME = "student_firstname";
        private static final String STUDENT_LASTNAME = "student_lastname";
        private static final String STUDENT_EMAILADDRESS = "student_emailaddress";

    public DtbHelper (Context context) {super(context, DB_NAME, null, DB_VERSION);}


        @Override
        public void onCreate(SQLiteDatabase db) {
        String studentQuery = "CREATE TABLE" + STUDENT_TABLE + "(" +
                STUDENT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                STUDENT_FIRSTNAME + "TEXT, " +
                STUDENT_LASTNAME + "TEXT, " +
                STUDENT_EMAILADDRESS + "TEXT" + ")";
        Log.d("studentQuery", studentQuery);
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
        //add student method
        public void addStudent(StudentEntity studentEntity){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO student_table (student_firstname, student_lastname, student_emailaddress) values ('"+
                studentEntity.getStudent_firstname()+" ', '"+
                studentEntity.getStudent_lastname()+" ', '"+
                studentEntity.getStudent_emailaddress()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
        //get all students
        public ArrayList<StudentEntity> getAllStudent(){
        ArrayList<StudentEntity> list = new ArrayList<StudentEntity>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentEntity.setStudent_firstname(cursor.getString(1));
                studentEntity.setStudent_lastname(cursor.getString(2));
                studentEntity.setStudent_emailaddress(cursor.getString(3));

                list.add(studentEntity);
            }while(cursor.moveToNext());
        }
        return list;
    }
        //get student by ID
        public StudentEntity getStudentById (int studentId){
        StudentEntity studentEntity = new StudentEntity();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id ="+studentId;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                studentEntity.setStudent_id(Integer.parseInt(cursor.getString(0)));
                studentEntity.setStudent_firstname(cursor.getString(1));
                studentEntity.setStudent_lastname(cursor.getString(2));
                studentEntity.setStudent_emailaddress(cursor.getString(3));
            }
            while (cursor.moveToNext());
        }
        return studentEntity;

    }
}
