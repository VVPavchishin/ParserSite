import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SiteParser {

    public static Document getPage(String url, int numPages) {
        Document page = null;
        try {
            if (numPages == 1) {
                page = Jsoup.parse(new URL(url), 100000);
            } else {
                page = Jsoup.parse(new URL(url + "/page/" + numPages + "/"), 100000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }
    public static void main(String[] args) {
        int numPages = 200;
        String url = "https://kinoprofi.vip";
        List<Poster> posterList = new ArrayList<>();

        for (int i = 1; i <= numPages; i++) {

            Document page = getPage(url, i);
            Element box = page.select("div[id=dle-content]").first();
            Elements picture = box.select("div[class=sh-block ns]");


            for (Element el : picture) {
                String posterUrl = el.select("link[itemprop=image]").attr("href");
                String posterName = el.select("meta[itemprop=name]").attr("content");
                String posterDate = el.select("meta[itemprop=dateCreated]").attr("content");

                Poster poster = new Poster(posterName, posterUrl, posterDate);
                posterList.add(poster);
                loadToFolder(posterName, posterDate, posterUrl);
            }
        }
        System.out.println(posterList.size());
        posterList.forEach(post -> System.out.println(post + " " + post.getUrlPoster()));
    }

    private static void loadToFolder(String posterName, String posterDate, String posterUrl) {
        String folder = "E:\\Posters";
        String folderPath =  posterName.replaceAll("[:*?I]", "") + " " + posterDate;

        try {
            URL url = new URL(posterUrl);
            InputStream streamInput = url.openStream();
            OutputStream streamOutput = new FileOutputStream(folder + "\\" + folderPath + ".jpeg");
            byte[] buffer = new byte[2048];
            int length;

            while ((length = streamInput.read(buffer)) != -1) {
                streamOutput.write(buffer, 0, length);
            }
            streamInput.close();
            streamOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
