package thanhnnt.itplus.vn.myprojectt;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ExpandableListView ex;
    private List<String> header;
    private RadioGroup heso;
    TextView tv;
    private EditText nhapso;
    private ImageView close, update, ghichu;
    private RadioButton he1, he2, he3;
    int heSoDaChon = 0;
    String ngayGio, id;
    private HashMap<String, List<diem>> l;
    private expan_listView adapter;
    private dataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getID();
        init();

    }

    private void getID() {
        id = getIntent().getStringExtra("id");

    }

    private void init() {
        db = new dataBase(this);
        tv = (TextView) findViewById(R.id.aaaa);
        ex = (ExpandableListView) findViewById(R.id.exListView);
        diem d = new diem();
        Date date = new Date();
        db.diemTB(id);
        String strDateFormat12 = "hh:mm:ss a";
        String strDateFormat24 = "HH:mm ";
        String ngayThangNam = "dd/MM/yyyy";
        SimpleDateFormat sdf = null;
        SimpleDateFormat sdf2 = null;
        //Tạo đối tượng SimpleDateFormat với định dạng 12
        sdf = new SimpleDateFormat(strDateFormat24);
        sdf2 = new SimpleDateFormat(ngayThangNam);
        // Toast.makeText(this, "->  "+ thu(date)+ "  "+ sdf2.format(date)+"  "+ sdf.format(date) , Toast.LENGTH_SHORT).show();
        ngayGio = thu(date) + " " + sdf2.format(date) + " " + sdf.format(date);
        initData();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
        ex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    public void Dialog() {
        final float[] diemSo = new float[1];
        final String note = "aaaa";
        final AlertDialog.Builder buider = new AlertDialog.Builder(this);
        final View mView = getLayoutInflater().inflate(R.layout.themdiem, null);
        buider.setCancelable(false);
        buider.setView(mView);
        final AlertDialog dialog = buider.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        he1 = (RadioButton) mView.findViewById(R.id.he12);
        he2 = (RadioButton) mView.findViewById(R.id.he22);
        he3 = (RadioButton) mView.findViewById(R.id.he32);
        heso = (RadioGroup) mView.findViewById(R.id.luachon2);
        nhapso = (EditText) mView.findViewById(R.id.nhapso);
        close = (ImageView) mView.findViewById(R.id.close);
        update = (ImageView) mView.findViewById(R.id.update);
        ghichu = (ImageView) mView.findViewById(R.id.ghichu);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ghichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        heso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.he12:
                        heSoDaChon = 1;
                        break;
                    case R.id.he22:
                        heSoDaChon = 2;
                        break;
                    case R.id.he32:
                        heSoDaChon = 3;
                        break;
                    default:
                        heSoDaChon = 0;
                        break;
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diemsp = nhapso.getText().toString();
                if (diemsp.isEmpty()) nhapso.setError("Nhập Điểm");
                else
                    diemSo[0] = Float.valueOf(nhapso.getText().toString());
                if (heSoDaChon == 0)
                    Toast.makeText(Main2Activity.this, "Chọn hệ số", Toast.LENGTH_SHORT).show();
                else if (diemSo[0] > 10 || diemSo[0] < 0) nhapso.setError("Chọn từ 1 -> 10");
                else {
                    db.insertDATA2(id, diemSo[0], heSoDaChon, "aaa", ngayGio);
                    initData();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();


                }
            }
        });

        dialog.show();
    }

    public String thu(Date date) {
        String s = "";
        switch (date.getDay()) {
            case 0:
                s = "Chủ Nhật";
                break;
            case 1:
                s = "Thứ Hai";
                break;
            case 2:
                s = "Thứ Ba";
                break;
            case 3:
                s = "Thứ Tư";
                break;
            case 4:
                s = "Thứ Năm";
                break;
            case 5:
                s = "Thứ Sáu";
                break;
            case 6:
                s = "Thứ Bảy";
                break;

        }

        return s;
    }

    private void initData() {
        header = new ArrayList<>();
        l = new HashMap<>();
        header.add("Hệ số 1");
        header.add("Hệ số 2");
        header.add("Hệ số 3");
        diem d = new diem();
        //hệ số 1
        List<diem> heso1 = new ArrayList<>();

        heso1.addAll(db.getDATA3(id, 1));
        //hệ số 2
        List<diem> heso2 = new ArrayList<>();
        heso2.addAll(db.getDATA3(id, 2));
        //hệ số 3
        List<diem> heso3 = new ArrayList<>();
        heso3.addAll(db.getDATA3(id, 3));
        // ---------
        l.put(header.get(0), heso1);
        l.put(header.get(1), heso2);
        l.put(header.get(2), heso3);
        adapter = new expan_listView(header, this, l);
        ex.setAdapter(adapter);


    }

}
