package thanhnnt.itplus.vn.myprojectt;

/**
 * Created by Lenovo on 03/09/2017.
 */

public class monHoc {
    private String id;
    private String name;
    private float diemTb;
    private String Note;

    public monHoc() {
    }

    public monHoc(String id, String name, float diemTb , String Note) {
        this.id = id;
        this.name = name;
        this.diemTb = diemTb;
        this.Note = Note;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiemTb() {
        return diemTb;
    }

    public void setDiemTb(float diemTb) {
        this.diemTb = diemTb;
    }
}
