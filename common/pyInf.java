import java.io.Serializable;

public class pyInf implements Serializable {
    public double quality;

    public pyInf(double quality, String fname, String uid, String matha, int lent, int hei, int maxi, int mini) {
        this.quality = quality;
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
        this.lent = lent;
        this.hei = hei;
        this.maxi = maxi;
        this.mini = mini;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public String fname;
    public String uid;
    public String matha="x=0";
    public int lent=10;
    public int hei=10;

    public pyInf() {
    }

    public pyInf(String fname, String uid, String matha, int lent, int hei, int maxi, int mini) {
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
        this.lent = lent;
        this.hei = hei;
        this.maxi = maxi;
        this.mini = mini;
    }

    public int getLent() {
        return lent;
    }

    public void setLent(int lent) {
        this.lent = lent;
    }

    public int getHei() {
        return hei;
    }

    public void setHei(int hei) {
        this.hei = hei;
    }

    public int getMaxi() {
        return maxi;
    }

    public void setMaxi(int maxi) {
        this.maxi = maxi;
    }

    public int getMini() {
        return mini;
    }

    public void setMini(int mini) {
        this.mini = mini;
    }

    public int maxi=10;
    public int mini=-10;

    public pyInf(String fname, String uid, String matha) {
        this.fname = fname;
        this.uid = uid;
        this.matha = matha;
    }

    public pyInf(String fname, String uid) {
        this.fname = fname;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "pyInf{" +
                "fname='" + fname + '\'' +
                ", uid='" + uid + '\'' +
                ", matha='" + matha + '\'' +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMatha() {
        return matha;
    }

    public void setMatha(String matha) {
        this.matha = matha;
    }
}
