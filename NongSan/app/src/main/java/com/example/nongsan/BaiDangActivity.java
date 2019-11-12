package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BaiDangActivity extends AppCompatActivity {
    private AsyncTask<?,?,?> asyncTask;
    private Button btndangbai;
    private ImageView ImageBaiDang;
    private EditText txtten,txtgia,txtnoidung;
    private List<String> listDonViTinh ;
    private List<String> listDanhMuc ;
    private Uri imagePath;
    private int max;
    private String imageURL;
    private Spinner sploai,spdonvitinh;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageRef = firebaseStorage.getReference();

    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_dang);
        AddControl();
        LoadData();
        imgAvatarOnclick();
        ButtonClick();
    }

    private List<DanhMuc> sort(List<DanhMuc> danhMuc) {
        for(int i = 0; i<danhMuc.size();i++) {
            for(int j =0;j<i+1;j++) {
                if(danhMuc.get(i).id<danhMuc.get(j).id)
                {
                    DanhMuc temp = danhMuc.get(i);
                    danhMuc.set(i,danhMuc.get(j));
                    danhMuc.set(j,temp);
                }
            }
        }
        return danhMuc;
    }

        private void  getMaxId() {
            max = 0;
           firebaseFirestore.collection("BaiDang").get().addOnCompleteListener(
                    new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                                for (DocumentSnapshot snapshot : documentSnapshots) {
                                    BaiDang baidang = snapshot.toObject(BaiDang.class);
                                    if (baidang.getId() > max) max = baidang.getId();
                                }
                                max = max+1;
                                taoBaiDang();


                            }


                        }
                    }
            );
    }

    private void imgAvatarOnclick() {
        ImageBaiDang.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent pickingImage = new Intent(Intent.ACTION_PICK);
                        pickingImage.setType("image/*");
                        startActivityForResult(pickingImage,111);
                    }
                }
        );
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 111 && resultCode==RESULT_OK && data!=null) {
            try {
                imagePath = data.getData();
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageBaiDang.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void ButtonClick() {
        btndangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });
    }

    public synchronized void uploadImage(){
        final StorageReference imagesRef = storageRef.child("BaiDang/" + UUID.randomUUID().toString());
        imagesRef.putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Task<Uri> taskUri = task.getResult().getMetadata().getReference().getDownloadUrl();

                taskUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageURL = uri.toString();
                        getMaxId();
                    }
                });
            }
        });

    }

    private void taoBaiDang() {
        BaiDang baiDang = new BaiDang(
                max,
                txtten.getText().toString(),
                txtnoidung.getText().toString(),
                new Date(),
                imageURL,
                sharedPreference.read("username",null),
                sploai.getSelectedItem().toString(),
                spdonvitinh.getSelectedItem().toString(),
                txtgia.getText().toString());

        firebaseFirestore.collection("BaiDang").document().set(baiDang).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("create", "create success");
                        }
                        else {
                            Log.d("create", "create fail");
                        }
                    }
                }
        );
    }
    private void LoadData() {
        firebaseFirestore.collection("DanhMuc").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listDanhMuc = new ArrayList<>();

                        // tao list danh muc ne`
                        List<DanhMuc> danhMucs = new ArrayList<>();
                       if(task.isSuccessful()) {
                           List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                           // lay du lieu tu firebase ve
                           for(DocumentSnapshot snapshot: documentSnapshots) {
                               DanhMuc danhmuc = snapshot.toObject(DanhMuc.class);
                               danhMucs.add(danhmuc);
                           }
                           // sap xep lai list danh muc theo id tang dan`.
                           danhMucs = sort(danhMucs);
                           for(DanhMuc dm: danhMucs) {
                                listDanhMuc.add(dm.description);
                           }
                           // set data cho spiner
                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listDanhMuc);
                           sploai.setAdapter(adapter);
                       }
                    }
                }
        );

        firebaseFirestore.collection("DonViTinh").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<DanhMuc> listTemp = new ArrayList<>();
                listDonViTinh = new ArrayList<>();
                if(task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                    for(DocumentSnapshot snapshot: documentSnapshots) {
                        DanhMuc danhmuc = snapshot.toObject(DanhMuc.class) ;
                        listTemp.add(danhmuc);
                    }
                    listTemp = sort(listTemp);
                    for(DanhMuc dm: listTemp) {
                        listDonViTinh.add(dm.description);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listDonViTinh);
                    spdonvitinh.setAdapter(adapter);
                }
            }
        });
    }

    private void  AddControl() {
        sploai = (Spinner)findViewById(R.id.SpLoai);
        spdonvitinh = (Spinner)findViewById(R.id.SpDonvitinh);
        btndangbai = (Button) findViewById(R.id.btndangbai);
        ImageBaiDang = (ImageView) findViewById(R.id.imagebaidang);
        txtten = (EditText) findViewById(R.id.edtBaidang);
        txtgia = (EditText) findViewById(R.id.edtGia);
        txtnoidung = (EditText) findViewById(R.id.edtNoidung);
    }
}
