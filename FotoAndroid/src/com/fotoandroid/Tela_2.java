package com.fotoandroid;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_2 extends Activity {
	
	public String path;
	public String name;
	public int index = 0;
	public final int CTRL_FILTRO_COR = 1;
	public final int CTRL_FILTRO_NEGATIVO = 2;
	public final int CTRL_FILTRO_SEPIA = 3;
	public final int CTRL_FILTRO_OVERLAY = 4;
	public final int FILTRO_COR = 1;
	public final int FILTRO_NEGATIVO = 2;
	// public final int FILTRO_SEPIA = 3;
	// ...
	
	private int VISIVEL = 0;
	private int INVISIVEL = 4;
	private Bitmap bmp;
	private Bitmap bmp_preview;
	private double seek1_value = 0;
	private double seek2_value = 0;
	private double seek3_value = 0;
	private int bt_action;
	
	
	ImageView img_view;
	ImageView img_view_thumb;
	SeekBar seekbar1;
	SeekBar seekbar2;
	SeekBar seekbar3;
	TextView txt_red;
	TextView txt_green;
	TextView txt_blue;
	Button button_aplicar;
	Foto foto;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_tela_2);
		
		seekbar1 = (SeekBar)findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar)findViewById(R.id.seekBar2);
		seekbar3 = (SeekBar)findViewById(R.id.seekBar3);
		seekbar1.setVisibility(INVISIVEL);
		seekbar2.setVisibility(INVISIVEL);
		seekbar3.setVisibility(INVISIVEL);
		
		txt_red = (TextView)findViewById(R.id.textView1);		
		txt_green = (TextView)findViewById(R.id.textView2);		
		txt_blue = (TextView)findViewById(R.id.textView3);
		txt_red.setText("Vermelho:");
		txt_green.setText("Verde:");
		txt_blue.setText("Azul:");
		txt_red.setVisibility(INVISIVEL);
		txt_green.setVisibility(INVISIVEL);
		txt_blue.setVisibility(INVISIVEL);
		
		
		button_aplicar = (Button)findViewById(R.id.button1);
		button_aplicar.setVisibility(INVISIVEL);
		
		img_view_thumb = (ImageView)findViewById(R.id.imageView2);
		img_view_thumb.setVisibility(INVISIVEL);
		
		path = getIntent().getStringExtra("path");
		name = getIntent().getStringExtra("name");
		index = getIntent().getIntExtra("arg2", 0);
		foto = new Foto(name, path);
		bmp = foto.getBitmap();
		atualizaTela();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		 Intent intent = new Intent();
		 intent.putExtra("changed-flag", foto.getChangedFlag());
		 intent.putExtra("arg2", index);
		 setResult(RESULT_OK, intent);
		 finish();
	}	
	
	public void atualizaTela(){
		img_view = (ImageView)findViewById(R.id.imageView1);
		img_view.setImageBitmap(bmp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_2, menu);
		return true;
	}
	
	public void UI_Controls(int type, boolean enabled){
		
		switch(type){		
		case CTRL_FILTRO_COR:
			if(enabled){
				
				// habilita botoes na tela para ajustes antes de aplicar o filtro
				img_view.setImageAlpha(40);
				
				Bitmap bitmap_temp = bmp;
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
	            bitmap_temp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
				foto.img_thumb = ThumbnailUtils.extractThumbnail(bitmap_temp, 150, 150);				
				img_view_thumb.setImageBitmap(foto.img_thumb);			
				img_view_thumb.setVisibility(VISIVEL);					
				button_aplicar.setVisibility(VISIVEL);				
				seekbar1.setVisibility(VISIVEL);
				seekbar2.setVisibility(VISIVEL);
				seekbar3.setVisibility(VISIVEL);
				txt_red.setVisibility(VISIVEL);
				txt_green.setVisibility(VISIVEL);
				txt_blue.setVisibility(VISIVEL);		
				button_aplicar.setAlpha(1);
				
				// Controles Seekbar e atualização do thumbnail para preview
				seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub				
					}			
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub				
					}			
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						// TODO Auto-generated method stub
						seek1_value = progress * 0.01;
						txt_red.setText("Vermelho: "+progress);
					}
				});
				seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						// TODO Auto-generated method stub
						seek2_value = progress * 0.01;
						txt_green.setText("Verde: "+progress);
						
					}
				});
				seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						// TODO Auto-generated method stub
						seek3_value = progress * 0.01;
						txt_blue.setText("Azul: "+progress);
						
					}
				});

			}
			else{
				// desabilita botoes da tela
				seekbar1.setVisibility(INVISIVEL);
				seekbar2.setVisibility(INVISIVEL);
				seekbar3.setVisibility(INVISIVEL);
				button_aplicar.setVisibility(INVISIVEL);				
				img_view_thumb.setVisibility(INVISIVEL);					
				txt_red.setVisibility(INVISIVEL);
				txt_green.setVisibility(INVISIVEL);
				txt_blue.setVisibility(INVISIVEL);				
				img_view.setImageAlpha(255);				
			}			
			break;
			
			
		case CTRL_FILTRO_NEGATIVO:
			if(enabled){				
			}else{			
			}
			break;
		}		
	}
	
	public void setBt_action(int type){
		
		if(type == FILTRO_COR){
			bt_action = FILTRO_COR;			
		}
		else if(type == FILTRO_NEGATIVO){
			bt_action = FILTRO_NEGATIVO; 
		}		
	}
	
	
	// Tratamento do botão 'Aplicar'
	public void bt_aplicar(View v){
		
		switch(bt_action){		
		case FILTRO_COR:
			foto.doImageProcessing(foto.FILTRO_COR, bmp, seek1_value, seek2_value, seek3_value);
			UI_Controls(CTRL_FILTRO_COR, false);
			foto.setChangedFlag();
			atualizaTela();
			break;
			
		case FILTRO_NEGATIVO:	
			break;		
		}		
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			setBt_action(FILTRO_COR);
			UI_Controls(CTRL_FILTRO_COR, true);
			break;
		case R.id.item2:
			//setBt_action(FILTRO_NEGATIVO);
			//UI_Controls(CTRL_FILTRO_NEGATIVO, true);
			foto.doImageProcessing(foto.FILTRO_NEGATIVO, bmp, 0, 0, 0);			
			foto.setChangedFlag();
			atualizaTela();
			break;
		case R.id.item3:
			foto.salvaBitmapSDCard(bmp, path);
			Toast.makeText(getApplicationContext(), "A foto "+foto.nome+" foi salva no SD Card com sucesso!",
					Toast.LENGTH_SHORT).show();
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
