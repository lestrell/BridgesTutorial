package util;

import com.sun.istack.internal.Nullable;

/**
 * Created by PADINGTON on 1/31/2017.
 */
public class GoogleMapStaticApi {

    static final String baseUrl = "https://maps.googleapis.com/maps/api/staticmap?center=";

    public static String getStaticMapUrl(String lat, String lon){
        return baseUrl + lat + "," + lon +
               "&zoom=12&scale=1&size=600x300&maptype=roadmap&format=jpg&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:1%7C"
               +lat + "," + lon;
    }
}
