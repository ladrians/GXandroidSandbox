package com.gxsandbox.signature;

import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artech.base.controls.IGxControlRuntime;
import com.artech.base.metadata.ActionDefinition;
import com.artech.base.metadata.ActionParameter;
import com.artech.base.metadata.layout.LayoutItemDefinition;
import com.artech.base.services.Services;
import com.artech.controllers.ViewData;
import com.artech.controls.IGridView;
import com.artech.controls.IGxEdit;
import com.artech.controls.grids.GridHelper;
import com.artech.ui.Coordinator;
import com.artech.usercontrols.IGxUserControl;
import com.github.gcacace.signaturepad.views.SignaturePad;

@SuppressLint("ViewConstructor")
public class SignatureUserControl extends SignaturePad implements IGxEdit {
	final static String NAME = "SDSignature";
	private Coordinator mCoordinator;

	private String mLanguage;
	private String mTheme;
	private static final String CONTROL_ID = "@SDSignature";
	private static final String PROPERTY_THEME = "Theme";

	private Context mContext;
	private LayoutItemDefinition mLayoutDefinition;

	public SignatureUserControl(Context context, Coordinator coordinator, LayoutItemDefinition definition) {
		super(context, null);
		setBackgroundColor(Color.WHITE);
		mCoordinator = coordinator;
		setOnClickListener(mOnClickListener);
		Services.Log.debug(NAME, "SignatureUserControl constructor");

		mContext = context;
		mLayoutDefinition = definition;
		initializeControl();
	}

	private void initializeControl() {
		initializeProperties();

		this.setOnSignedListener(new SignaturePad.OnSignedListener() {
			@Override
			public void onStartSigning() {
				Toast.makeText(mContext, "OnStartSigning", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSigned() {
				Toast.makeText(mContext, "onSigned", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onClear() {
				Toast.makeText(mContext, "onClear", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initializeProperties() {
		mLanguage = Services.Resources.getCurrentLanguage();
		mTheme = getDefinitionProperty(PROPERTY_THEME);
	}

	private String getDefinitionProperty(String propertyName) {
		return mLayoutDefinition.getControlInfo().optStringProperty(CONTROL_ID + propertyName);
	}

	private final View.OnClickListener mOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(mContext, "OnClickListener", Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public String getGx_Value() {
		return null;
	}

	@Override
	public void setGx_Value(String value) {

	}

	@Override
	public String getGx_Tag() {
		return null;
	}

	@Override
	public void setGx_Tag(String tag) {

	}

	@Override
	public void setValueFromIntent(Intent data) {

	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public IGxEdit getViewControl() {
		return this;
	}

	@Override
	public IGxEdit getEditControl() {
		return this;
	}
}