package com.fotoandroid;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;

public class Foto {	
	
	public Bitmap img_thumb; 						// Bitmap thumbnail da foto
	public String nome; 							// nome da foto	
	public String path; 							// String caminho completo da foto	
	private boolean changed = false;	
	public final int FILTRO_COR = 1;
	public final int FILTRO_NEGATIVO = 2;
	public final int FILTRO_SEPIA = 3;
	public final int FILTRO_OVERLAY = 4;	
	
	// CONSTRUTOR 
	public Foto(String nome, String path) {
		this.nome = nome;
		this.path = path;
		img_thumb = getThumb(); // armazena thumbnail no objeto
	}
	
	// METODOS PUBLICOS	
	public Bitmap getBitmap(){
		Bitmap bitmap_new = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_8888, true);
		return bitmap_new;
	}
	
	public Bitmap getThumb(){
		Bitmap bitmap_new_thumb = null;
		if(changed = true){
			Bitmap bitmap_temp = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_4444, true);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap_temp.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			bitmap_new_thumb = ThumbnailUtils.extractThumbnail(bitmap_temp, 70, 70);
			return bitmap_new_thumb;
		}		
		return img_thumb;	
	}
	
	public void setChangedFlag(){
		this.changed = true;
	}
	
	public boolean getChangedFlag(){
		return changed;
	}
		
	public int doImageProcessing(int type, Bitmap img, double red, double green, double blue){		
		
		changed = true; 						// seta flag de alteração da imagem para que o thumbnail seja recriado		
		
		switch (type) {		
		case FILTRO_COR:						// Filtro de cor -> doColorFilter
			doColorFilter(img, red, green, blue);
			return 0;		
		case FILTRO_NEGATIVO:					// Negativo -> doNegative
			return 0;		
		case FILTRO_SEPIA:						// Sepia -> doSepia
			return 0;		
		case FILTRO_OVERLAY:					// Overlay -> doOverlay
			return 0;		
		default:								// Caso de parametro invalido
			changed = false; 					// altera a flag de bitmap pois nada foi alterado
			return 1;
		
		}
		
	}
	
	
	// METODOS PRIVADOS
	
	/*
	 * Algoritmo para filtrar cores
	 * Parametros: 	Bitmap, (imagem alvo)
	 * 				vermelho, verde, azul (fatores de multiplicação)
	 *
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
        return img;
    }
	
}
