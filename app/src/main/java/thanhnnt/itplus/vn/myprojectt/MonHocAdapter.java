package thanhnnt.itplus.vn.myprojectt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 08/09/2017.
 */


public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.MyViewHolder> {

    private List<monHoc> l = new ArrayList<monHoc>();
    private Activity mA;

    public MonHocAdapter(List<monHoc> l, Activity mA) {
        this.l = l;
        this.mA = mA;
    }


    @Override
    public int getItemCount() {
        return l.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridview_item, parent, false);
        return new MyViewHolder(itemView,mA);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        monHoc mMonHoc = l.get(position);
        holder.bindData(mMonHoc,position);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RelativeLayout r;
        public TextView t, t2;
        public monHoc data = null;
        private Activity mA;
        public int po= 8;
        public MyViewHolder(final View view,Activity mA) {
            super(view);
            this.mA = mA;
            view.setOnClickListener(this);
            t = (TextView) view.findViewById(R.id.a);
            t2 = (TextView) view.findViewById(R.id.b);
            r = (RelativeLayout) view.findViewById(R.id.item_layout);

        }

        public void bindData(monHoc mMonHoc,int po) {
            data = mMonHoc;
            this.po = po;
            switch (po%5){
                case 0:
                    r.setBackgroundResource(R.drawable.ic_grade_blue);
                    break;
                case 1:
                    r.setBackgroundResource(R.drawable.ic_grade_green);
                    break;
                case 2:
                    r.setBackgroundResource(R.drawable.ic_grade_orange);
                    break;
                case 3:
                    r.setBackgroundResource(R.drawable.ic_grade_violet1);
                    break;
                case 4:
                    r.setBackgroundResource(R.drawable.ic_grade_yellow);
                    break;
            }
            t.setText(mMonHoc.getName());
            t2.setText(String.valueOf(mMonHoc.getDiemTb()));
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent it = new Intent(mA,Main2Activity.class);
            it.putExtra("id",l.get(position).getId());
            this.mA.startActivity(it);

        }
    }
}
