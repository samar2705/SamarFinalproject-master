package comsdwecv.example.hp1.finalproject;

/**
 * Created by Hp1 on 27/09/2017.
 */

public class Item {
    private String description;
    private int imageId;

    public Item(String description, int imageId) {
        this.description = description;
        this.imageId = imageId;
    }

    public void setTitle(String title) {
        this.description = title;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {

        return description;
    }

    public int getImageId() {
        return imageId;
    }
}
