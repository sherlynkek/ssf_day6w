package sg.edu.nus.iss.vttp5_day6w.model;

public class BoardGames {
    private Integer id;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer userRated;
    private String url;
    private String imageUrl;
    

    public BoardGames(Integer id, String name, Integer year, Integer ranking, Integer userRated, String url,
            String imageUrl) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.userRated = userRated;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public BoardGames() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUserRated() {
        return userRated;
    }

    public void setUserRated(Integer userRated) {
        this.userRated = userRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
}
