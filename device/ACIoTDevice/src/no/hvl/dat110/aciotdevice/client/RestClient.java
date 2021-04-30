package no.hvl.dat110.aciotdevice.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.io.IOException;

public class RestClient {



	public RestClient() {
		// TODO Auto-generated constructor stub
	}
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private static String logpath = "/accessdevice/log/";

	public void doPostAccessEntry(String message) {

		OkHttpClient client = new OkHttpClient();

		AccessMessage accessMessage = new AccessMessage(message);


		Gson gson = new GsonBuilder().create();

		String json = gson.toJson(accessMessage);

		RequestBody body = RequestBody.create(JSON, json);


		Request request = new Request.Builder().url("http://localhost:8080"+logpath).post(body).build();

		System.out.println(request.toString());

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {
		AccessCode accessCode = null;
		try{
			Gson gson = new GsonBuilder().create();

			OkHttpClient client = new OkHttpClient();

			String url = "http://localhost:8080"+codepath;

			Request request = new Request.Builder()
					.url(url)
					.build();

			try (Response response = client.newCall(request).execute()) {
				System.out.println (response.body().string());
				accessCode = gson.fromJson(response.body().string(), AccessCode.class);
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e){
			System.out.println(e);
		}
		System.out.println(accessCode);
		return accessCode;
	}
}
