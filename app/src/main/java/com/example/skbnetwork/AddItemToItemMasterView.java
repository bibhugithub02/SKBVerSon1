package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;

public class AddItemToItemMasterView<onActivityResult> extends AppCompatActivity {

    ImageView imageView;
    EditText multiLineText;
    ImageButton imageButton;
    TextView addButton, clearButton;

    Uri localImageUri, firebaseUri;
    InputStream inputStream;
    byte[] data1;
    ProgressDialog dialog;
    FirebaseStorage storage;
    StorageReference uploader;
    String timeStamp;
    String logoFlag = "Y";
    String ErrorFlag = "N";
    String skblogo, multiLineText01;


    final int TAKE_PICTURE = 1;
    final int ACTIVITY_SELECT_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_item_master_view);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imageView = findViewById(R.id.imageView);
        multiLineText = findViewById(R.id.editTextTextMultiLine);
        imageButton = findViewById(R.id.imageButton);
        addButton = findViewById(R.id.textView7);
        clearButton = findViewById(R.id.textView8);
        skblogo = "https://firebasestorage.googleapis.com/v0/b/skb-network.appspot.com/o/SKB.jpg?alt=media&token=c02bde83-c922-47f5-9f08-c21d3640836d";
        Glide.with(AddItemToItemMasterView.this).load(skblogo).into(imageView);
        logoFlag = "Y";
             //   load("https://firebasestorage.googleapis.com/v0/b/skb-network.appspot.com/o/SKB.jpg?alt=media&token=c02bde83-c922-47f5-9f08-c21d3640836d").into(imageView);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              selectImage();
              //  selectImageFromStorage();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Validate the input fields
                validateInputFields();
            // Upload Image to FireStore

                if((logoFlag == "N") && (ErrorFlag == "N")) {
                    uploadImageToFireStore();
                    logoFlag = "Y";
                }

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiLineText.setError(null);
                multiLineText.setText("");
                Glide.with(AddItemToItemMasterView.this).load(skblogo).into(imageView);
                logoFlag = "Y";
            }
        });

    }

    //Validate all the inputs fields to ensure we are good before upload
    private void validateInputFields() {

        if (TextUtils.isEmpty(multiLineText.getText().toString())){

            multiLineText.setError("Enter a valid Item Category");
            ErrorFlag = "Y";
            multiLineText.requestFocus();

        }else{
            ErrorFlag = "N";
            multiLineText01= multiLineText.getText().toString().toUpperCase().trim().replace(' ','_');
            multiLineText.setText(multiLineText01);
        };



        if(logoFlag =="Y"){

            Toast.makeText(this, "Error!!! - Item Picture is missing", Toast.LENGTH_LONG).show();
        }

    }

    //Build and upload the file to firestore
    private void uploadImageToFireStore() {

        // Create an image file name
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String imageFileName = "JPEG_" + timeStamp + "_" + ps1;
        String imageFileName =  multiLineText01.trim() +"_SKB_" + "JPEG" ;

        dialog = new ProgressDialog(this);
        dialog.setTitle("Uploading the File/ Please wait");
        dialog.show();

        storage = FirebaseStorage.getInstance();
        uploader = storage.getReference(imageFileName);

        try {
            uploadingImageToFirebaseStorageUsingInputStream();

        }catch (Exception ex){
            Toast.makeText(this, "Error!! " + ex.getMessage() + " //  Image Missing  // ", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

    }
    // Code to upload the image to the FireStore ( Get the access (Connect FireBase+ FireStore) +
    // change the rule then only this works)
    private void uploadingImageToFirebaseStorageUsingInputStream() {

        uploader.putBytes(data1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        Toast.makeText(AddItemToItemMasterView.this, "Uploaded Image file URi is: " + uri, Toast.LENGTH_SHORT).show();
                        firebaseUri = uri;
                       // writeToDataBaseWithUri();
                        writeToDataBaseWithUri();
                        try {
                            writeToDataBaseWithUri();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        multiLineText.setText("");
                        Glide.with(AddItemToItemMasterView.this).load(skblogo).into(imageView);
                        dialog.dismiss();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                dialog.setMessage("Uploader : " + (int)percent + " % ");
            }
        });
    }

    private void writeToDataBaseWithUri() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference("dModelItemMaster");

        String dtMIMItemName = multiLineText01.toString().toUpperCase().trim().replace(" ", "_");
        String dtMIMItemUrl= firebaseUri.toString();
        String dtMIMEnterDate= timeStamp;
        String dtMIMAddedBy = "SKB";

        ModelItemMaster obj = new ModelItemMaster(dtMIMItemName,dtMIMItemUrl,dtMIMEnterDate,dtMIMAddedBy);
        db.child(multiLineText01.trim() +"_SKB_" + "JPEG").setValue(obj);

        //Write to monitoring DB ModelForMonitoring

        ModelForMonitoring m = new ModelForMonitoring();
        m.writeToDB(timeStamp,"AddItemToItemMasterView",dtMIMItemName,
                "ModelItemMaster","Add Record",
                dtMIMItemUrl);


    }

    // Take picture using Camera
    private void takePicture(){
        Dexter.withContext(AddItemToItemMasterView.this).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, TAKE_PICTURE);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

    }
    // Take picture from the album
    private void selectImageFromStorage() {

        Dexter.withContext(AddItemToItemMasterView.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                         Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"),101);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }
    //Dialog to choose whether to use Camera or Album to get the picture or Cancel and go back
    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(AddItemToItemMasterView.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options,new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(options[which].equals("Take Photo"))
                {
                    takePicture();

                }
                else if(options[which].equals("Choose from Gallery"))
                {
                    selectImageFromStorage();

                }
                else if(options[which].equals("Cancel"))
                {
                    dialog.dismiss();
                }

            }
        });
        builder.show();

    }

    // On Activity result for taking Picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //For taking picture using camera

        if((requestCode == TAKE_PICTURE) && (resultCode == RESULT_OK)) {
            //localImageUri = data.getData();
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
                logoFlag ="N";
                //imageView.setImageBitmap(Bitmap.createScaledBitmap(photo,300,300,true));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 30, baos);
                data1 = baos.toByteArray();
            }catch (Exception ex)
            {
                Toast.makeText(this, "Error!!" + ex, Toast.LENGTH_SHORT).show();
            }

        }

        //For Selecting picture from the album/storage area
        if((requestCode == 101) && (resultCode == RESULT_OK)) {
            localImageUri = data.getData();
            //profileImage.setImageURI(Uri);  / This line also works

            // Another way we can reduce the size of the bitmap , Convert that to byte ans upload

            String filename = data.getData().getPath();
            try{
                inputStream = getContentResolver().openInputStream(localImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);
                logoFlag ="N";
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                data1 = baos.toByteArray();

            }catch (Exception ex)
            {
                Toast.makeText(this, "Error!!" + ex, Toast.LENGTH_SHORT).show();
            }

        }
    }
}