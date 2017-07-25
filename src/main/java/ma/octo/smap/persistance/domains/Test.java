package ma.octo.smap.persistance.domains;

/**
 * Created by adib on 27/04/17.
 */
public class Test {

    private int id;
    private String name, image;


    public Test(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
