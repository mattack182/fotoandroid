package com.fotoandroid;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private File f = null;
	public int counter;
	public String TAG = "FotoAndroid";
	Adaptador adaptador;
	ArrayList<Foto> foto_array = new ArrayList<Foto>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		counter = 0;
		ListView lista = (ListView) findViewById(R.id.ListView1);
		adaptador = new Adaptador(getApplicationContext());
		lista.setAdapter(adaptador);
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent in = new Intent(getApplicationContext(), Tela_2.class);
				in.putExtra("path", foto_array.get(arg2).path);
				in.putExtra("name", foto_array.get(arg2).nome);
				in.putExtra("arg2", arg2);
				startActivityForResult(in, 81);		
			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.		
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

//		switch (item.getItemId()) {
//		case R.id.action_settings:
//			capturaFoto(getCurrentFocus());
//			break;
//		}

		return super.onMenuItemSelected(featureId, item);

	}
	
	
	/*
	 * Classe Adaptador
	 */
	
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
			return foto_array.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return foto_array.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub		
			if (arg1 == null) {
				arg1 = inflater.inflate(R.layout.linha, arg2, false);
			}

			// seta o nome_da_foto da linha
			TextView txtnome = (TextView) arg1.findViewById(R.id.textView_nome_foto);
			txtnome.setText(foto_array.get(arg0).nome);

			// seta o thumbnail da linha
			ImageView thumbnail = (ImageView) arg1.findViewById(R.id.imageView1);
			thumbnail.setImageBitmap(foto_array.get(arg0).getThumb());

			return arg1;

		}

	}

	public void capturaFoto(View v) {
		
		f = new File(Environment.getExternalStorageDirectory(), "img"+counter+".jpg");
		counter++;
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		startActivityForResult(intent, 80);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 80 && resultCode == RESULT_OK) {

			Foto new_foto = new Foto(f.getName(), f.getAbsolutePath());

			// adiciona nova foto no arraylist<Foto>
			foto_array.add(new_foto);
			// notifica adapter para atualizar o conteudo
			adaptador.notifyDataSetChanged();
		}
		else if(requestCode == 81 && resultCode == RESULT_OK){
		int index = data.getIntExtra("arg2", 0);
		if(data.getBooleanExtra("changed-flag", true)){
			foto_array.get(index).setChangedFlag();
			adaptador.notifyDataSetChanged();
			}
		
		}

	}

}
