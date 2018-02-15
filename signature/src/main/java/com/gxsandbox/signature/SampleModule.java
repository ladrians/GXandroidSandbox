package com.gxsandbox.signature;

import android.content.Context;

import com.artech.externalapi.ExternalApiDefinition;
import com.artech.externalapi.ExternalApiFactory;
import com.artech.framework.GenexusModule;
import com.artech.usercontrols.UcFactory;
import com.artech.usercontrols.UserControlDefinition;

public class SampleModule implements GenexusModule {

	@Override
	public void initialize(Context context) {

		UserControlDefinition basicUserControl = new UserControlDefinition(
				SignatureUserControl.NAME,
				SignatureUserControl.class
		);
		UcFactory.addControl(basicUserControl);

		/*UserControlDefinition basicUserControl2 = new UserControlDefinition(
				SignatureWrapper.NAME,
				SignatureWrapper.class
		);
		UcFactory.addControl(basicUserControl2);*/

	}
}
