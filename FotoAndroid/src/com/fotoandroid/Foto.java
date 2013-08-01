package com.fotoandroid;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class Foto {	
	public Bitmap img; // bitmap para a imagem reduzida
	public Bitmap img_thumb;
	public String nome; // nome da imagem (para mostrar no
						// ListView(firula..)
	public String url; // Url Completa da Imagem :) para ser aberta na
						// Activity PhotoEdit

	public Foto(Bitmap img, String nome, String url) {
		// TODO Auto-generated constructor stub
		this.img = img;
		this.nome = nome;
		this.url = url;
		
		// gera thumbnail da foto
		new ThumbnailUtils();
		img_thumb = ThumbnailUtils.extractThumbnail(img, 60, 60);	
		
	}
}
