package tj.colibri.avrang.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tj.colibri.avrang.R;
import tj.colibri.avrang.data.checkout.spiner.BankSelect;

public class BankSelectorAdapter extends ArrayAdapter<BankSelect> {

    int groupid;
    Activity context;
    List<BankSelect> list;
    LayoutInflater inflater;
    public BankSelectorAdapter(Activity context, int groupid, int id, List<BankSelect>
            list){ super(context,id,list);
        this.list=list;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
    }

    @SuppressLint("CheckResult")
    public View getView(int position, View convertView, ViewGroup parent ){
        View itemView=inflater.inflate(groupid,parent,false);
        ImageView imageView= (ImageView)itemView.findViewById(R.id.img);

        Glide.with(itemView).load(list.get(position).getImage()).into(imageView);

        TextView textView=(TextView)itemView.findViewById(R.id.txt);
        textView.setText(list.get(position).getName());
        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup
            parent){
        return getView(position,convertView,parent);

    }
}