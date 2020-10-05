package com.jiten.ut.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PwCallBackHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		for (Callback callback : callbacks) {
			
			WSPasswordCallback wsPwdCallBack = (WSPasswordCallback) callback;
			
			if(wsPwdCallBack.getIdentifier().equals("test-user")) {
				wsPwdCallBack.setPassword("dummy-pwd");
				return;
			}
			
		}
	}

}
