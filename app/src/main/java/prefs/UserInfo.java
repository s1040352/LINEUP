package prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 10/1/2016.
 */

public class UserInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_NAME = "st_name";
    private static final String KEY_USERNAME = "st_username";
    private static final String KEY_PHONE = "st_phone";
    private static final String KEY_ADDRESS = "st_address";
    private static final String KEY_start1 = "start1";
    private static final String KEY_start2 = "start2";
    private static final String KEY_end1 = "end1";
    private static final String KEY_end2 = "end2";
    private static final String KEY_weekday = "weekday";
    private static final String KEY_holiday = "holiday";
    private static final String KEY_spike_start_1_1 = "spike_start_1_1";
    private static final String KEY_spike_start_1_2 = "spike_start_1_2";
    private static final String KEY_spike_start_2_1 = "spike_start_2_1";
    private static final String KEY_spike_start_2_2 = "spike_start_2_2";
    private static final String KEY_spike_end_2_2 = "spike_end_2_2";
    private static final String KEY_spike_end_2_1 = "spike_end_2_1";
    private static final String KEY_spike_end_1_1 = "spike_end_1_1";
    private static final String KEY_spike_end_1_2 = "spike_end_1_2";
    private static final String KEY_spike_waiting_time = "spike_waiting_time";
    private static final String KEY_peak_waiting_time = "peak_waiting_time";
    private static final String KEY_waitingtime = "waitingtime";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setName(String st_name){
        editor.putString(KEY_NAME, st_name);
        editor.apply();
    }

    public void setUsername(String st_username){
        editor.putString(KEY_USERNAME, st_username);
        editor.apply();
    }

    public void setAddress(String st_address){
        editor.putString(KEY_ADDRESS, st_address);
        editor.apply();
    }

    public void setPhone(String st_phone){
        editor.putString(KEY_USERNAME, st_phone);
        editor.apply();
    }


    public void setstart1(String start1){
        editor.putString(KEY_start1, start1);
        editor.apply();
    }

    public void setstart2(String start2){
        editor.putString(KEY_start2, start2);
        editor.apply();
    }
    public void setend1(String end1){
        editor.putString(KEY_end1, end1);
        editor.apply();
    }
    public void setend2(String end2){
        editor.putString(KEY_end2, end2);
        editor.apply();
    }
    public void setweekday(String weekday){
        editor.putString(KEY_weekday, weekday);
        editor.apply();
    }
    public void setholiday(String holiday){
        editor.putString(KEY_holiday, holiday);
        editor.apply();
    }
    public void setspike_start_1_1(String spike_start_1_1){
        editor.putString(KEY_spike_start_1_1, spike_start_1_1);
        editor.apply();
    }
    public void setspike_start_1_2(String spike_start_1_2){
        editor.putString(KEY_spike_start_1_2, spike_start_1_2);
        editor.apply();
    }
    public void setspike_start_2_1(String spike_start_2_1){
        editor.putString(KEY_spike_start_2_1, spike_start_2_1);
        editor.apply();
    }
    public void setspike_start_2_2(String spike_start_2_2){
        editor.putString(KEY_spike_start_2_2, spike_start_2_2);
        editor.apply();
    }
    public void setspike_end_1_1(String spike_end_1_1){
        editor.putString(KEY_spike_end_1_1, spike_end_1_1);
        editor.apply();
    }
    public void setspike_end_1_2(String spike_end_1_2){
        editor.putString(KEY_spike_end_1_2, spike_end_1_2);
        editor.apply();
    }
    public void setspike_end_2_1(String spike_end_2_1){
        editor.putString(KEY_spike_end_2_1, spike_end_2_1);
        editor.apply();
    }
    public void setspike_end_2_2(String spike_end_2_2){
        editor.putString(KEY_spike_end_2_2, spike_end_2_2);
        editor.apply();
    }
    public void setspike_waiting_time(String spike_waiting_time){
        editor.putString(KEY_spike_waiting_time, spike_waiting_time);
        editor.apply();
    }
    public void setpeak_waiting_time(String peak_waiting_time){
        editor.putString(KEY_peak_waiting_time, peak_waiting_time);
        editor.apply();
    }
    public void setwaitingtime(String waitingtime){
        editor.putString(KEY_waitingtime, waitingtime);
        editor.apply();
    }







    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyUsername(){return prefs.getString(KEY_USERNAME, "");}

    public String getKeyName(){return prefs.getString(KEY_NAME, "");}

    public String getKeyAddress(){return prefs.getString(KEY_ADDRESS, "");}

    public String getKeyPhone(){return prefs.getString(KEY_PHONE, "");}
}
