package com.fotoandroid;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	public int numFotos;
	public File f;
	ArrayList<Fotos> itens = new ArrayList<MainActivity.Fotos>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void capturaFoto(View v){
		f = new File(Environment.getExternalStorageDirectory(),"foto"+numFotos+".jpg");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		intent.putExtra("return-data", false);
		startActivityForResult(intent, 100);
	}
	
	// Classe que ira definir a minha lista
		class Fotos {
			public Bitmap thumb; // bitmap para a imagem reduzida
			public String nome; // nome da imagem (para mostrar no ListView(firula..)
			public String url; // Url Completa da Imagem :) para ser aberta na Activity PhotoEdit

			public Fotos(Bitmap thumb,String nome,String url) {
				// TODO Auto-generated constructor stub
				this.thumb = thumb;
				this.nome = nome;
				this.url = url;
			}		
		}

}
