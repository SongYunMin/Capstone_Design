package com.example.capstone_design;

//import android.hardware.biometrics.BiometricPrompt;

/*
 *
 *       생체인증(지문, 홍채 ...) 을 위한 BioFactory.java
 *
 */

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FingerBioFactory {
    private AppCompatActivity activity;
    private BiometricPrompt.AuthenticationCallback callback;
    private BiometricPrompt myBiometricPrompt;
    private BiometricPrompt.PromptInfo biPromptInfo;

    public FingerBioFactory(AppCompatActivity activity, BiometricPrompt.AuthenticationCallback callback) {
        this.activity = activity;
        this.callback = callback;
        setting();
    }

    /**
     * Setting
     ************************************************************************************************************************************************/
    private void setting() {
        Executor newExecutor = Executors.newSingleThreadExecutor();
        biPromptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(activity.getString(R.string.str_finger_print))
                .setSubtitle(activity.getString(R.string.msg_subtitile_finger_print))
                .setDescription(activity.getString(R.string.msg_btn_finger_print))
                .setNegativeButtonText(activity.getString(R.string.str_cancle))
                .build();

        myBiometricPrompt = new BiometricPrompt(activity, newExecutor, callback);
    }

    /**
     * Shows the biometric prompt
     ************************************************************************************************************************************************/
    public void authenticate() {
        myBiometricPrompt.authenticate(biPromptInfo);
    }
}
