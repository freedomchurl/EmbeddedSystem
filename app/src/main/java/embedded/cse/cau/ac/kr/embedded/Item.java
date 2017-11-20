package embedded.cse.cau.ac.kr.embedded;

/**
 * Created by Inhee on 2017-11-21.
 */

public class Item {
    int image;
    String imagetitle;

    public int getImage() {
        return image;
    }

    public String getImagetitle() {
        return imagetitle;
    }
    public Item(int image, String imagetitle){
        this.image=image;
        this.imagetitle=imagetitle;
    }
}

