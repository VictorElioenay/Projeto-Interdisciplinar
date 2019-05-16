package main_memory;

import java.net.URI;

public class Main_Memory {
	
	private URI file;
	
	public Main_Memory(URI url) {
		this.file = url;
	}
	
	public URI getFile() {
		return file;
	}
	
}
