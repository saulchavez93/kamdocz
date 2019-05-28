package org.saulchavez.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SingleNews {

    private NewsType type;
    private String title;
    private String content;

    public SingleNews(NewsType type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public static List<SingleNews> fromFile(String filepath){
        List<SingleNews> news = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(System.getProperty("user.dir"),filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] newsComponents = line.split("\\|");
                news.add(new SingleNews(
                                NewsType.valueOf(newsComponents[0]),
                                newsComponents[1],
                                newsComponents[2]
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public String toString(){
        return getType() + " - " + getTitle() + " - " + getContent();
    }

    public NewsType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
