package com.example.pepinteen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.jessicathornsby.telephonesms.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

//    private static final int PERMISSION_REQUEST_CODE = 1;
    private static Map<String, String> phoneNumbersMap = new HashMap<>();
//    private static Boolean isLocationSet = false;
//    private static String locationContentUrl;
//
//    private GoogleMap mMap;
//    private LatLng coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Se creaza in xml-> design EditText-urile si Button-urile.
        // Facem conexiunea cu EditText si Button =(findViewById)

        // 2. Adauga onClickListener pentru butonul de selectat contacte
        btnPickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 99);
            }
        });

//        2. Adauga onClickListener pentru butonul de send SMS
        sendSMS.setOnClickListener(new View.OnClickListener() {

        });
//        // 2. Adauga onClickListener pentru butonul de send SMS
//        sendSMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String sms = smsText.getText().toString();
//                String phoneNum = phoneNumber.getText().toString();
//                if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
//                    if (checkPermission()) {
//                        //Get the default SmsManager//
//                        SmsManager smsManager = SmsManager.getDefault();
//                        //Send the SMS//
//                        if (phoneNumbersMap != null && phoneNumbersMap.size() > 0) {
//                            for (String name : phoneNumbersMap.keySet()) {
//                                smsManager.sendTextMessage(phoneNumbersMap.get(name), null, sms, null, null);
//                            }
//                            Toast.makeText(MainActivity.this, "Successfully sent SMS to all the contacts", Toast.LENGTH_SHORT).show();
//                            phoneNumbersMap.clear();
//                            phoneNumber.setText("");
//                        }
//                    } else {
//                        Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });

        // 3. Adauga configurarile de permisiuni in AndroidManifest.xml  <uses-permission.....
        // Adauga functiile checkPermission & requestPermissions()
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        // 4. Adauga fragmentele pentru autocomplete si harta in activity_main.xml
        // Adauga <meta-data..... (x2) si <uses-library.... in AndroidManifest.xml
        // Adauga/inlocuieste in build.gradle, sectiunea dependencies urmatoarele:
        //  implementation 'com.google.android.gms:play-services-maps:16.0.0'
        //  implementation 'com.google.android.gms:play-services-location:16.0.0'
        //  implementation 'com.google.android.libraries.places:places:1.0.0'
        // Initializare Places.
//        Places.initialize(getApplicationContext(), "AIzaSyCuQF16owHJdPiR_GNPTNR6An4dALLrtK8", new Locale("ro"));
//
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

//        //4. Facem cautarea pe harta a unui loc
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                coordinates = place.getLatLng();
//                moveToSelectedArea(coordinates);
//                locationContentUrl = "https://www.google.com/maps/search/?api=1&query=Google&query_place_id=" + place.getId();
//
//                final EditText messageText = (EditText) findViewById(R.id.message);
//                String messageContent = messageText.getText().toString();
//
//                if (isLocationSet && messageContent.length() > 0) {
//                    int locationStartIndex = messageContent.indexOf("https");
//                    if (locationStartIndex != -1) {
//                        messageText.setText(messageContent.substring(0, locationStartIndex));
//                    }
//                }
//
//                String prefix = messageText.getText().length() == 0 ? "" : messageText.getText().toString() + ": ";
//                messageText.setText(prefix + locationContentUrl);
//                isLocationSet = true;
//            }
//
//            @Override
//            public void onError(Status status) {
//                Log.i("INFO", "A aparut o eroare: " + status);
//            }
//        });

//        //4. Obtinerea unui SupportMapFragment si notificare cand harta este pregatita de utilizare.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    // 2. Functie necesara pentru startActivityForResult()
//    @Override
//    public void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
//        if (reqCode == 99) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri contactData = data.getData();
//                Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
//                if (cursor.moveToFirst()) {
//                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                    String hasNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//                    String contactPhoneNumber;
//                    if (Integer.valueOf(hasNumber) >= 1) {
//                        Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
//                        while (numbers.moveToNext()) {
//                            contactPhoneNumber = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                            String name = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                            phoneNumbersMap.put(name, contactPhoneNumber);
//                        }
//                    }
//                }
//            }
//        }
//        phoneNumber.setText(phoneNumbersMap.keySet().toString());
//    }

    // 3.
    private boolean checkPermission() {

    }

//    // 3.
//    private void requestPermission() {
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]
//                {
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.SEND_SMS
//                }, PERMISSION_REQUEST_CODE);
//    }
//
//    // 3. Adauga functia onRequestPermissionsResult()
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(MainActivity.this,
//                            "Permission accepted", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Toast.makeText(MainActivity.this,
//                            "Permission denied", Toast.LENGTH_LONG).show();
//                    Button sendSMS = (Button) findViewById(R.id.sendSMS);
//                    sendSMS.setEnabled(false);
//                }
//                break;
//        }
//    }

//    // 4. Functie necesara pentru manipularea hartii si evenimente (pin, listeneri....)
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        LatLng bucuresti = new LatLng(44.427147, 26.102443);
//        //TODO Incercam si cu locatia curenta
//        //Adaugam un pin pe Bucuresti
//        mMap.addMarker(new MarkerOptions().position(bucuresti).title("Bucuresti"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bucuresti, 17));
//
//        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//                // Crearea unui pin
//                MarkerOptions markerOptions = new MarkerOptions();
//
//                // Adaugam pozitia pinului
//                markerOptions.position(latLng);
//
//                // Adaugam un titlu pentru pin
//                // Se va afisa cand se apasa pe pin
//                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
//
//                // Stergem pinul vechi
//                mMap.clear();
//
//                // Mutam camera pe pozitia pinului
//                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//                // Adaugam pinul pe harta
//                mMap.addMarker(markerOptions);
//
//                locationContentUrl = "https://www.google.com/maps/search/?api=1&query=" + markerOptions.getPosition().latitude + "," + markerOptions.getPosition().longitude;
//
//                final EditText messageText = (EditText) findViewById(R.id.message);
//                String messageContent = messageText.getText().toString();
//
//                if (isLocationSet && messageContent.length() > 0) {
//                    int locationStartIndex = messageContent.indexOf("https");
//                    if (locationStartIndex != -1) {
//                        messageText.setText(messageContent.substring(0, locationStartIndex));
//                    }
//                }
//
//                String prefix = messageText.getText().length() == 0 ? "" : messageText.getText().toString() + ": ";
//                messageText.setText(prefix + locationContentUrl);
//                isLocationSet = true;
//            }
//        });
    }
//
//    // 4. Functie pentru schimbarea locatiei
//    private void moveToSelectedArea(LatLng position) {
//        if (mMap != null) {
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(position);
//            markerOptions.title(position.latitude + " : " + position.longitude);
//            mMap.clear();
//            mMap.addMarker(markerOptions);
//
//            CameraUpdate center =
//                    CameraUpdateFactory.newLatLng(new LatLng(position.latitude, position.longitude));
//            CameraUpdate zoom = CameraUpdateFactory.zoomTo(18);
//
//            mMap.moveCamera(center);
//            mMap.animateCamera(zoom);
//        }
//    }
}