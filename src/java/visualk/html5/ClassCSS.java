package visualk.html5;

public class ClassCSS {

    private String id;
    private String color = "";
    private String left = "";
    private String top = "";
    private String bottom = "";
    private String right = "";
    private String position = "";
    private String padding = "";
    private String textalign = "";
    private String background = "";
    private String zindex = "";
    private String visible = "";
   
    private String height = "";

    public ClassCSS(String id) {
        this.id = id;
    }

    public ClassCSS() {
        this.id = new UniqueName(10).getName();
    }

    public String toHtml() {
        String temp;
        temp = "#" + id + "{";

       
        if (color != "") {
            temp += "color:" + color + ";";
        }
        if (left != "") {
            temp += "left:" + left + ";";
        }
        if (top != "") {
            temp += "top:" + top + ";";
        }

        if (background != "") {
            temp += "background:" + background + ";";
        }
        if (zindex != "") {
            temp += "z-index:" + zindex + ";";
        }


        if (height != "") {
            temp += "height:" + height + ";";
        }
        if (visible != "") {
            temp += "visibility:" + visible + ";";
        }

        if (right != "") {
            temp += "right:" + right + ";";
        }
        if (bottom != "") {
            temp += "bottom:" + bottom + ";";
        }

        if (position != "") {
            temp += "position:" + position + ";";
        }
        if (padding != "") {
            temp += "padding:" + padding + ";";
        }
        if (textalign != "") {
            temp += "text-align:" + textalign + ";";
        }

        return (temp + "}");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  

    public void setColor(String color) {
        this.color = color;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getTextalign() {
        return textalign;
    }

    public void setTextalign(String textalign) {
        this.textalign = textalign;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getZindex() {
        return zindex;
    }

    public void setZindex(String zindex) {
        this.zindex = zindex;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }
}
