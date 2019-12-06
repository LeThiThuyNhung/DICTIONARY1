package com.example.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TDTAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<TDT> tdtList;
    private LayoutInflater inflater;
    private int layout;
    List<TDT> mValue;
    ValueFilter valueFilter;

    public TDTAdapter(Context context, List<TDT> tdtList, int layout) {
        this.context = context;
        this.tdtList = tdtList;
        this.layout = layout;
        mValue = tdtList;
    }

    @Override
    public int getCount() {
        return tdtList.size();
    }

    @Override
    public Object getItem(int position) {
        return tdtList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(layout, null);
        }
        TextView txtWord2 = convertView.findViewById(R.id.txttu);
        TextView txtMeaning2 = convertView.findViewById(R.id.txtnghia);

        TDT tdt = tdtList.get(position);
        String word2 = tdt.getWord2();
        String meaning2 = tdt.getMeaning2();

        txtWord2.setText(word2);
        txtMeaning2.setText(meaning2);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter == null){
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends  Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<TDT> filterList = new ArrayList<TDT>();
                for( int i = 0; i < mValue.size(); i++){
                    if((mValue.get(i).getWord2().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        TDT yourWord = new TDT(mValue.get(i).getWord2(), mValue.get(i).getType2(), mValue.get(i).getPronounce2(), mValue.get(i).getMeaning2());
                        filterList.add(yourWord);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }else{
                results.count = mValue.size();
                results.values = mValue;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tdtList = (ArrayList<TDT>) results.values;
            notifyDataSetChanged();
        }
    }
}
