package com.example.vitaly.test_gym.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitaly.test_gym.R;
import com.example.vitaly.test_gym.activities.ShowPageActivity;


/**
 * Created by Android on 30.06.2016.
 */
public class ListOfPagesAdapter extends RecyclerView.Adapter {

    private Context context;
    private String[] arrayOfPages;

    AdapterHolder.AdapterOnClickListener onClickListener = new AdapterHolder.AdapterOnClickListener() {
        @Override
        public void clickOnItem(View v) {
            Intent intent = new Intent(context,ShowPageActivity.class);
            String url = ((TextView) v.findViewById(R.id.tvItemURL)).getText().toString();
            intent.putExtra(ShowPageActivity.URL_EXTRAS,url);
            context.startActivity(intent);
        }
    };

    public ListOfPagesAdapter(Context context){
        this.context = context;
        arrayOfPages = context.getResources().getStringArray(R.array.array_of_pages);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterHolder adapterHolder = (AdapterHolder) holder;
        adapterHolder.tvItemURL.setText(arrayOfPages[position]);
    }

    @Override
    public int getItemCount() {
        return arrayOfPages.length;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new AdapterHolder(itemView, onClickListener);
    }


    private static class AdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvItemURL;
        public AdapterOnClickListener myListener;

        public AdapterHolder(View itemView, AdapterOnClickListener listener) {
            super(itemView);
            myListener = listener;
            tvItemURL = (TextView) itemView.findViewById(R.id.tvItemURL);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myListener.clickOnItem(v);
        }

        public interface AdapterOnClickListener{
            void clickOnItem(View v);
        }
    }
}
