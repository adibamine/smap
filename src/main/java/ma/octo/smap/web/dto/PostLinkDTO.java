package ma.octo.smap.web.dto;

/**
 * Created by adib on 15/04/17.
 */
public class PostLinkDTO {

    private String link;
    private String title;

    public PostLinkDTO() {}

    public PostLinkDTO(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }


}
