package com.fotoandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Tela_2 extends Activity {
	public String path;
	public String name;
	private int VISIVEL = 0;
	private int INVISIVEL = 4;
	private Bitmap bmp;
	ImageView img_view;
	SeekBar seekbar1;
	SeekBar seekbar2;
	SeekBar seekbar3;
	Button button_aplicar;
	private double seek1_value = 0;
	private double seek2_value = 0;
	private double seek3_value = 0;
	Foto foto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_tela_2);
		seekbar1 = (SeekBar)findViewById(R.id.seekBar1);
		seekbar2 = (SeekBar)findViewById(R.id.seekBar2);
		seekbar3 = (SeekBar)findViewById(R.id.seekBar3);
		button_aplicar = (Button)findViewById(R.id.button1);
		button_aplicar.setVisibility(INVISIVEL);
		seekbar1.setVisibility(INVISIVEL);
		seekbar2.setVisibility(INVISIVEL);
		seekbar3.setVisibility(INVISIVEL);
		path = getIntent().getStringExtra("path");
		name = getIntent().getStringExtra("name");
		foto = new Foto(name, path);
		bmp = foto.getBitmap();
		atualizaTela();
	}
	
	
	
	
//	public void voltar(View v){
//		EditText ed = (EditText)findViewById(R.id.texto_tela1);
//		String texto = ed.getText().toString();		
//		Intent in = new Intent();
//		in.putExtra("retorno", texto);
//		setResult(RESULT_OK, in);
//		finish();
//	}
	
	
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
	
	public void getFilterSettings(){	
		img_view.setImageAlpha(80);
		button_aplicar.setAlpha(1);
		
		seekbar1.setVisibility(VISIVEL);
		seekbar2.setVisibility(VISIVEL);
		seekbar3.setVisibility(VISIVEL);
		button_aplicar.setVisibility(VISIVEL);
		
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
				
			}
		});
		
	}
	
	public void bt_aplicar(View v){
		
		Log.v("COR", seek1_value+"");
		Log.v("COR", seek2_value+"");
		Log.v("COR", seek3_value+"");
		
		foto.doImageProcessing(foto.FILTRO_COR, bmp, seek1_value, seek2_value, seek3_value);
		seekbar1.setVisibility(INVISIVEL);
		seekbar2.setVisibility(INVISIVEL);
		seekbar3.setVisibility(INVISIVEL);
		button_aplicar.setVisibility(INVISIVEL);
		img_view.setImageAlpha(255);
		foto.setChangedFlag();
		atualizaTela();
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			getFilterSettings();
			break;
		case R.id.item2:
			// TODO salvar imagem
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
