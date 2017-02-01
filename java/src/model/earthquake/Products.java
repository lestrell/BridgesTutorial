package model.earthquake;

/**
 * Created by PADINGTON on 1/31/2017.
 */
public class Products
{
    private String[] String;

    public String[] getString ()
    {
        return String;
    }

    public void setString (String[] String)
    {
        this.String = String;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [String = "+String+"]";
    }
}