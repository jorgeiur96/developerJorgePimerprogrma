package com.example.jorgeurueta.fotos;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ImageView imageView;
    private Bitmap loadedImage;
  //  private String imageHttpAddress = "https://lh6.googleusercontent.com/JLN3E-G4FOWITZh1ClJNrf-9XPFqCGzqrmLh2renFByeDLG4kuptUC_usCZJ_Vy6S3Z6vKgMVzfax-U=w1366-h662-rw";
    public static final String URL = "https://lh6.googleusercontent.com/JLN3E-G4FOWITZh1ClJNrf-9XPFqCGzqrmLh2renFByeDLG4kuptUC_usCZJ_Vy6S3Z6vKgMVzfax-U=w1366-h662-rw";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.Vista_foto);
      //  downloadFile(imageHttpAddress);

       // Bitmap obtener_imagen = get_imagen("https://lh6.googleusercontent.com/JLN3E-G4FOWITZh1ClJNrf-9XPFqCGzqrmLh2renFByeDLG4kuptUC_usCZJ_Vy6S3Z6vKgMVzfax-U=w1366-h662-rw");
        //imageView.setImageBitmap(obtener_imagen);
        //GetImageTask task = new GetImageTask();
        //task.execute(new String[] { URL });
       // new GetImageTask().execute(URL);
        new DownloadImageTask().execute("http://c1.staticflickr.com/5/4352/37152215115_19ded9c724_b.jpg");

    }





    class DownloadImageTask extends AsyncTask<String, Void, Drawable>
    {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        protected void onPreExecute()
        {
            progressDialog.setTitle("");
            progressDialog.setMessage("Cargando imagen...");
            progressDialog.show();


        }


        protected Drawable doInBackground(String... urls)
        {
            Log.d("DEBUG", "drawable");

            return downloadImage(urls[0]);

        }


        protected void onPostExecute(Drawable imagen)
        {


            Bitmap b = ((BitmapDrawable)imagen).getBitmap();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(b,360,300,false);
            Drawable n= new BitmapDrawable(getResources(), bitmapResized);
            imageView.setImageDrawable(n);

            progressDialog.dismiss();

        }


        private Drawable downloadImage(String imageUrl)
        {
            try
            {
                URL url = new URL(imageUrl);
                InputStream is = (InputStream)url.getContent();

                return Drawable.createFromStream(is, "src");
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return null;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }



}
