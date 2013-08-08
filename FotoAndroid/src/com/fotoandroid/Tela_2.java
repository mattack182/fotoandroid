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
	private Bitmap bmp_global;
//	private Bitmap bmp_preview_global;
	private double seek1_value = 1;
	private double seek2_value = 1;
	private double seek3_value = 1;
	private int bt_action;
	
	
	ImageView imgView;
	//ImageView imgView_thumb;
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
		
		//imgView_thumb = (ImageView)findViewById(R.id.imageView2);
	//	imgView_thumb.setVisibility(INVISIVEL);
		
		path = getIntent().getStringExtra("path");
		name = getIntent().getStringExtra("name");
		index = getIntent().getIntExtra("arg2", 0);
		foto = new Foto(name, path);
		bmp_global = foto.getBitmap();	
//		bmp_preview_global = ThumbnailUtils.extractThumbnail(bmp_global, 120, 120);		
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
		imgView = (ImageView)findViewById(R.id.imageView1);
		imgView.setImageBitmap(bmp_global);
//		bmp_preview_global = ThumbnailUtils.extractThumbnail(bmp_global, 120, 120);
//		imgView_thumb.setImageBitmap(bmp_preview_global);
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
				imgView.setImageAlpha(40);	
			
//				imgView_thumb.setImageBitmap(bmp_preview_global);
//				imgView_thumb.setVisibility(VISIVEL);					
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
//					Bitmap bmp_preview_local;
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub						
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_local, seek1_value, 1, 1);
//						imgView_thumb.setImageBitmap(bmp_preview_local);
//						bmp_preview_global = bmp_preview_local;
					}			
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						// inverte bmp_preview_global 1/seek1_value
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_global, (1/seek1_value), 1, 1);
//						bmp_preview_local = bmp_preview_global;
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
//					Bitmap bmp_preview_local;
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_local, 1, seek2_value, 1);
//						imgView_thumb.setImageBitmap(bmp_preview_local);
//						bmp_preview_global = bmp_preview_local;
						
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_global, 1, (1/seek2_value), 1);
//						bmp_preview_local = bmp_preview_global;
						
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
//					Bitmap bmp_preview_local;
					
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_local, 1, 1, seek3_value);
//						imgView_thumb.setImageBitmap(bmp_preview_local);
//						bmp_preview_global = bmp_preview_local;
					}
					
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
//						foto.doImageProcessing(FILTRO_COR, bmp_preview_global, 1, 1, 1/seek3_value);
//						bmp_preview_local = bmp_preview_global;
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
//				imgView_thumb.setVisibility(INVISIVEL);					
				txt_red.setVisibility(INVISIVEL);
				txt_green.setVisibility(INVISIVEL);
				txt_blue.setVisibility(INVISIVEL);				
				imgView.setImageAlpha(255);				
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
			bmp_global = foto.getBitmap();
			foto.doImageProcessing(foto.FILTRO_COR, bmp_global, seek1_value, seek2_value, seek3_value);
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
			foto.doImageProcessing(foto.FILTRO_NEGATIVO, bmp_global, 0, 0, 0);			
			foto.setChangedFlag();
			atualizaTela();
			break;
		case R.id.item3:
			foto.salvaBitmapSDCard(bmp_global, path);
			Toast.makeText(getApplicationContext(), "A foto "+foto.nome+" foi salva no SD Card com sucesso!",
					Toast.LENGTH_SHORT).show();
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
