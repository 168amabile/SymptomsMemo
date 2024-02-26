package model;

public class Mutter {
    private String jikan;
    private String syoujyou;
    private String koudou;

    // コンストラクタ
    public Mutter() {
    }
    
    // 引数を受け取るコンストラクタ
    public Mutter(String jikan, String syoujyou, String koudou) {
        this.jikan = jikan;
        this.syoujyou = syoujyou;
        this.koudou = koudou;
    }

    // jikanのgetterとsetter
    public String getJikan() {
        return jikan;
    }

    public void setJikan(String jikan) {
        this.jikan = jikan;
    }

    // syoujyouのgetterとsetter
    public String getSyoujyou() {
        return syoujyou;
    }

    public void setSyoujyou(String syoujyou) {
        this.syoujyou = syoujyou;
    }

    // koudouのgetterとsetter
    public String getKoudou() {
        return koudou;
    }

    public void setKoudou(String koudou) {
        this.koudou = koudou;
    }
}
