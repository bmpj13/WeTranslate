package server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import utils.Exchanges;

public class InsertTranslation extends NodeHandler implements HttpHandler {

	public InsertTranslation(String[] requiredParams) {
		super(requiredParams);
	}

	@Override
	public void handle(HttpExchange exch) throws IOException {
		Map<String, String> params = Exchanges.queryToMap(exch.getRequestURI().getQuery());
		if (!requestAcceptable(params)) {
			Exchanges.writeResponse(exch, HttpURLConnection.HTTP_BAD_REQUEST, "Parameters do not match.");
			return;
		}
		
		
		String response = "Email: " + params.get("email") + " & " + "RequestID: "  + params.get("requestid")
		+ " & " + "Text: "  + params.get("text");
		System.out.println("Insert: " + response);
		
		Exchanges.writeResponse(exch, HttpURLConnection.HTTP_OK, response);
	}
}
