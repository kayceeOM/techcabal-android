package onyekachi.me.techcabal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Onyekachy on 31/12/2014.
 */
public class Response {

    ArrayList<Article> articles;
    String next;

    public Response() { }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
