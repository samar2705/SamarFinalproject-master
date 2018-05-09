/**
 * Created by Hp1 on 09/05/2018.
 */

public class Notifacation {
    public void onReceive(Context context, Intent intent) {

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Toast.makeText(context,"Notification",Toast.LENGTH_SHORT).show();

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.song)
                .setContentTitle("You are in.")
        notificationManager.notify(0, mNotifyBuilder.build());


    }
