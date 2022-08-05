package com.example.blood_donantion_app.Email;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;


import androidx.appcompat.widget.AppCompatButton;

import com.example.blood_donantion_app.R;
import com.example.blood_donantion_app.Util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPi extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;
    private String email, subject, message;

    public JavaMailAPi(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait as the email is being sent");
        progressDialog.setTitle("Sending Email to Donor");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Util.Email, Util.Password);
                //return super.getPasswordAuthentication();
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Util.Email));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressDialog.dismiss();

        startAlertDialog();
        super.onPostExecute(unused);
    }

    private void startAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.output, null);
        alert.setView(view);
        final  AlertDialog dialog = alert.create();
        dialog.setCancelable(false);
        AppCompatButton close = view.findViewById(R.id.close_btn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
