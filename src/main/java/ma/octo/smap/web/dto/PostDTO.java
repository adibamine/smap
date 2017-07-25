package ma.octo.smap.web.dto;

import java.net.URL;

/**
 * Created by adib on 15/04/17.
 */
public class PostDTO {

    private String link;
    private String picture;
    private String name;
    private String caption;
    private String description;

    public PostDTO() {}

    public PostDTO(String link, String picture, String name, String caption, String description) {
        this.link = link;
        this.picture = picture;
        this.name = name;
        this.caption = caption;
        this.description = description;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCaption() { return caption; }

    public void setcaption(String caption) { this.caption = caption; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

}
