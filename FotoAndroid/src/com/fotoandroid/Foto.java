package com.fotoandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;

public class Foto {	
	public Bitmap imagem_copy;
	public Bitmap img_thumb; // Bitmap thumbnail da foto
	public String nome; // nome da foto	
	public String path; // String caminho completo da foto	
	private boolean changed = false;
	public final int FILTRO_COR = 1;
	public final int FILTRO_NEGATIVO = 2;
	public final int FILTRO_SEPIA = 3;
	public final int FILTRO_OVERLAY = 4;
	
	
	
	// CONSTRUTOR 
	public Foto(Bitmap bmp, String nome, String path) {				
		this.nome = nome;
		this.path = path;		
		this.imagem_copy = bmp.copy(Bitmap.Config.ARGB_8888, true);
		
					
		// gera o thumbnail da foto	
		img_thumb = ThumbnailUtils.extractThumbnail(imagem_copy, 70, 70);
	} // end of construtor class Foto
	
	public Bitmap getThumb(){
		Bitmap bitmap_return = null;
		if(changed = true){
			Bitmap bitmap_temp = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_8888, true);
			bitmap_return = bitmap_temp.copy(Bitmap.Config.ARGB_8888, true);
		}		
		return bitmap_return;		
	}
	
	public void setChangedFlag(){
		this.changed = true;
	}
	
	public boolean getChangedFlag(){
		return changed;
	}
	
	
	public int doImageProcessing(int type, Bitmap img, double red, double green, double blue){
		
		
		changed = true; // seta flag de alteração da imagem para que o thumbnail seja recriado
		
		switch(type){
		case FILTRO_COR:			// Filtro de cor -> doColorFilter
			doColorFilter(img, red, green, blue);
			return 0;
		case FILTRO_NEGATIVO:			// Negativo -> doNegative
			return 0;
		case FILTRO_SEPIA:			// Sepia -> doSepia
			return 0;
		case FILTRO_OVERLAY:			// Overlay -> doOverlay
			return 0;
		default:		// Caso de parametro invalido
			changed = false; // altera a flag de bitmap pois nada foi alterado
			return 1;				
		}
		
	}
	
	
	
	/*
	 * Algoritmo para filtrar cores
	 * Parametros: 	Bitmap, (imagem alvo)
	 * 				vermelho, verde, azul (fatores de multiplicação)
	 * Seta boolean changed para 'true'
	 * Retorna Bitmap
	 */
	private Bitmap doColorFilter(Bitmap img, double red, double green, double blue) {
        // image size
        int width = img.getWidth();
        int height = img.getHeight();       
        
        // color information
        int A, R, G, B;
        int pixel;
 
        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = img.getPixel(x, y);
                // apply filtering on each channel R, G, B
                A = Color.alpha(pixel);
                R = (int)(Color.red(pixel) * red);
                G = (int)(Color.green(pixel) * green);
                B = (int)(Color.blue(pixel) * blue);
                // set new color pixel to output bitmap
                img.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        setChangedFlag();
        return img;
    }
	
}
