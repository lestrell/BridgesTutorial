package model.earthquake;

import org.apache.http.client.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PADINGTON on 1/31/2017.
 */
public class Earthquake
{

    public String getLabel() {
        return  "<img src='"+mapURl+"' alt='"+properties.getTitle()+"' width='100%'>\n" +
                "<hr style='margin:5px;'>"+
                "<b>Title: </b> " + properties.getTitle() + "\n" +
                "<b>Magnitude: </b> " + properties.getMag() + "\n" +
                "<b>Place: </b> " + getProperties().getPlace() + "\n"+
                "<b>Date: </b> " + new SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss a").format(new Date(Long.parseLong(getProperties().getTime())));
    }

    private String mapURl;

    public String getMapURl() {
        return mapURl;
    }

    public void setMapURl(String mapURl) {
        this.mapURl = mapURl;
    }

    private String id;

    private String _id;

    private Properties properties;

    private String type;

    private Geometry geometry;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public Properties getProperties ()
    {
        return properties;
    }

    public void setProperties (Properties properties)
    {
        this.properties = properties;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }

}