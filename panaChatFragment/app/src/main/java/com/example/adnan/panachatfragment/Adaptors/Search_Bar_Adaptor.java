package com.example.adnan.panachatfragment.Adaptors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.transition.CircularPropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.Frient_Req_Signature;
import com.example.adnan.panachatfragment.Signatures.Search_Signature;
import com.example.adnan.panachatfragment.Signatures.User;
import com.example.adnan.panachatfragment.UTils.Global;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Adnan on 1/3/2016.
 */
//public class Search_Bar_Adaptor extends BaseAdapter implements Filterable {

public class Search_Bar_Adaptor extends BaseAdapter implements Filterable {
    public Context context;
    public ArrayList<User> employeeArrayList;
    public ArrayList<User> orig;
    Firebase fire;

    public Search_Bar_Adaptor(Context context, ArrayList<User> employeeArrayList) {
        super();
        fire = new Firebase(Global.url);
        this.context = context;
        this.employeeArrayList = employeeArrayList;
    }

    public class EmployeeHolder {
        TextView name;
        CircularImageView image;
        ImageButton imageButton;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<User> results = new ArrayList<User>();
                if (orig == null)
                    orig = employeeArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final User g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                employeeArrayList = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return employeeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_fragment_each_item, parent, false);
            holder = new EmployeeHolder();
            holder.name = (TextView) convertView.findViewById(R.id.friendListSingleItem);
            holder.image = (CircularImageView) convertView.findViewById(R.id.imageViewFriendListPic);
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.addButton);
            holder.image.setBorderColor(R.color.grey);
            holder.image.setBorderWidth(1);
            convertView.setTag(holder);
        } else {
            holder = (EmployeeHolder) convertView.getTag();
        }

        holder.name.setText(employeeArrayList.get(position).getName());
        Picasso.with(context)
                .load(employeeArrayList.get(position).getPicUrl())
                .placeholder(R.drawable.userdefaul)
                .error(R.drawable.userdefaul)
                .into(holder.image);


        final View finalConvertView = convertView;
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeArrayList.get(position).getUid().equals(Global.uid)) {
                    Toast.makeText(context, "Its You", Toast.LENGTH_SHORT).show();
//
                } else {
                    check(position, finalConvertView);
                }
            }
        });

        return convertView;

    }

    public void check(final int position, final View convert) {
        fire.child("AppData").child("Friends").child(Global.uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(employeeArrayList.get(position).getUid())) {
                    YoYo.with(Techniques.Wobble)
                            .duration(700)
                            .playOn(convert.findViewById(R.id.addButton));
                    Snackbar.make(convert.findViewById(R.id.addButton), "Already In Your List", Snackbar.LENGTH_SHORT).show();
                } else {
                    alert(position);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void alert(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("What You Want To Do");
        alert.setMessage("Are you sure you want to add " + "" + employeeArrayList.get(position).getName() + "?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                fire.child("AppData").child("FriendRequests").child(employeeArrayList.get(position).getUid()).child(Global.uid).setValue(new Search_Signature(Global.name, Global.picUrl));
                Toast.makeText(context, "Friend Request Sent ", Toast.LENGTH_SHORT).show();


            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
        alert.create();
    }

}
