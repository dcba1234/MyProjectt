package thanhnnt.itplus.vn.myprojectt;

/**
 * Created by Lenovo on 12/09/2017.
 */

public class diem {
    String id;
    String date;
    float diem;
    String note;
    int heSo;

    public diem() {
    }

    public diem(String id,  float diem, int heSo,String note, String date) {
        this.id = id;
        this.date = date;
        this.diem = diem;
        this.note = note;
        this.heSo = heSo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getHeSo() {
        return heSo;
    }

    public void setHeSo(int heSo) {
        this.heSo = heSo;
    }
}