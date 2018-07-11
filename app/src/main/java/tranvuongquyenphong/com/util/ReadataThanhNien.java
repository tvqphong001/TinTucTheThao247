package tranvuongquyenphong.com.util;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tranvuongquyenphong.com.fragment.Frament_Main.fm_Home;
import tranvuongquyenphong.com.model.TinTuc;

public class ReadataThanhNien extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... strings) {
        return docNoiDung_Tu_URL(strings[0]);
    }

    // vn express
    @Override
    protected void onPostExecute(String s) {
        XMLDOMParser parser = new XMLDOMParser();
        Document document = parser.getDocument(s);
        NodeList nodeList = document.getElementsByTagName("item");
        NodeList nodeList1Desciption = document.getElementsByTagName("description");
        NodeList nodeListTitle = document.getElementsByTagName("title");
        // NodeList nodeListLink = document.getElementsByTagName("link");
        String title = "";
        String link = "";
        String hinhanh = "";
        String NguonBao = nodeListTitle.item(1).getTextContent();
        String Ngay = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            String cdata = nodeList1Desciption.item(i+1).getTextContent();
            int flag =0;
//                Element line = (Element) nodeList1Desciption.item(i+1);
//                String cdata = getCharacterDataFromElement(line);
            Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
            Pattern p1 = Pattern.compile("<img[^>]+data-original\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
            Matcher matcher = p.matcher(cdata);
            Matcher matcher1 = p1.matcher(cdata);
            //Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(getCharacterDataFromElement(line));

            if (matcher1.find())
            {
                hinhanh = matcher1.group(1);
                flag=1;

            }
            if  (matcher.find()&& flag==0)
            {

                hinhanh = matcher.group(1);
                //Log.d("hinhanh",hinhanh + ".........." +i);
            }

            Element element = (Element) nodeList.item(i);
            title = nodeListTitle.item(i+2).getTextContent();
            link = parser.getValue(element,"link");
            Ngay = parser.getValue(element,"pubDate");

//                try {
//                    NgayDang = FormatHelper.formatPubDate(Ngay.trim());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            Date NgayDang = FormatHelper.parseDate(Ngay);

            //Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
            TinTuc tinTuc = new TinTuc(title,link,hinhanh,NguonBao,NgayDang);
            //fm_Home.listTrungGian.add(tinTuc);

        }
        super.onPostExecute(s);

    }
    private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
