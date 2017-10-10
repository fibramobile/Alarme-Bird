package francisns.alarmedepssaros;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by francisns on 13/12/16.
 */

public class Alarm_Receiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        String get_you_string = intent.getExtras().getString("extra");

        Integer get_your_whale_choice = intent.getExtras().getInt("whale_choice");

        Intent intent_service = new Intent(context , RingtonePlayingService.class);

        intent_service.putExtra("extra",get_you_string);

        intent_service.putExtra("whale_choice", get_your_whale_choice);

        context.startService(intent_service);

    }
}
