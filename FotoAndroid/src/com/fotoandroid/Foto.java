package com.fotoandroid;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;

public class Foto {	
	public Bitmap img; // Bitmap foto tamanho original
	public Bitmap img_thumb; // Bitmap thumbnail da foto
	public String nome; // nome da foto
	public String url; // String caminho completo da foto
	public Uri url_uri; // Uri da foto
	
	// construtor
	public Foto(Bitmap img, String nome, String url) {
		// TODO Auto-generated constructor stub
		this.img = img;
		this.nome = nome;
		this.url = url;
		// converts url string to Uri
		url_uri = Uri.parse(url);
			
		// gera o thumbnail da foto 60 x 60
		new ThumbnailUtils();
		img_thumb = ThumbnailUtils.extractThumbnail(img, 70, 70);		
	}
}
