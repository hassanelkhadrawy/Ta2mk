package com.hassan.ta2mk.View;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.hassan.ta2mk.Adapters.AddImageAdapter;
import com.hassan.ta2mk.Common.Commens;
import com.hassan.ta2mk.Model.Dashboard.AddItemInterface;
import com.hassan.ta2mk.Presenter.AddItemPresenter;
import com.hassan.ta2mk.Presenter.Fonts.Comfortaa_Bold;
import com.hassan.ta2mk.R;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity implements AddItemInterface.View {
    AddItemPresenter addItemPresenter;
    private Spinner selectCategory;
    private Spinner selectSize;
    private Spinner selectTypes;
    // private ImageButton selectImage;
    private Comfortaa_Bold upLoadItem;
    private ArrayAdapter<String> adapterCategory;
    private ArrayAdapter<String> adapterSize;
    private ArrayAdapter<String> adapterTypes;
    private ArrayList<Uri> uriArrayList;
    private LinearLayout addItemContainer;
    private Uri saveuri;
    private RecyclerView addImageRecycler;
    private TextView Addphoto;
    private TextView Adddescription;
    private TextView phototext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initView();
        Action();
    }


    @Override
    public void ShowCategories(ArrayList<String> list) {

        adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ffffff"));
                return view;
            }
        };
        selectCategory.setAdapter(adapterCategory);

        Commens.mProgressDialog.dismiss();


    }

    @Override
    public void ShowSize(ArrayList<String> list) {

        adapterSize = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, list) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ffffff"));
                return view;
            }
        };
        selectSize.setAdapter(adapterSize);
    }

    @Override
    public void ShowTypes(ArrayList<String> list) {
        adapterTypes = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, list) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#ffffff"));
                return view;
            }
        };
        selectTypes.setAdapter(adapterTypes);
    }

    @Override
    public void onSuccess() {
        Commens.mProgressDialog.dismiss();
        Snackbar.make(addItemContainer, getString(R.string.success), Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void Failure(String Message) {
        Commens.mProgressDialog.dismiss();
        Snackbar.make(addItemContainer, Message, Snackbar.LENGTH_SHORT).show();

    }

    private void initView() {
        addItemContainer = (LinearLayout) findViewById(R.id.addItemContainer);
        selectCategory = (Spinner) findViewById(R.id.selectCategory);
        selectSize = (Spinner) findViewById(R.id.selectSize);
        selectTypes = (Spinner) findViewById(R.id.selectColor);
        //selectImage = (ImageButton) findViewById(R.id.selectImage);
        upLoadItem = (Comfortaa_Bold) findViewById(R.id.upLoadItem);
        addItemPresenter = new AddItemPresenter(this);
        addItemPresenter.Categories();
        addItemPresenter.Size();
        Commens.Prograss(this, getString(R.string.wait));
        Commens.mProgressDialog.show();
        addImageRecycler = (RecyclerView) findViewById(R.id.addImageRecycler);
        Addphoto = (TextView) findViewById(R.id._addphoto);
        Adddescription = (TextView) findViewById(R.id._adddescription);
        phototext = (TextView) findViewById(R.id.phototext);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == 1) {


            uriArrayList = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {

                for (int i = 0; i < clipData.getItemCount(); i++) {
                    saveuri = clipData.getItemAt(i).getUri();


                    uriArrayList.add(saveuri);
                    // selectImage.setImageURI(saveuri);

//                    try {
//                        InputStream inputStream=getContentResolver().openInputStream(saveuri);
//                        Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
//                        bitmapList.add(bitmap);
//
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

                }


            } else {


                saveuri = data.getData();
                uriArrayList.add(saveuri);

                //selectImage.setImageURI(saveuri);
//                try {
//                    InputStream inputStream=getContentResolver().openInputStream(saveuri);
//                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
//                    bitmapList.add(bitmap);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTraceHomeTabPresenter();
//                }
            }
            if (!uriArrayList.isEmpty()){

                phototext.setVisibility(View.GONE);
                addImageRecycler.setVisibility(View.VISIBLE);
                addImageRecycler.setLayoutManager(new GridLayoutManager(this, 3));
                AddImageAdapter addImageAdapter = new AddImageAdapter(this, uriArrayList);
                addImageRecycler.setAdapter(addImageAdapter);
                addImageAdapter.notifyDataSetChanged();

            }


        }
    }

    private void Action() {



        selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addItemPresenter.Types(selectCategory.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemPresenter.SelectImage(AddItem.this);
                 }
        });

        upLoadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commens.mProgressDialog.show();
                addItemPresenter.SendItemData(uriArrayList, selectCategory.getSelectedItem().toString(),
                        selectTypes.getSelectedItem().toString(), selectSize.getSelectedItem().toString());

            }
        });
    }
}
