package com.llorca.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<Android> {
    Context mContext;
    int mResource;

    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<Android> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int image = getItem(position).getLogo();
        String name = getItem(position).getName();
        String level = getItem(position).getLevel();
        String version = getItem(position).getVersion();
        String released = getItem(position).getReleased();
        String description = getItem(position).getDescription();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvVersion = convertView.findViewById(R.id.tvVersion);
        ImageView ivLogo = convertView.findViewById(R.id.ivLogo);
        TextView tvLevel = convertView.findViewById(R.id.tvLevel);
        TextView tvReleased = convertView.findViewById(R.id.tvReleased);
        tvName.setText(name);
        tvVersion.setText(version);
        tvLevel.setText(level);
        tvReleased.setText(released);
        ivLogo.setImageResource(image);
        return convertView;
    }
}