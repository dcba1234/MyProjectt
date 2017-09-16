package thanhnnt.itplus.vn.myprojectt;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lenovo on 13/09/2017.
 */

public class expan_listView extends BaseExpandableListAdapter{
    private List<String> listDataHeader = new ArrayList<String>();
    private Context mContext;
    private HashMap<String,List<diem>> listHashMap;
    private float x=5;
    public expan_listView(List<String> l, Context mContext, HashMap<String, List<diem>> listHashMap) {
        listDataHeader = l;
        this.mContext = mContext;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ex_group_listvie,null);
        }
        convertView.setBackgroundResource(R.drawable.expanable_listview);

       convertView.setElevation(x);
        convertView.setTranslationZ(10);
        TextView t = (TextView) convertView.findViewById(R.id.list_header);
        t.setTypeface(null, Typeface.BOLD);
        t.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        diem child = (diem) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ex_listview,null);
        }
        parent.setElevation(0);
        TextView t1 = (TextView) convertView.findViewById(R.id.hai_1);
        TextView t2 = (TextView) convertView.findViewById(R.id.hai_2);
        TextView t3 = (TextView) convertView.findViewById(R.id.hai_3);
        //t1.setText(listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition).getDiem().toString());
        t1.setText(String.valueOf(child.getDiem()));
        t3.setText(child.getDate());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {


        return true;
    }
}
