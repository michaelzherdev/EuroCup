package ru.mzherdev.eurocup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.model.EuroInfo;
import ru.mzherdev.eurocup.ui.EuroCupActivity;

/**
 * Created by Mikhail on 10.07.2016.
 */

public class EuroListAdapter extends RecyclerView.Adapter<EuroListAdapter.ViewHolder> {

    private List<EuroInfo> euros;
    private Context ctx;

    public EuroListAdapter(Context ctx, List<EuroInfo> euros) {
        this.ctx = ctx;
        this.euros = euros;
    }

    @Override
    public EuroListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_euro_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EuroListAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, EuroCupActivity.class);
                intent.putExtra("euro_data", euros.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("ViewHolder", "onClick " + position + "; " + euros.get(position).getName());
                ctx.startActivity(intent);
            }
        });

        viewHolder.textView.setText(euros.get(position).getName());
        Picasso.with(ctx)
                .load(euros.get(position).getImage())
                .resize(160, 112)
                .centerInside()
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return euros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_text);
            imageView = (ImageView) itemView.findViewById(R.id.row_image);
        }
    }
}
