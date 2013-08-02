package com.fotoandroid;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;

public class Foto {	
	public Bitmap imagem_mutable;
	public Bitmap img_thumb; // Bitmap thumbnail da foto
	public String nome; // nome da foto	
	public String url; // String caminho completo da foto
	public Uri url_uri; // Uri da foto
	
	// construtor
	public Foto(Bitmap bmp, String nome, String url) {
		// TODO Auto-generated constructor stub
				
		this.nome = nome;
		this.url = url;
		this.url_uri = Uri.parse(url);
		this.imagem_mutable = bmp.copy(Bitmap.Config.ARGB_8888, true);
		
					
		// gera o thumbnail da foto	
		img_thumb = ThumbnailUtils.extractThumbnail(imagem_mutable, 70, 70);	
	}
	
	public Bitmap getThumb(String path){
		File f = new File()
		Bitmap bitmap_temp = BitmapFactory.decodeFile(f.getAbsolutePath());
		Bitmap bitmap_return = bitmap_temp.copy(Bitmap.Config.ARGB_8888, true);
		return bitmap_return;		
	}
	
	
	
	public Bitmap doColorFilter(Bitmap img, double red, double green, double blue) {
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
