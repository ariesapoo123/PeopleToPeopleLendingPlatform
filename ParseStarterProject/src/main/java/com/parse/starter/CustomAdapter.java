package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lender> list;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<Lender> list) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        //Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.layout_list, null);
        TextView email_id = (TextView) view.findViewById(R.id.email_id);
        TextView loan_amount = (TextView) view.findViewById(R.id.loan_amount);
        TextView interest_rate = (TextView) view.findViewById(R.id.interest_rate);
        TextView type_of_loan = (TextView) view.findViewById(R.id.type_of_loan);

        Lender lender = list.get(i);
        //Toast.makeText(context, lender.toString(), Toast.LENGTH_SHORT).show();

        email_id.setText(lender.getEmail_id());
        loan_amount.setText(lender.getLoan_amt());
        interest_rate.setText(lender.getInterst_rate());
        type_of_loan.setText(lender.getType_of_loan());

        return view;
    }
}
