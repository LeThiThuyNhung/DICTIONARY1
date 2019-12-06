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


public class YourWordAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private  List<YourWord> yourWordList;
    private  LayoutInflater inflater;
    private int layout;
    List<YourWord> mValue;
    ValueFilter valueFilter;

    public YourWordAdapter(Context context, List<YourWord> yourWordList, int layout) {
        this.context = context;
        this.yourWordList = yourWordList;
        this.layout = layout;
        mValue = yourWordList;
    }


    @Override
    public int getCount() {
        return yourWordList.size();
    }

    @Override
    public Object getItem(int position) {
        return yourWordList.get(position);
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
        TextView txtWord1 = convertView.findViewById(R.id.txttu);
        TextView txtMeaning1 = convertView.findViewById(R.id.txtnghia);
//        ImageView imv1 = convertView.findViewById(R.id.imgloa);
//        ImageView imv2 = convertView.findViewById(R.id.imgmnu);

        YourWord yourWord = yourWordList.get(position);
        String word1 = yourWord.getWord1();
        String meaning1 = yourWord.getMeaning1();

        txtWord1.setText(word1);
        txtMeaning1.setText(meaning1);
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
                ArrayList<YourWord> filterList = new ArrayList<YourWord>();
                for( int i = 0; i < mValue.size(); i++){
                    if((mValue.get(i).getWord1().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        YourWord yourWord = new YourWord(mValue.get(i).getWord1(), mValue.get(i).getType1(), mValue.get(i).getPronounce1(), mValue.get(i).getMeaning1());
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
            yourWordList = (ArrayList<YourWord>) results.values;
            notifyDataSetChanged();
        }
    }
}
