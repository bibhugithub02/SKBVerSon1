package com.example.skbnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginScreenOne extends AppCompatActivity {

    TextView getOtp ;
    EditText phNumber;
    String ph;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_one);

        //Set the orientation to Portrait for this screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getOtp = findViewById(R.id.textView99);
        phNumber = findViewById(R.id.editTextNumber18);

        mAuth = FirebaseAuth.getInstance();
        phNumber.requestFocus();



        ProgressBar progressBar = findViewById(R.id.progress_bar_sendingotp);
        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!phNumber.getText().toString().trim().isEmpty()) {
                    if ((phNumber.getText().toString().trim()).length() == 10) {
                        ph = "+91"+ phNumber.getText().toString().trim();
                       // ph = phNumber.getText().toString().trim();
                        progressBar.setVisibility(View.VISIBLE);
                        getOtp.setVisibility(View.INVISIBLE);
                        initiateOtp();
//                        Intent i = new Intent(LoginScreenOne.this, OtpReceived.class);
//                        i.putExtra("mobile", ph);
//                        //i.putExtra("otp", backendOtp);
//                        startActivity(i);
                    }else{
                        Toast.makeText(LoginScreenOne.this, "Enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginScreenOne.this, "Phone Number cannot be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mAuth.setLanguageCode("En");
        // To apply the default app language instead of explicitly setting it.
        // auth.useAppLanguage();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            private static final String TAG = "Bibhu" ;

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                progressBar.setVisibility(View.GONE);
                getOtp.setVisibility(View.VISIBLE);

                signInWithPhoneAuthCredential(credential);
            }

            private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(LoginScreenOne.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithCredential:success");

                                    FirebaseUser user = task.getResult().getUser();
                                    // Update UI
                                } else {
                                    // Sign in failed, display a message and update the UI
                                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        // The verification code entered was invalid
                                    }
                                }
                            }
                        });
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                progressBar.setVisibility(View.INVISIBLE);
                getOtp.setVisibility(View.VISIBLE);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request , for Example : If the phone number i snot correct , it will hit this part of the code
                    Toast.makeText(LoginScreenOne.this, "Error!!:" + e.getMessage(), Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(LoginScreenOne.this, "SMS quota for the project has been exceeded", Toast.LENGTH_LONG).show();
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                String mVerificationId = verificationId;
                PhoneAuthProvider.ForceResendingToken mResendToken = token;

                progressBar.setVisibility(View.GONE);
                getOtp.setVisibility(View.VISIBLE);

                    Intent i = new Intent(LoginScreenOne.this, OtpReceived.class);
                    i.putExtra("mobile", ph);
                    i.putExtra("otp", mVerificationId);
                    startActivity(i);

            }
        };

    }

    private void initiateOtp() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(ph)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}