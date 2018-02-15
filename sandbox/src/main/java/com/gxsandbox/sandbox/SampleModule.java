package com.gxsandbox.sandbox;

import android.content.Context;

import com.artech.externalapi.ExternalApiDefinition;
import com.artech.externalapi.ExternalApiFactory;
import com.artech.framework.GenexusModule;

public class SampleModule implements GenexusModule {

	@Override
	public void initialize(Context context) {

        ExternalApiDefinition batterySample = new ExternalApiDefinition(
                BatteryWrapper.NAME,
                BatteryWrapper.class
        );
        ExternalApiFactory.addApi(batterySample);
	}
}
