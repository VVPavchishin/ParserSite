public class Poster {
    private String namePoster;
    private String urlPoster;
    private String creationDate;

    public Poster(String namePoster, String urlPoster, String creationDate) {
        this.namePoster = namePoster;
        this.urlPoster = urlPoster;
        this.creationDate = creationDate;
    }

    public String getNamePoster() {
        return namePoster;
    }

    public void setNamePoster(String namePoster) {
        this.namePoster = namePoster;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "namePoster='" + namePoster + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
