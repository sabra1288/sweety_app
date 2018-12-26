package firstapp.om.sweety.language;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

import firstapp.om.sweety.R;

public class SweetyLangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweety_lang);
    }

    public void chageLang(String lang){

        Locale mylocale=new Locale(lang);
        android.content.res.Configuration config=new android.content.res.Configuration();
        config.locale=mylocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics()); //defalte code for changelangage

        SharedPreferences sharedPreferences=getSharedPreferences(SweetyLangConstans.LANG_FILE,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(SweetyLangConstans.LANG,lang);

        editor.commit();
    }
}
