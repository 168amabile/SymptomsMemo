package model;

public class Symptom {
    private int id;
    private String name;
    private String pass;
    private String jikan;
    private String syoujyou;
    private String koudou;

    public Symptom(int id, String name, String pass, String jikan, String syoujyou, String koudou) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.jikan = jikan;
        this.syoujyou = syoujyou;
        this.koudou = koudou;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getJikan() {
        return jikan;
    }

    public void setJikan(String jikan) {
        this.jikan = jikan;
    }

    public String getSyoujyou() {
        return syoujyou;
    }

    public void setSyoujyou(String syoujyou) {
        this.syoujyou = syoujyou;
    }

    public String getKoudou() {
        return koudou;
    }

    public void setKoudou(String koudou) {
        this.koudou = koudou;
    }
}
