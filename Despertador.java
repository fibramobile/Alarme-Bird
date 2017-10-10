package francisns.alarmedepssaros;



import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;


public class Despertador extends Activity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView update_text;
    Context context;
    PendingIntent pendingIntent;
    int choose_whale_sound;
    long atual_alarme = 0;
    long proximo_alarme = 1000 * 60 * 60 * 24;
    String data;
    private String file = "mydata";
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despertador);

        NativeExpressAdView adView = (NativeExpressAdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);


        this.context = this;

        intent = new Intent(this.context, Alarm_Receiver.class);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        update_text = (TextView) findViewById(R.id.update_text);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cantos_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        Button alarme_on = (Button) findViewById(R.id.alarme_on);
        Button b2 = (Button) findViewById(R.id.btnLoad);

        final Calendar calendar = Calendar.getInstance();

        alarme_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hora = timePicker.getCurrentHour();
                int minuto = timePicker.getCurrentMinute();

                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minuto);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                String horaString = String.valueOf(hora);
                String minutoString = String.valueOf(minuto);

                if (hora > 24) {

                    horaString = String.valueOf(hora - 24);

                }
                if (minuto < 10) {
                    //10:7 ---> 10:07
                    minutoString = "0" + String.valueOf(minuto);
                }

                data = set_alarme_text("Alarme ajustado para " + horaString + ":" + minutoString);

                salvandoDados();

                intent.putExtra("extra", "alarm on");
                intent.putExtra("whale_choice", choose_whale_sound);
                pendingIntent = PendingIntent.getBroadcast(Despertador.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar now = Calendar.getInstance();
                if (calendar.getTimeInMillis() <= now.getTimeInMillis()) {
                    atual_alarme = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1);
                } else {
                    atual_alarme = calendar.getTimeInMillis();
                }
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, atual_alarme, proximo_alarme, pendingIntent);


            }

        });
//----------------------------------------------------------------------------------------------------------
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exibindoDadosSalvos();

            }
        });

    }
    protected String set_alarme_text(String saida) {
        update_text.setText(saida);
        update_text.setTextColor(getResources().getColor(R.color.colorPrimary));
        return saida;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        choose_whale_sound = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void salvandoDados(){

        try {
            FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);
            fOut.write(data.getBytes());
            fOut.close();
            update_text.setText(data);
            //  Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void exibindoDadosSalvos(){
        try {
            FileInputStream fin = openFileInput(file);
            int c;
            String temp = "";
            while ((c = fin.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            update_text.setText(temp);
            update_text.setTextColor(getResources().getColor(R.color.colorPrimary));
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Falha ao carregar ultimo alarme", Toast.LENGTH_LONG).show();
        }
    }

    public void snooze() {

        Calendar calendar =  Calendar.getInstance();

        int hora = timePicker.getCurrentHour();
        int minuto = timePicker.getCurrentMinute();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora);
        calendar.set(Calendar.MINUTE, minuto);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        intent.putExtra("extra", "alarm on");
        intent.putExtra("whale_choice", choose_whale_sound);
        pendingIntent = PendingIntent.getBroadcast(Despertador.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+ 1000*60*5 , pendingIntent);

    }
}
