package firstapp.om.sweety.activities;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import firstapp.om.sweety.R;

public class MessageAdapter extends BaseAdapter {
    private TextView name;

    List<Messages> messages = new ArrayList<Messages>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Messages message) {
        this.messages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Messages message = messages.get(position);

        if (message.isBelongsToCurrentUser()) { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());

        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.view = (View) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getData().getName());
            holder.messageBody.setText(message.getText());
            GradientDrawable drawable = (GradientDrawable) holder.view.getBackground();
            drawable.setColor(Color.parseColor(message.getData().getColor()));
        }

        return convertView;
    }

    private class MessageViewHolder {
        public TextView messageBody;
        public View view;
        public  TextView name;
    }
}

