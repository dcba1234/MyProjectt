package thanhnnt.itplus.vn.myprojectt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 01/09/2017.
 */

public class dataBase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "quanlimonhoc";
    private static final String TABLE_MONHOC = "monhoc";
    private static final String TABLE_DIEM = "diem";
    private Context mContext;
    private SQLiteDatabase db;
    public dataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QueryMonhoc = "CREATE TABLE "+TABLE_MONHOC + "( ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , NAME	TEXT NOT NULL , DIEMTB FLOAT ,NOTE TEXT )";
        String QueryDiem = "CREATE TABLE "+TABLE_DIEM + "( ID INTEGER , DIEM FLOAT , HESO INTEGER , NOTE TEXT , NGAY TEXT )";
        db.execSQL(QueryMonhoc);
        db.execSQL(QueryDiem);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDATA(String ten,String note){
        db = this.getWritableDatabase();
        try {
            Float diemtb = null;
            ContentValues values = new ContentValues();
            values.put("NAME",ten);
            values.put("DIEMTB",diemtb);
            values.put("NOTE",note);
            long i = db.insert(TABLE_MONHOC,null,values);
            if(i>=1) Toast.makeText(mContext, "Tạo mới thành công", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void insertDATA2(String id,  float diem, int heSo,String note, String date){
        db = this.getWritableDatabase();
        try {
            Float diemtb = null;

            ContentValues values = new ContentValues();
            values.put("ID",id);
            values.put("HESO",heSo);
            values.put("DIEM",diem);
            values.put("NOTE",note);
            values.put("NGAY",date);
            long i = db.insert(TABLE_DIEM,null,values);
            if(i>=1) Toast.makeText(mContext, "Tạo mới thành công", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
    }

    public List<monHoc> getDATA(){
        List<monHoc> l = new ArrayList<monHoc>();
        db = this.getWritableDatabase();
        try {
            String s = "select * from "+TABLE_MONHOC;
            Cursor cur = db.rawQuery(s,null);

            if(cur!=null){
                cur.moveToFirst();
                while (!cur.isAfterLast()){
                    monHoc mh = new monHoc(cur.getString(0),cur.getString(1),cur.getFloat(2),cur.getString(3));
                    l.add(mh);
                    cur.moveToNext();
                }
                cur.close();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return l;
    }

    public List<diem> getDATA2(String id){
        List<diem> l = new ArrayList<diem>();
        db = this.getWritableDatabase();
        try {
            String s = "select * from "+ TABLE_MONHOC + " where ID = " +id ;
            Cursor cur = db.rawQuery(s,null);

            if(cur!=null){
                cur.moveToFirst();
                while (!cur.isAfterLast()){
                    diem d = new diem(cur.getString(0),cur.getFloat(1),cur.getInt(3),cur.getString(3),cur.getString(4));
                    l.add(d);
                    cur.moveToNext();
                }
                cur.close();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return l;
    }
    public List<diem> getDATA3(String id,int heSo){
        List<diem> l = new ArrayList<diem>();
        db = this.getWritableDatabase();
        try {
            String s = "select * from "+ TABLE_DIEM + " where ID = " +id+" and HESO = "+heSo ;
            Cursor cur = db.rawQuery(s,null);

            if(cur!=null){
                cur.moveToFirst();
                while (!cur.isAfterLast()){
                    diem d = new diem(cur.getString(0),cur.getFloat(1),cur.getInt(2),cur.getString(3),cur.getString(4));
                    l.add(d);
                    cur.moveToNext();
                }
                cur.close();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return l;
    }

    public List<Integer> getID(){
        List<Integer> l = new ArrayList<>();
        db = this.getWritableDatabase();
        try {
            String s = "select ID from "+TABLE_MONHOC ;
            Cursor cur = db.rawQuery(s,null);
            if(cur!=null){
                cur.moveToFirst();
                while (!cur.isAfterLast()){
                    l.add(cur.getInt(0));

                    cur.moveToNext();
                }
                cur.close();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }
        return l;
    }

    public double diemTB(String id){
        float diemtb = 0;
        List<diem> heso1 = new ArrayList<diem>();
        Toast.makeText(mContext, String.valueOf(getID().size()), Toast.LENGTH_SHORT).show();
        float diem1 = 0,diem2=0,diem3=0;
        db = this.getWritableDatabase();
        try {
            String s = "select SUM(DIEM) from "+TABLE_DIEM +" where ID = "+id+" and HESO = 1";
            Cursor cur = db.rawQuery(s,null);
            Toast.makeText(mContext, " scscscs", Toast.LENGTH_SHORT).show();
            if(cur!=null){
                cur.moveToFirst();
                while (!cur.isAfterLast()){
                    diem1 = cur.getFloat(0);
                    cur.moveToNext();
                }
                cur.close();
            }

            String s2 = "select SUM(DIEM) from "+TABLE_DIEM +" where ID = "+id+" and HESO = 2";
            Cursor cur2 = db.rawQuery(s2,null);
            Toast.makeText(mContext, " scscscs", Toast.LENGTH_SHORT).show();
            if(cur2!=null){
                cur2.moveToFirst();
                while (!cur2.isAfterLast()){
                    diem2 = cur2.getFloat(0);
                    cur2.moveToNext();
                }
                cur2.close();
            }

            String s3 = "select SUM(DIEM) from "+TABLE_DIEM +" where ID = "+id+" and HESO = 3";
            Cursor cur3 = db.rawQuery(s3,null);
            Toast.makeText(mContext, " scscscs", Toast.LENGTH_SHORT).show();
            if(cur3!=null){
                cur3.moveToFirst();
                while (!cur3.isAfterLast()){
                    diem3 = cur3.getFloat(0);
                    cur3.moveToNext();
                }
                cur3.close();
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            db.close();
        }

        diemtb = (diem1 + diem2*2+diem3*3);
        return diemtb;
    }

    public void delete_monHoc(String[] s){
        db = this.getWritableDatabase();
        try {
            long ketq = db.delete(TABLE_MONHOC,"NAME=?",s);
            if(ketq==1) Toast.makeText(mContext, "Xóa thành công", Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
           db.close();
        }
    }
}
