package util;

import com.sun.istack.internal.Nullable;

/**
 * Created by PADINGTON on 1/31/2017.
 */
public class YandexStaticMap {

    //http://static-maps.yandex.ru/1.x/?lang=en-US&ll=-152.0067,61.511&%20\%20%20size=450,450&z=10&l=map&pt=-152.0067,61.511,pm2rdl1~32.870152,39.869847,pm2rdl99
    final static String baseUrl = "http://static-maps.yandex.ru/1.x/?lang=en-US&ll=";
    private static String $zoom;
    private static String $size;
    private static String $latitude ;
    private static String $longitude;

    public YandexStaticMap(){

    }

    public YandexStaticMap reset(){
        $zoom = "13";
        $size = "320,240";
        $latitude = null;
        $longitude = null;
        return this;
    }

    public static String build() {
        return baseUrl + $latitude + "," + $longitude + "&z="+$zoom+"&l=sat,skl&size="+$size;
    }

    public YandexStaticMap setZoom(@Nullable String zoom) {
        if(zoom != null)$zoom = zoom.trim();
        return this;
    }

    public YandexStaticMap setSize(@Nullable String size) {
        if(size != null)$size = size.trim().toLowerCase().replace("x",",");
        return this;
    }

    public YandexStaticMap setLatitude(String latitude) {
        $latitude = latitude;
        return this;
    }

    public YandexStaticMap setLongitude(String longitude) {
        $longitude = longitude;
        return this;
    }
}
