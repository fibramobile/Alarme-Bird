package francisns.alarmedepssaros;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Random;

/**
 * Created by francisns on 13/12/16.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int startId;
    boolean isRunning;
    Integer whale_sound_choice;

    Notification myNotication;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String state = intent.getExtras().getString("extra");

        whale_sound_choice = intent.getExtras().getInt("whale_choice");

        //    NotificationManager notification_manager = (NotificationManager)
        //         getSystemService(NOTIFICATION_SERVICE);

        //this converts the extra strings from the intent to start IDs, values 0 or 1
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        //instrucoes de  if else :

        //Se não houver reprodução de música, e o usuário pressionou "alarme em" música começar a tocar
        if (!this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 0;

            Intent alarmIntent = new Intent(getBaseContext(), Off_Despertador.class);
            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            alarmIntent.putExtras(intent);
            getApplication().startActivity(alarmIntent);

            //API level 11
            Intent intent1 = new Intent(this.getApplicationContext(), Off_Despertador.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);


            if (whale_sound_choice == 0) {

                mRandom();

            } else {

                mNormal();

            }

        } else if (this.isRunning && startId == 0) {
            //stop the ringtone
            mediaSong.stop();
            mediaSong.reset();

            this.isRunning = false;
            this.startId = 0;

        }
        //these are if the user pressed random buttons just to bug-proof the app if there is no music
        //playing, and the user pressed "alarm off" do nothing
        else if (!this.isRunning && startId == 0) {

            this.isRunning = false;
            this.startId = 0;

        }

        //if there is music playing and the user pressed "alarm on" do nothing
        else if (this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 1;
        }
        // can't think of anything else, just to catch the odd event
        else {

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("on destroy called ", "ta da ");

        super.onDestroy();
        this.isRunning = false;
    }

    public void mNormal() {
        if (whale_sound_choice == 1) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.coleiro_tuituiclassico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_sound_choice == 2) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.trinca_catingueiro);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 3) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.curio_canto_paracambi);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_sound_choice == 4) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.bicudo_flauta);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 5) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.sabia_laranjeira1);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 6) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.tico_mariquinha);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 7) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.canario_estalo_classico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 8) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.melro);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 9) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.bigodinho_classico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 10) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.sanhaco_violino);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        } else if (whale_sound_choice == 11) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.azulao);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();
        }
        //-----------------------------------------------------------------------------
    }

    public void mRandom() {

        int max = 11;
        int min = 1;

        Random random = new Random();
        int whale_number = random.nextInt(max - min) + min;


        if (whale_number == 1) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.coleiro_tuituiclassico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 2) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.trinca_catingueiro);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 3) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.curio_canto_paracambi);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 4) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.bicudo_flauta);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 5) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.sabia_laranjeira1);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 6) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.tico_mariquinha);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 7) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.canario_estalo_classico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 8) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.melro);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 9) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.bigodinho_classico);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else if (whale_number == 10) {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.sanhaco_violino);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        } else {

            //criando uma instancia para o media player
            mediaSong = MediaPlayer.create(this, R.raw.azulao);
            //iniciando looping de audio
            mediaSong.setLooping(true);
            //start the ringtone
            mediaSong.start();

        }
    }


}


