package com.fotoandroid;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;

public class Foto {	
	
	public Bitmap img_thumb; 						// Bitmap thumbnail da foto
	public String nome; 							// nome da foto	
	public String path; 							// String caminho completo da foto	
	public final int FILTRO_COR = 1;
	public final int FILTRO_NEGATIVO = 2;
	public final int FILTRO_SEPIA = 3;
	public final int FILTRO_OVERLAY = 4;
	private boolean changed = false;
	
	// CONSTRUTOR 
	public Foto(String nome, String path) {
		this.nome = nome;
		this.path = path;		
		Bitmap bitmap_temp = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_4444, true);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap_temp.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        img_thumb = ThumbnailUtils.extractThumbnail(bitmap_temp, 70, 70);		
	}
	
	// METODOS PUBLICOS	
	public Bitmap getBitmap(){
		Bitmap bitmap_new = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_8888, true);
		return bitmap_new;
	}
	
	public void salvaBitmapSDCard(Bitmap bmp, String path){

			File f = new File(Environment.getExternalStorageDirectory(), nome);
		    try {
		           FileOutputStream out = new FileOutputStream(f);
		           bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
		           out.flush();
		           out.close();

		    } catch (Exception e) {
		           e.printStackTrace();
		    }		
		
	}
	
	public Bitmap getThumb(){
		
		if(changed){
			Bitmap bitmap_temp = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_4444, true);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap_temp.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			img_thumb = ThumbnailUtils.extractThumbnail(bitmap_temp, 70, 70);
			changed = false;			
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
		
		setChangedFlag();						// seta flag de alteração da imagem para que o thumbnail seja recriado		
		
		switch (type) {		
		case FILTRO_COR:						// Filtro de cor -> doColorFilter
			img = doColorFilter(img, red, green, blue);
			return 0;		
		case FILTRO_NEGATIVO:					// Negativo -> doNegative
			img = doNegative(img);
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
	
	
	// METODOS PRIVADOS (Processamento da Imagem)
	
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
	
	private Bitmap doNegative(Bitmap img){
		
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
                R = (int)(255 - Color.red(pixel));
                G = (int)(255 - Color.green(pixel));
                B = (int)(255 - Color.blue(pixel));
                // set new color pixel to output bitmap
                img.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }        
        return img;
			
	}
	
}
