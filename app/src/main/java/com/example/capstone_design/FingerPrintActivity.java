package com.example.capstone_design;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FingerPrintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FingerBioFactory fingerBioFactory1 = new FingerBioFactory
                (this, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent intent = new Intent(getApplicationContext(),NFCActivity.class);
                startActivity(intent);
            }
        });
        fingerBioFactory1.authenticate();
    }
}
