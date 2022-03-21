package com.example.thuongki2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<NhanVien> arrayNV;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> arrayNV) {
        this.context = context;
        this.layout = layout;
        this.arrayNV = arrayNV;
    }

    @Override
    public int getCount() {
        return arrayNV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        ImageView imgAnh = view.findViewById(R.id.imgAnh);
//        imgAnh.setImageResource(arrayNV.get(i).anh);

        TextView txtTen = view.findViewById(R.id.txtTen);
        txtTen.setText(arrayNV.get(i).ten);

        TextView txtNamSinh = view.findViewById(R.id.txtns);
        txtNamSinh.setText(arrayNV.get(i).ns);

        TextView txtGioiTinh = view.findViewById(R.id.txtgt);
        txtGioiTinh.setText(arrayNV.get(i).gt);

        TextView txtDVCT = view.findViewById(R.id.txtdvct);
        txtDVCT.setText(arrayNV.get(i).dv);

        return view;
    }
}
