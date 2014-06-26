import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class TocHw3 {
	

	public static void main(String[] argv) throws IOException, JSONException
	{
		String jsstring = getUrlSource(argv[0]); //Url
		JSONArray arr = new JSONArray(jsstring);
		int count=0,total=0; //計數和總值
		String syear = argv[3]+"00";
		int year = Integer.parseInt(syear);
		for(int i=0;i<arr.length();i++)
		{
			JSONObject obj =arr.getJSONObject(i);
			if(obj.getString("鄉鎮市區").equals(argv[1])&&obj.getInt("交易年月")>year)
			{
					String add=obj.getString("土地區段位置或建物區門牌");
					if(add.indexOf(argv[2])!=-1)
					{
						total=total+obj.getInt("總價元");
						count++;
					}
			}
		}
		System.out.println("Output:\n"+total/count);
	}


        private static String getUrlSource(String url) throws IOException {
        URL connect = new URL(url);
        URLConnection ucc = connect.openConnection();
        BufferedReader read = new BufferedReader(new InputStreamReader(ucc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = read.readLine()) != null)
            a.append(inputLine);
        read.close();
        return a.toString();
        }
}