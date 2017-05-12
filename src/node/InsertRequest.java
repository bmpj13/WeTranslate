package node;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import database.Database;
import utils.Exchanges;

public class InsertRequest extends NodeHandler implements HttpHandler {

	public InsertRequest(String[] requiredParams) {
		super(requiredParams);
	}

	@Override
	public void handle(HttpExchange exch) throws IOException {
		Map<String, String> params = Exchanges.queryToMap(exch.getRequestURI().getQuery());
		if (!requestAcceptable(params)) {
			Exchanges.writeResponse(exch, HttpURLConnection.HTTP_BAD_REQUEST, "Parameters do not match.");
			return;
		}
		
		String email = params.get("email");
		String from = params.get("from");
		String to = params.get("to");
		String text = params.get("text");
		
		if (Database.insertRequest(email, from, to, text))
			Exchanges.writeResponse(exch, HttpURLConnection.HTTP_OK, "Inserted request");
		else
			Exchanges.writeResponse(exch, HttpURLConnection.HTTP_CONFLICT, "Error inserting request");
	}

}