package com.nhahv.lovecoupon.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nhahv.lovecoupon.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.facebook.GraphRequest.TAG;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.FAILURE_RESULT;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.LOCATION_DATA_EXTRA;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.RECEIVER;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.RESULT_DATA_KEY;
import static com.nhahv.lovecoupon.util.Constant.LocationAddress.SUCCESS_RESULT;

public class LocationAddressService extends IntentService {
    protected ResultReceiver mReceiver;

    public LocationAddressService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String errorMessage = "";
        mReceiver = intent.getParcelableExtra(RECEIVER);
        if (mReceiver == null) {
            Log.wtf(TAG, "No receiver received. There is nowhere to send the results.");
            return;
        }
        Location location = intent.getParcelableExtra(LOCATION_DATA_EXTRA);
        if (location == null) {
            errorMessage = getString(R.string.no_location_data_provided);
            Log.wtf(TAG, errorMessage);
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
            return;
        }
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses =
                geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException ioException) {
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG,
                errorMessage + ". " + "Latitude = " + location.getLatitude() + ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(SUCCESS_RESULT, address.getAddressLine(5));
        }
    }

    private void deliverResultToReceiver(int resultCode, @NonNull String message) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }
}
