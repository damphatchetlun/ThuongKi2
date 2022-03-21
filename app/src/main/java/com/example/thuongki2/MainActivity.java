package com.example.thuongki2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lvNV;
    ArrayList<NhanVien> data;
    ImageView iv;
    EditText txtTen;
    EditText txtNS;
    RadioGroup radioGroupGT;
    RadioButton radioButtonNam, radioButtonNu;
    Spinner spnDVCT;
    Button btnThem, btnSua, btnXoa, btnTruyVan;

    int REQUEST_ID_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageView);
        Button anh = findViewById(R.id.btnAnh);
        anh.setOnClickListener(view -> captureImage());

        Button thoat = findViewById(R.id.btnThoat);
        thoat.setOnClickListener(view -> MainActivity.this.finish());

        txtTen = findViewById(R.id.editText);
        txtNS = findViewById(R.id.editTextDate);
        radioGroupGT = findViewById(R.id.radioGroup);
        spnDVCT = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dvct, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDVCT.setAdapter(adapter);

        lvNV = findViewById(R.id.listView);
        data = new ArrayList<>();

        data.add(new NhanVien(R.drawable.ic_baseline_camera_alt_24, "Võ Trung Hiếu", "25/11/2001", "Nam", "Vườn cau"));
        NhanVienAdapter nhanVienAdapter = new NhanVienAdapter(this, R.layout.list_item, data);
        lvNV.setAdapter(nhanVienAdapter);

        radioButtonNam = findViewById(R.id.radioButtonNam);
        radioButtonNu = findViewById(R.id.radioButtonNu);
        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                String ten = txtTen.getText().toString();
                String ns = txtNS.getText().toString();
                String gt = "";
                int idChecked = radioGroupGT.getCheckedRadioButtonId();
                switch(idChecked){
                    case R.id.radioButtonNu:{gt = "Nữ";break;}
                    case R.id.radioButtonNam:gt = "Nam";
                }
                String dvct = spnDVCT.getSelectedItem().toString();
                data.add(new NhanVien(1, ten, ns, gt, dvct));
                lvNV.deferNotifyDataSetChanged();
                lvNV.setAdapter(nhanVienAdapter);
            }
        });

        btnXoa = findViewById(R.id.btnXoa);
        lvNV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "Đã chọn" + lvNV.getCheckedItemPosition(), Toast.LENGTH_SHORT).show();
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        data.remove(i);
                    }
                });
                lvNV.deferNotifyDataSetChanged();
                lvNV.setAdapter(nhanVienAdapter);
                return false;
            }
        });

        btnSua = findViewById(R.id.btnSua);
        lvNV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = i;
                txtTen.setText(data.get(i).ten);
                txtNS.setText(data.get(i).ns);
                if (data.get(i).gt.equals("Nam") == true)
                    radioButtonNam.setChecked(true);
                else radioButtonNu.setChecked(true);

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String t = txtTen.getText().toString();
                        String n = txtNS.getText().toString();
                        String g = "";
                        int idChecked = radioGroupGT.getCheckedRadioButtonId();
                        switch(idChecked){
                            case R.id.radioButtonNu:{g = "Nữ";break;}
                            case R.id.radioButtonNam:g = "Nam";
                        }
                        String d = spnDVCT.getSelectedItem().toString();
                        NhanVien nvCapNhat = new NhanVien(1, t, n, g, d);
                        data.set(id,nvCapNhat);
                        lvNV.deferNotifyDataSetChanged();
                        lvNV.setAdapter(nhanVienAdapter);
                    }
                });
            }
        });


        btnTruyVan = findViewById(R.id.btnTruyVan);
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                this.iv.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}