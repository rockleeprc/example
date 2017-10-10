package k17.util;

public class HttpRespons {

	public String urlString;
	public int defaultPort;
	public String file;
	public String host;
	public String path;
	public int port;
	public String protocol;
	public String query;
	public String ref;
	public String userInfo;
	public String content;
	public String contentEncoding;
	public int code;
	public String message;
	public String contentType;
	public String method;
	public int connectTimeout;
	public int readTimeout;
	@Override
	public String toString() {
		return "HttpRespons [urlString=" + urlString + ", defaultPort=" + defaultPort + ", file=" + file + ", host="
				+ host + ", path=" + path + ", port=" + port + ", protocol=" + protocol + ", query=" + query + ", ref="
				+ ref + ", userInfo=" + userInfo + ", content=" + ", contentEncoding=" + contentEncoding
				+ ", code=" + code + ", message=" + message + ", contentType=" + contentType + ", method=" + method
				+ ", connectTimeout=" + connectTimeout + ", readTimeout=" + readTimeout + "]";
	}
	
	

}
