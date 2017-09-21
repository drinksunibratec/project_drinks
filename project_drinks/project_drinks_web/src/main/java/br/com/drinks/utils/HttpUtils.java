package br.com.drinks.utils;
import org.springframework.http.HttpHeaders;

public abstract class HttpUtils {

	public static HttpHeaders getHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, OPTIONS, POST");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		
		return headers;
	}
}