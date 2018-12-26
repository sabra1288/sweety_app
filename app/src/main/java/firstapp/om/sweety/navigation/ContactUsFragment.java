package firstapp.om.sweety.navigation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import firstapp.om.sweety.R;


public class ContactUsFragment extends Fragment {
    TextView call,email,chat;
    private static Intent phoneCallIntent;
    public static final int REQUEST_CODE=1;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact_us, container, false);
        call=view.findViewById(R.id.phone);
        email=view.findViewById(R.id.c_email);
        chat=view.findViewById(R.id.chat);
        call.setText(Html.fromHtml(getString(R.string._0096898519000)));
        email.setText(Html.fromHtml(getString(R.string.email)));
        chat.setText(Html.fromHtml("<u> Chat with us </u>"));

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String url ="https://wa.me/96898519000";
                sendIntent.setData(Uri.parse(url));
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);

            }
        });



email.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("vnd.android.cursor.item/email");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sweetyapp00@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "body");

        startActivity(Intent.createChooser(intent, "chooser"));


    }
});
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        phoneCallIntent = new Intent(Intent.ACTION_CALL);
        phoneCallIntent.setData(Uri.parse("tel:00968 98519000")); //Uri.parse("tel:your number")

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
            return;

        }

        startActivity(phoneCallIntent);


    }
});

        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            final int permissionsLength = permissions.length;
            for (int i = 0; i < permissionsLength; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    startActivity(phoneCallIntent);
                }
            }
        }
    }
}
