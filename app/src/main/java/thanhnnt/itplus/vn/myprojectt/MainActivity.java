package thanhnnt.itplus.vn.myprojectt;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   private RecyclerView recyclerView;
    int heSoDaChon = 0;
    private RadioGroup heso;
    private RadioButton he1,he2,he3;
    private MonHocAdapter adapter;
    private List<monHoc> l = new ArrayList<>();
    private dataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogThem();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    // đưa dữ liệu vào và hiển thị lên griView
    void themMonHoc(monHoc mh){
        db.insertDATA(mh.getName(),mh.getNote());
        l.clear();
        l.addAll(db.getDATA());
        adapter.notifyDataSetChanged();

    }
    private void initView() {
        RadioButton he1 = (RadioButton) findViewById(R.id.he12);
        RadioButton he2 = (RadioButton) findViewById(R.id.he22);
        RadioButton he3 = (RadioButton) findViewById(R.id.he32);
        RadioGroup heso = (RadioGroup) findViewById(R.id.luachon2) ;
        //initRadioButton();
        db = new dataBase(this);
        l.addAll(db.getDATA());
        recyclerView = (RecyclerView) findViewById(R.id.reCycleView);
        adapter = new MonHocAdapter(l,this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        
}



    private void initRadioButton() {

        heso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.he12:
                        heSoDaChon = 1;
                        break;
                    case R.id.he22:
                        heSoDaChon = 2;
                        break;
                    case R.id.he32:
                        heSoDaChon = 3;
                        break;
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }
// diaglog thêm môn học

    void dialogThem(){
        final AlertDialog.Builder buider = new AlertDialog.Builder(this);
        final View mView = getLayoutInflater().inflate(R.layout.them_monhoc,null);
        final EditText e1 = (EditText) mView.findViewById(R.id.TenMonHocc);
        final EditText e2 = (EditText) mView.findViewById(R.id.ghiChuu);
        ImageView b1 = (ImageView) mView.findViewById(R.id.dongg);
        ImageView b2 = (ImageView) mView.findViewById(R.id.capnhatt);

        buider.setCancelable(false);
        buider.setView(mView);
        final AlertDialog dialog = buider.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monHoc mh = new monHoc();
                mh.setName(e1.getText().toString());
                mh.setNote(e2.getText().toString());
               // e1.addTextChangedListener(new);
                if (e1.getText().toString().trim().length() > 0) {
                    themMonHoc(mh);
                    dialog.dismiss();
                } else {
                    e1.setError("Không được để trống");
                }
            }
        });
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

        } else if (id == R.id.bangdiem) {

        } else if (id == R.id.tailieu) {

        } else if (id == R.id.share) {

        } else if (id == R.id.setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
