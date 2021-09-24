

package com.example.e_library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper
{
    public  static final String DBNAME="Libray.db";
    public DbHelper(Context context)

    {
        super(context,"Libray.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB)
    {

        MyDB.execSQL("CREATE TABLE Student(Id INTEGER PRIMARY KEY AUTOINCREMENT,Username varchar(15),Email varchar(50),Phone int(10),Password varchar(16))");
        MyDB.execSQL("CREATE TABLE Bookrequest(Id INTEGER PRIMARY KEY AUTOINCREMENT,txtDate date,bookName varchar(20),Authername varchar(20),name varchar(20),email varchar(50),phone int(10),enrollmentNo int(15),status varchar(50))");
        MyDB.execSQL("CREATE TABLE FeedBack(Id INTEGER PRIMARY KEY AUTOINCREMENT,Username varchar(15),Email varchar(50),Phone int(10),feedback varchar(50),Rating float(5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion)
    {
        MyDB.execSQL("DROP TABLE Student");
        MyDB.execSQL("DROP TABLE Bookrequest");
        MyDB.execSQL("DROP TABLE FeedBack");

        onCreate(MyDB);
    }
    public boolean insertStudentData(String Username,String Email,String Phone,String Password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",Username);
        contentValues.put("Email",Email);
        contentValues.put("Phone",Phone);
        contentValues.put("Password",Password);

        long result=MyDB.insert("Student",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertBookrquest(String txtDate,String bookName,String Authername,String name,String email,String phone,String enrollmentNo,String status)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        // contentValues.put("txtDate",txtDate);
        contentValues.put("txtDate",txtDate);
        contentValues.put("bookName",bookName);
        contentValues.put("Authername",Authername);
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        contentValues.put("enrollmentNo",enrollmentNo);
        contentValues.put("status",status);

        long result=MyDB.insert("Bookrequest",null,contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }

    }
    public boolean insertFeedbackData(String Username, String Email, String Phone, String feedback, float Rating)
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",Username);
        contentValues.put("Email",Email);
        contentValues.put("Phone",Phone);
        contentValues.put("feedback",feedback);
        contentValues.put("Rating",Rating);

        long result=MYDB.insert("FeedBack",null,contentValues);

        if (result==-1)
            return false;
        else
            return true;
    }


    public Boolean chekStudentEmail(String Email)
    {
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from Student where Email=?",new String[]{Email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkStudentEmailPassword(String Email,String Password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("SELECT * FROM Student WHERE Email=? AND Password=?",new String[]{Email,Password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getData()
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("SELECT Username,Email,Phone FROM Student",null);
        return cursor;
    }


    /*public  Cursor bookstatus()
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        Cursor cursor=MYDB.rawQuery("SELECT * from Bookrequest",null);
        return cursor;
    }
*/
    public Cursor CancelRequest()
    {
        SQLiteDatabase MYDB=this.getWritableDatabase();
        Cursor cursor=MYDB.rawQuery("DELETE FROM Bookrequest WHERE id=0",null);
        return  cursor;
    }
    public Boolean feedbackCheck(String Email)
    {
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from FeedBack where Email=?",new String[]{Email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean BookCheck(String Email)
    {
        SQLiteDatabase MyDB=this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from Bookrequest where Email=?",new String[]{Email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public Boolean getProfile(String Email,String Username)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("SELECT * FROM Student WHERE Email=? AND Password=?",new String[]{Email,Username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    BookStatusDisplay[] displayBookStatus()
    {
        SQLiteDatabase MYDB=this.getReadableDatabase();
        Cursor cursor=MYDB.rawQuery("SELECT txtDate,bookName,Authername,enrollmentNo,status from Bookrequest",null);
        if (cursor.getCount()==0)
        {
            return null;
        }
        else
        {
            BookStatusDisplay[] displayBookList=new BookStatusDisplay[cursor.getCount()];
            int ctr=0;
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                BookStatusDisplay display=new BookStatusDisplay();
                display.setDate(cursor.getString(cursor.getColumnIndex("txtDate")));
                display.setBook(cursor.getString(cursor.getColumnIndex("bookName")));
                display.setAuthor(cursor.getString(cursor.getColumnIndex("Authername")));
                display.setEnroll(cursor.getString(cursor.getColumnIndex("enrollmentNo")));
                display.setStatus(cursor.getString(cursor.getColumnIndex("status")));

                displayBookList[ctr++]=display;
                cursor.moveToNext();
            }
            return  displayBookList;
        }

    }

}
