package simulador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.stream.events.EndDocument;

import cache.DirectMap;
import cache.Four_Way;
import cache.Two_Way;

public class Configurar {
	
	private DirectMap directMap;
	private Two_Way two_Way;
	private Four_Way four_Way;
	private int cache_size;
	private int unit_cache;
	private int pri_size;
	private int unit_pri;
	private int linhas_cache;
	private int nBlocos;
	private int bloco_size;
	private int bits_ende;
	
	public Configurar( String file ){
		
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			
			pri_size = Integer.parseInt(lerLinha(bfr));
			unit_pri = conv_Unidade(lerLinha(bfr));
			cache_size = Integer.parseInt(lerLinha(bfr));
			unit_cache = conv_Unidade(lerLinha(bfr));
			nBlocos = Integer.parseInt(lerLinha(bfr));
			bloco_size = Integer.parseInt(lerLinha(bfr));
			linhas_cache = ( cache_size/nBlocos ) * unit_cache;
			bits_ende = bits((pri_size * unit_pri)/bloco_size); 
					
			set_Cache_DirectMap();
			set_Cache_2_Way();
			set_Cache_4_Way();
			
			bfr.close();
		}
		catch (Exception e) {
			
		}
	}
	
	private void set_Cache_DirectMap() {
		directMap = new DirectMap(bits_ende,linhas_cache, nBlocos);
	}
	
	private void set_Cache_2_Way() {
		two_Way = new Two_Way(linhas_cache, nBlocos);	
	}
	
	private void set_Cache_4_Way() {
		four_Way = new Four_Way(linhas_cache, nBlocos);
	}
	
	public DirectMap get_Cache_DirectMap() {
		return directMap;
	}
	
	public Two_Way get_Cache_2_way() {
		return two_Way;
	}
	
	public Four_Way get_Cache_4_way() {
		return four_Way;
	}
	
	private String lerLinha(BufferedReader bfr) throws IOException  {
		
		String aux = "";
		int count = 0;
		String palavra = bfr.readLine();
		
		for( int index = 0; index < palavra.length(); index++ ) {
			
			if( palavra.charAt(index) == ' ' ) {
				count++;				
			}
			else if( count == 2 ) {
				aux += palavra.charAt(index);
			}
		}
		
		return aux;
	}
	
	private int conv_Unidade(String tipo) {
		
		switch(tipo.toUpperCase().charAt(0)) {
		
			case 'M' : return 1048576; 
			case 'K' : return 1024;
			case 'G' : return 1073741824;
		}
		
		return 1;
	}
	
	private int bits( int num ) {
		
		for( int i = 0; i < Integer.MAX_VALUE; i++ ) {
			
			if( Math.pow(2, i) >= num ) {
				return i;
			}
		}		
		return 0;
	}
}
