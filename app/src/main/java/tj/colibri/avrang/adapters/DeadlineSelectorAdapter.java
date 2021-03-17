package tj.colibri.avrang.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tj.colibri.avrang.R;
import tj.colibri.avrang.data.checkout.spiner.DedlineSelect;

public class DeadlineSelectorAdapter extends ArrayAdapter<DedlineSelect> {

    int groupid;
    Activity context;
    List<DedlineSelect> list;
    LayoutInflater inflater;

    public DeadlineSelectorAdapter(Activity context, int groupid, int id, List<DedlineSelect>
            list){ super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    @SuppressLint("CheckResult")
    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);
        TextView textView=(TextView)itemView.findViewById(R.id.txt);
        textView.setText(list.get(position).getText());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}