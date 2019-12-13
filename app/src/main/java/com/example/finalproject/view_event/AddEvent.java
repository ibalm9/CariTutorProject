package com.example.finalproject.view_event;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.Adapter.RequestHandler;
import com.example.finalproject.entity.BaseActivity;
import com.example.finalproject.network.ConfigEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by tanwir on 09/05/2016.
 */

public class AddEvent extends BaseActivity {

    private EditText inputName, inputPublished, inputDate, inputTicket, inputDesc, inputLocation, inputTime;
    private TextInputLayout inputLayoutName,inputLayoutPublished, inputLayoutDate, inputLayoutTicket, inputLayoutDesc, inputLayoutLocation, inputLayoutTime;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enterFromBottomAnimation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_home_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.LTGRAY);
        toolbar.setTitleMargin(20,20,20,20);

       toolbar.setTitle("Tentang Matakuliahmu");

        imageView = (ImageView) findViewById(R.id.imageView);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPublished = (TextInputLayout) findViewById(R.id.input_layout_published);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date);
        inputLayoutTicket = (TextInputLayout) findViewById(R.id.input_layout_ticket);
        inputLayoutDesc = (TextInputLayout) findViewById(R.id.input_layout_desc);
        inputLayoutLocation = (TextInputLayout) findViewById(R.id.input_layout_location);
        inputLayoutTime = (TextInputLayout) findViewById(R.id.input_layout_time);


        inputName = (EditText) findViewById(R.id.input_name);
        inputPublished = (EditText) findViewById(R.id.input_published);
        inputDate = (EditText) findViewById(R.id.input_date);
        inputTicket = (EditText) findViewById(R.id.input_ticket);
        inputDesc = (EditText) findViewById(R.id.input_desc);
        inputLocation = (EditText) findViewById(R.id.input_location);
        inputTime =  (EditText) findViewById(R.id.input_time);


        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputPublished.addTextChangedListener(new MyTextWatcher(inputPublished));
        inputDate.addTextChangedListener(new MyTextWatcher(inputDate));
        inputTicket.addTextChangedListener(new MyTextWatcher(inputTicket));
        inputDesc.addTextChangedListener(new MyTextWatcher(inputDesc));
        inputTime.addTextChangedListener(new MyTextWatcher(inputTime));

        FloatingActionButton fabFile = (FloatingActionButton) findViewById(R.id.fabFile);
        fabFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        FloatingActionButton fabSimpan = (FloatingActionButton) findViewById(R.id.fabSimpan);
        fabSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

public String getStringImage(Bitmap bmp) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] imageBytes = baos.toByteArray();
    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    return encodedImage;
}

    private void uploadImage(){

        final String name = inputName.getText().toString().trim();
        final String pbs = inputPublished.getText().toString().trim();
        final String date = inputDate.getText().toString().trim();
        final String location = inputLocation.getText().toString().trim();
        final String description = inputDesc.getText().toString().trim();
        final String ticket = inputTicket.getText().toString().trim();

        final String time = inputTime.getText().toString().trim();




        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddEvent.this, "Tunggu Sebentar...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                onBackPressed();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }



            @Override
            protected String doInBackground(Bitmap... params) {



                Bitmap bitmap = params[0];

                if(bitmap == null){
                    bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder);
                }


                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();

                data.put(ConfigEvent.TAG_NAME, name);
                data.put(ConfigEvent.TAG_PUBLISHER, pbs);
                data.put(ConfigEvent.TAG_DATE, date);
                data.put(ConfigEvent.TAG_LOCATION, location);
                data.put(ConfigEvent.TAG_TICKET,ticket);
                data.put(ConfigEvent.TAG_TIME,time);

                data.put(ConfigEvent.TAG_DESCRIPTION, description);
                data.put(ConfigEvent.UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(ConfigEvent.UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    @Override
    protected void onPause() {
        exitToBottomAnimation();
        super.onPause();
    }



    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validatePublished()) {
            return;
        }

        uploadImage();

    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Mohon tambahkan nama mata kuliah");
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePublished() {
        if (inputPublished.getText().toString().trim().isEmpty()) {
            inputLayoutPublished.setError("Mohon tambahkan nama tutor");
            requestFocus(inputPublished);
            return false;
        } else {
            inputLayoutPublished.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTicket() {
        if (inputTicket.getText().toString().trim().isEmpty()) {
            inputLayoutTicket.setError("Mohon tambahkan harga");
            requestFocus(inputTicket);
            return false;
        } else {
            inputLayoutTicket.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDate() {
        if (inputDate.getText().toString().trim().isEmpty()) {
            inputLayoutDate.setError("Mohon tambahkan tanggal");
            requestFocus(inputDate);
            return false;
        } else {
            inputLayoutDate.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDesc() {
        if (inputDesc.getText().toString().trim().isEmpty()) {
            inputLayoutDesc.setError("Mohon tambahkan Deskripsi");
            requestFocus(inputDesc);
            return false;
        } else {
            inputLayoutDesc.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLocation() {
        if (inputLocation.getText().toString().trim().isEmpty()) {
            inputLayoutLocation.setError("Mohon tambahkan lokasi");
            requestFocus(inputLocation);
            return false;
        } else {
            inputLayoutLocation.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTime() {
        if (inputTime.getText().toString().trim().isEmpty()) {
            inputLayoutTime.setError("Mohon tambahkan waktu");
            requestFocus(inputTime);
            return false;
        } else {
            inputLayoutLocation.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_published:
                    validatePublished();
                    break;
                case R.id.input_date:
                    validateDate();
                    break;
                case R.id.input_ticket:
                    validateTicket();
                    break;
                case R.id.input_desc:
                    validateDesc();
                    break;
                case R.id.input_location:
                    validateLocation();
                    break;
                case R.id.input_time:
                    validateTime();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}