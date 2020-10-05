package com.jiten.ut.client;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import edu.jiten.uber.cabservice.CabReserve;
import edu.jiten.uber.cabservice.CabServiceImplService;
import edu.jiten.uber.cabservice.CabServiceRequest;
import edu.jiten.uber.cabservice.CarServiceResponse;
import edu.jiten.uber.cabservice.ICabService;
import edu.jiten.uber.cabservice.Ride;
import edu.jiten.uber.cabservice.ServiceException_Exception;


public class CabJavaSoapUtClientApplication {

	public static void main(String[] args) {
		
		try {
			
			CabServiceImplService srvc = new CabServiceImplService(
					new URL("http://localhost:8080/SoapCabSrvc/cabService?wsdl"));
			
			ICabService cabSrvcPort = srvc.getCabServiceImplPort();
			
			
			Client client =  ClientProxy.getClient(cabSrvcPort);
			Endpoint endpoint = client.getEndpoint();
			
			 Map<String, Object> props = new HashMap<>();
			 props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			 props.put(WSHandlerConstants.USER, "test-user");
			 props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			 props.put(WSHandlerConstants.PW_CALLBACK_CLASS, PwCallBackHandler.class.getName());
			 WSS4JOutInterceptor wss4jInterceptor = new WSS4JOutInterceptor(props );
			 
			endpoint.getOutInterceptors().add(wss4jInterceptor );
			
			
			CabServiceRequest cabServiceRequest= new CabServiceRequest();
			
			CabReserve reserveValue = new CabReserve();
			reserveValue.setCancle(false);
			cabServiceRequest.setCabReserve(reserveValue);
			
			Ride ride = new Ride();
			ride.setId(BigInteger.valueOf(1));
			ride.setFrom("123 Origin Dr");
			ride.setTo("456 Destination Dr");
			ride.setCost(50.0);
			cabServiceRequest.setRide(ride); 
			
			CarServiceResponse cabServiceResponse = cabSrvcPort.requestCab(cabServiceRequest);
			
			System.out.println("\nWas the Cab Service Request registered? --> "+cabServiceResponse.isProcessed());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException_Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		
	}

}
