package com.gxsandbox.signature;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.artech.base.controls.IGxControlRuntime;
import com.artech.base.metadata.layout.LayoutItemDefinition;
import com.artech.ui.Coordinator;
import com.artech.usercontrols.IGxUserControl;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignatureWrapper extends LinearLayout implements IGxUserControl {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    final static String NAME = "SDSignature2";

    private String mName;
    private String mLanguage;
    private String mTheme;
    private static final String CONTROL_ID = "@SDSignature";
    private static final String PROPERTY_THEME = "Theme";

    private Coordinator mCoordinator;
    private Context mContext;
    private LayoutItemDefinition mLayoutDefinition;

    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;

    public SignatureWrapper(Context context, Coordinator coordinator, LayoutItemDefinition def) {
        super(context);
        mCoordinator = coordinator;
        setLayoutDefinition(def);
    }

    public SignatureWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setLayoutDefinition(LayoutItemDefinition layoutItemDefinition) {
        mLayoutDefinition = layoutItemDefinition;
    }

    private void initView() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.signature, this);

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(mContext, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
            }
        });
    }
}
