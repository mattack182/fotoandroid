package com.fotoandroid;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
// imports do projeto

public class MainActivity extends Activity {
	public int counter;	
	Adaptador adaptador;
	ArrayList<Foto> foto = new ArrayList<Foto>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		counter = 0;
		
		ListView lista = (ListView) findViewById(R.id.ListView1);
		adaptador = new Adaptador(getApplicationContext());
		lista.setAdapter(adaptador);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub	
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			capturaFoto(getCurrentFocus());
			break;		
		}
		
		return super.onMenuItemSelected(featureId, item);
		
	}
	
	class Adaptador extends BaseAdapter {
		Context context;
		LayoutInflater inflater;	

		public Adaptador(Context context) {
			this.context = context;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return foto.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return foto.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			Log.v("TAGLOKA", "INICIO");
			View linha = arg1;
			if (linha == null) {
				linha = inflater.inflate(R.layout.linha, arg2, false);
			}
			
			// seta o nome_da_foto da linha 
			TextView txtnome = (TextView) linha.findViewById(R.id.textView_nome_foto);
			txtnome.setText(foto.get(arg0).nome);
			
			// seta o thumbnail da linha
			ImageView thumbnail = (ImageView)linha.findViewById(R.id.imageView1);
			thumbnail.setImageBitmap(foto.get(arg0).getThumb());
			Log.v("TAGLOKA", "FIM");
			return linha;
			
			
		}
		
		

	}
	
	public void salvaFoto(Bitmap bmp, String path){
		
	}
	
	public void capturaFoto(View v) {
		File f = new File(Environment.getExternalStorageDirectory(), "img" + counter+ ".jpg");
		counter++;		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		intent.putExtra("return-data", false);
		intent.putExtra("path", f.getAbsolutePath());
		startActivityForResult(intent, 80);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 80 && resultCode == RESULT_OK) {
			
			String path = getIntent().getStringExtra("path");
			Log.v("FOTOANDROID", path);
		
			//Bitmap bitmap_temp = BitmapFactory.decodeFile(f.getAbsolutePath());
			// instancia novo objeto Foto
			//Foto new_foto = new Foto(bitmap_temp, f.getName(), f.getAbsolutePath());
			// adiciona efeito metodo da classe Foto
			//Log.v("FOTOANDROID", "ENTRANDO NO doColorFilter");
			//new_foto.doColorFilter(0, 1, 0);
			
			//new_foto.doImageProcessing(type, img, red, green, blue)
			
			// adiciona nova foto no arraylist<Foto>
			//foto.add(new_foto);
			// notifica adapter para atualizar o conteudo
			//adaptador.notifyDataSetChanged();
		}

	}

}
