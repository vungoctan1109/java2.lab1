package com.t2012e.worker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DemoWorker {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArticleModel model = new ArticleModel();
        ArrayList<String> listUrl = getListUrl();
        ArrayList<ArticleThread> listThread = new ArrayList<>();
        for (int i = 0; i < listUrl.size(); i++) {
            ArticleThread articleThread = new ArticleThread(listUrl.get(i));
            listThread.add(articleThread);
            articleThread.start();
        }
        for (int i = 0; i < listThread.size(); i++) {
            try {
                listThread.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < listThread.size(); i++) {
            model.insert(listThread.get(i).getArticle());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "mls.");
    }

    private static ArrayList<String> getListUrl(){
        ArrayList<String> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://vnexpress.net/the-thao").get();
            Elements els = doc.select(".title-news a");
            if (els.size() > 0) {
                for (int i = 0; i < els.size(); i++) {
                    list.add(els.get(i).attr("href"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Got %d links.", list.size());
        return list;
    }
}
