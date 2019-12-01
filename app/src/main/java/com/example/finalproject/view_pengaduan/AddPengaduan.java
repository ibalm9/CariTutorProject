package com.example.finalproject.view_pengaduan;



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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.Adapter.RequestHandler;
import com.example.finalproject.entity.BaseActivity;
import com.example.finalproject.network.ConfigPengaduan;
import com.example.finalproject.view_pengaduan.AddPengaduan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class AddPengaduan extends BaseActivity {

    private EditText inputName, inputPublished, inputDate, inputTicket, inputDesc, inputLocation;
    private TextInputLayout inputLayoutName,inputLayoutPublished, inputLayoutDate, inputLayoutTicket, inputLayoutDesc, inputLayoutLocation;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap bitmap;
    private Uri filePath;
    private RadioGroup radioGroupNb;
    private RadioButton radioButtonNb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enterFromBottomAnimation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pengaduan_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.LTGRAY);
        radioGroupNb = (RadioGroup) findViewById(R.id.radioGroupNb);
        toolbar.setTitle("Tentang Tutor");

        imageView = (ImageView) findViewById(R.id.imageViewPengaduan);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name2);
        inputLayoutPublished = (TextInputLayout) findViewById(R.id.input_layout_published2);
        inputLayoutDate = (TextInputLayout) findViewById(R.id.input_layout_date2);
        inputLayoutTicket = (TextInputLayout) findViewById(R.id.input_layout_ticket2);
        inputLayoutDesc = (TextInputLayout) findViewById(R.id.input_layout_desc2);
        inputLayoutLocation = (TextInputLayout) findViewById(R.id.input_layout_location2);

        inputPublished = (EditText) findViewById(R.id.input_published2);
        inputDate = (EditText) findViewById(R.id.input_date2);
        inputTicket = (EditText) findViewById(R.id.input_ticket2);
        inputDesc = (EditText) findViewById(R.id.input_desc2);
        inputLocation = (EditText) findViewById(R.id.input_location2);





        inputPublished.addTextChangedListener(new AddPengaduan.MyTextWatcher(inputPublished));
        inputDate.addTextChangedListener(new AddPengaduan.MyTextWatcher(inputDate));
        inputTicket.addTextChangedListener(new AddPengaduan.MyTextWatcher(inputTicket));
        inputDesc.addTextChangedListener(new AddPengaduan.MyTextWatcher(inputDesc));

        FloatingActionButton fabFile = (FloatingActionButton) findViewById(R.id.fabFile2);
        fabFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        FloatingActionButton fabSimpan = (FloatingActionButton) findViewById(R.id.fabSimpan2);
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
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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



        final String name = radioButtonNb.getText().toString().trim();
        final String pbs = inputPublished.getText().toString().trim();
        final String date = "Tersubmit";
        final String location = inputLocation.getText().toString().trim();
        final String description = inputDesc.getText().toString().trim();
        final String ticket = inputTicket.getText().toString().trim();


        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddPengaduan.this, "Tunggu Sebentar...", null,true,true);
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

                data.put(ConfigPengaduan.TAG_NAME, name);
                data.put(ConfigPengaduan.TAG_PUBLISHER, pbs);
                data.put(ConfigPengaduan.TAG_DATE, date);
                data.put(ConfigPengaduan.TAG_LOCATION, location);
                data.put(ConfigPengaduan.TAG_TICKET,ticket);
                data.put(ConfigPengaduan.TAG_DESCRIPTION, description);
                data.put(ConfigPengaduan.UPLOAD_KEY, uploadImage);

                String result = rh.sendPostRequest(ConfigPengaduan.UPLOAD_URL,data);

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

        int selectedId = radioGroupNb.getCheckedRadioButtonId();

        // mencari radio button
        radioButtonNb = (RadioButton) findViewById(selectedId);

        if (!validateName()) {
            return;
        }

        if (!validatePublished()) {
            return;
        }

        if (!validateDesc()) {
            return;
        }



        uploadImage();

    }

    private boolean validateName() {
        if (radioButtonNb.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(radioButtonNb);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePublished() {
        if (inputPublished.getText().toString().trim().isEmpty()) {
            inputLayoutPublished.setError("Mohon tambahkan judul pengaduan");
            requestFocus(inputPublished);
            return false;
        } else {
            inputLayoutPublished.setErrorEnabled(false);
        }

        return true;
    }

    /*private boolean validateTicket() {
        if (inputTicket.getText().toString().trim().isEmpty()) {
            inputLayoutTicket.setError(getString(R.string.err_msg_name));
            requestFocus(inputTicket);
            return false;
        } else {
            inputLayoutTicket.setErrorEnabled(false);
        }

        return true;
    }*/

   /* private boolean validateDate() {
        if (inputDate.getText().toString().trim().isEmpty()) {
            inputLayoutDate.setError(getString(R.string.err_msg_name));
            requestFocus(inputDate);
            return false;
        } else {
            inputLayoutDate.setErrorEnabled(false);
        }

        return true;
    }*/

    private boolean validateDesc() {
        if (inputDesc.getText().toString().trim().isEmpty()) {
            inputLayoutDesc.setError("Mohon tambahkan deskripsi pengaduan");
            requestFocus(inputDesc);
            return false;
        } else {
            inputLayoutDesc.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLocation() {
        if (inputLocation.getText().toString().trim().isEmpty()) {
            inputLayoutLocation.setError(getString(R.string.err_msg_name));
            requestFocus(inputLocation);
            return false;
        } else {
            inputLayoutLocation.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
                case R.id.input_name2:
                    validateName();
                    break;
                case R.id.input_published2:
                    validatePublished();
                    break;
                case R.id.input_desc2:
                    validateDesc();
                    break;
                case R.id.input_location2:
                    validateLocation();
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
