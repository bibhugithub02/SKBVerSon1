package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class DemandItemStatusAtSite extends AppCompatActivity {


    String menuName, clientName, siteName, workType;
    TextView generateReport, clientNamev, siteNamev, workTypev;
    int pageWidth =200;
    MyAdopterForDemandItemStatusAtSite myAdopterForDemandItemStatusAtSite;
    RecyclerView recyclerView;
    Query query;
    String searchkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_item_status_at_site);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">"
                + getString(R.string.app_name) + "</font>"));

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Parameter used when called from Site Menu Option
        //Site menu then Choose the Site then Choose the Item Category and then Item Sub Category to add the Qty
        menuName = getIntent().getStringExtra("menuname").toString();
        clientName = getIntent().getStringExtra("clientname").toString();
        siteName = getIntent().getStringExtra("sitename").toString();
        workType = getIntent().getStringExtra("worktype").toString();

        generateReport = findViewById(R.id.textView61);
        clientNamev = findViewById(R.id.textView58);
        siteNamev = findViewById(R.id.textView59);
        workTypev = findViewById(R.id.textView60);

        if (menuName.equals("SIHR")){
            generateReport.setVisibility(View.VISIBLE);
        }

        //menuNamev.setText("Site Menu");
        clientNamev.setText("Client   : "+ clientName);
        siteNamev.setText("Site      : " +siteName);
        workTypev.setText("Work Type : "+workType);
        searchkey = clientName.toString().trim() +"_"+siteName.toString().trim()+"_"+workType.toString().trim();
        recyclerView=findViewById(R.id.recyclerView06);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                   // Toast.makeText(DemandItemStatusAtSite.this, "NO Record to Display", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(DemandItemStatusAtSite.this, "No Record to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseRecyclerOptions<ModelClientSiteWorkTypeItemSubItemQuantityMaster> options =
                new FirebaseRecyclerOptions.Builder<ModelClientSiteWorkTypeItemSubItemQuantityMaster>()
                        .setQuery(query, ModelClientSiteWorkTypeItemSubItemQuantityMaster.class)
                        .build();

        myAdopterForDemandItemStatusAtSite = new MyAdopterForDemandItemStatusAtSite(options, menuName);
        recyclerView.setAdapter(myAdopterForDemandItemStatusAtSite);

        //This part of the code generate the reports for STOCK IN HAND
        //*************************************************************
        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    generatePDFReportiText();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //generatePDFReport(); // Code not working
               // Toast.makeText(DemandItemStatusAtSite.this, "Button is working", Toast.LENGTH_SHORT).show();
            }

            private void generatePDFReportiText() throws FileNotFoundException {

                ActivityCompat.requestPermissions(DemandItemStatusAtSite.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);

                String pathName =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                String fname = searchkey + ".pdf";
                File file = new File(pathName, fname);
                OutputStream outputStream = new FileOutputStream(file);

                PdfWriter writer = new PdfWriter(file);
                com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
                Document document = new Document(pdfDocument);
                String ClientSiteWorkType = searchkey;
                Paragraph paragraph = new Paragraph("SKB Stock Report For : ");
                Paragraph paragraph1 = new Paragraph(ClientSiteWorkType).setBold();

//                Text textBold = new Text("Bold ").setBold();
//                Text textItalic = new Text("Italic ").setItalic();
//                Text textUnderline = new Text("Underline ").setUnderline();

                query = FirebaseDatabase.getInstance().getReference().child("dModelClientSiteWorkTypeItemSubItemQuantityMaster")
                        .orderByChild("dMCSWTISIQMSearchKey1").equalTo(searchkey);



                float columnwidth[] = {400f,100f,100f,100f,100f,100f};
                Table tableHeader = new Table(columnwidth);
                Table tabledetails = new Table(columnwidth);

               // tableHeader.addCell("Item Description")
                tableHeader.addCell(new Cell().add(new Paragraph("Item Description")).setBackgroundColor(ColorConstants.YELLOW));
                tableHeader.addCell(new Cell().add(new Paragraph("Demand")).setBackgroundColor(ColorConstants.YELLOW));
                tableHeader.addCell(new Cell().add(new Paragraph("Purchased")).setBackgroundColor(ColorConstants.YELLOW));
                tableHeader.addCell(new Cell().add(new Paragraph("Pending")).setBackgroundColor(ColorConstants.YELLOW));
                tableHeader.addCell(new Cell().add(new Paragraph("Used")).setBackgroundColor(ColorConstants.YELLOW));
                tableHeader.addCell(new Cell().add(new Paragraph("SIH")).setBackgroundColor(ColorConstants.YELLOW));
//                tableHeader.addCell("Demand");
//                tableHeader.addCell("Purchased");
//                tableHeader.addCell("Pending");
//                tableHeader.addCell("Used");
//                tableHeader.addCell("SIH");


                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int i=0,J=0;
                        if(snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()){

                                tableHeader.addCell(ds.child("dMCSWTISIQMItemCategory").getValue().toString());
                                int Dmd = Integer.parseInt(ds.child("totalDemand").getValue().toString());
                                tableHeader.addCell(ds.child("totalDemand").getValue().toString());
                                tableHeader.addCell(ds.child("totalReceived").getValue().toString());
                                tableHeader.addCell(String.valueOf(Integer.parseInt(ds.child("totalDemand").getValue().toString())-
                                        Integer.parseInt(ds.child("totalReceived").getValue().toString())));
                                tableHeader.addCell(String.valueOf(Integer.parseInt(ds.child("totalReceived").getValue().toString())-
                                        Integer.parseInt(ds.child("stokeInHand").getValue().toString())));
                                tableHeader.addCell(ds.child("stokeInHand").getValue().toString());
                                i = i+1;
                                J = J+1;

                                if((i==31 && i == J) || (i==33 && i != J)){
                                    tableHeader.addCell(new Cell().add(new Paragraph("Item Description")).setBackgroundColor(ColorConstants.YELLOW));
                                    tableHeader.addCell(new Cell().add(new Paragraph("Demand")).setBackgroundColor(ColorConstants.YELLOW));
                                    tableHeader.addCell(new Cell().add(new Paragraph("Purchased")).setBackgroundColor(ColorConstants.YELLOW));
                                    tableHeader.addCell(new Cell().add(new Paragraph("Pending")).setBackgroundColor(ColorConstants.YELLOW));
                                    tableHeader.addCell(new Cell().add(new Paragraph("Used")).setBackgroundColor(ColorConstants.YELLOW));
                                    tableHeader.addCell(new Cell().add(new Paragraph("SIH")).setBackgroundColor(ColorConstants.YELLOW));
                                    i=0;
                                }

                            }

                            document.add(tableHeader);
                            document.close();
                        }else{
                            Toast.makeText(DemandItemStatusAtSite.this, "Nothing to write", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                document.add(paragraph);
                document.add(paragraph1);
               // document.add(tableHeader);
              //  document.add(tabledetails);

              //  document.close();
                Toast.makeText(DemandItemStatusAtSite.this, "Pdf Created", Toast.LENGTH_SHORT).show();


            }


            // Below Code not working
            private void generatePDFReport() {

                //https://stackoverflow.com/questions/58426604/how-to-generate-pdf-from-jsonobject-in-android

                ActivityCompat.requestPermissions(DemandItemStatusAtSite.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},PackageManager.PERMISSION_GRANTED);


                PdfDocument myPdfDocument = new PdfDocument();
                PdfDocument.PageInfo myPageInfo1 =
                        new PdfDocument.PageInfo.Builder(250,400,1).create();
                int i = 0 ;
                for(i=0;i<5;i++) {
                    String myPage = "myPage";
                    String mypagefinal = myPage + i;
                    Toast.makeText(DemandItemStatusAtSite.this, "***" + mypagefinal, Toast.LENGTH_SHORT).show();
                }
                PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);


                Paint myTitlePaint = new Paint();
                myTitlePaint.setTextAlign(Paint.Align.CENTER);
                myTitlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                myTitlePaint.setTextSize(26);

                Canvas canvas = myPage1.getCanvas();

                canvas.drawText("Stock Report for SKB Network", pageWidth/2, 15, myTitlePaint);
                myPdfDocument.finishPage(myPage1);

                File file = new File(Environment.getExternalStorageDirectory(),"/SKBReport1.pdf");
                Toast.makeText(DemandItemStatusAtSite.this, "" + file , Toast.LENGTH_SHORT).show();
                try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                }catch (IOException e){
                    e.printStackTrace();
                }
                myPdfDocument.close();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdopterForDemandItemStatusAtSite.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //myAdopterForDemandItemStatusAtSite.startListening();
    }


}