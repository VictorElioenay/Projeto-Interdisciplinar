package cache;

import java.util.HashMap;
import java.util.Map;

public class DirectMap {

//	private String[] tag;
	
	private Map<String,String[]> cache = new HashMap<>();
	private Map<String,String> tags = new HashMap<>();
	private StringBuilder stringbuilder = new StringBuilder();

	private int bitsLinha;
	private int bitsBloco;
	private int bitsTag;
	
	public DirectMap(int end, int linhas, int nBlocos) {
		
		bitsLinha = bits(linhas);
		bitsBloco = bits(nBlocos);
		bitsTag = end - bitsLinha - bitsBloco;
		
		/*for (int i = 0; i < linhas; i++) {
			String aux = Integer.toBinaryString(i);
			for (int b = 0; b < bitsLinha - aux.length(); b++) {
				stringbuilder.append(0);
			}
			cache.put(stringbuilder.append(aux).toString(), null);
			tags.put(stringbuilder.toString(), null);
			stringbuilder.delete(0, stringbuilder.length());
		}*/
	}
	
	public String getDado( String end ) {
		
		String tag = end.substring(0, bitsTag);
		String linha = end.substring(bitsTag, bitsTag + bitsLinha);
		String palavra = end.substring(bitsTag + bitsLinha, end.length());
		
		if( this.tags.get(linha) != null && this.tags.get(linha).equals(tag) ) {
			return cache.get(linha)[ Integer.parseInt(palavra,2) ];
		}
		
		return null;
	}
	
	private int bits( int num ) {
		
		for( int i = 0; i < Integer.MAX_VALUE; i++ ) {
			
			if( Math.pow(2, i) >= num ) {
				return i;
			}
		}		
		return 0;
	}
	
	public void setDado() {
		
	}	
}
