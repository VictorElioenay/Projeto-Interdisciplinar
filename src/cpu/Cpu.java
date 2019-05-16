package cpu;

import java.net.URI;

public class Cpu {
	
	private URI file;
	
	public Cpu(URI uri) {
		this.file = uri;
	}

	public URI getFile() {
		return file;
	}
}
