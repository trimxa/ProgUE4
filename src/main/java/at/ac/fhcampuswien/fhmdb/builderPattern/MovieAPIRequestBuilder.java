package at.ac.fhcampuswien.fhmdb.builderPattern;

import at.ac.fhcampuswien.fhmdb.models.Genre;

public class MovieAPIRequestBuilder {

    private StringBuilder baseURL;
    private boolean first = true;
    public MovieAPIRequestBuilder(String baseURL){
        this.baseURL = new StringBuilder(baseURL);
    }

    public MovieAPIRequestBuilder query(String query){
        if(query == null || query.isEmpty()){
            return this;
        }
        this.appendDelimiter();
        this.baseURL.append("query=").append(query);
        return this;
    }

    public MovieAPIRequestBuilder genre(Genre genre){
        if(genre == null){
            return this;
        }
        this.appendDelimiter();
        this.baseURL.append("genre=").append(genre);
        return this;
    }
    public MovieAPIRequestBuilder releaseYear(String releaseYear){
        if(releaseYear == null || releaseYear.isEmpty()){
            return this;
        }
        this.appendDelimiter();
        this.baseURL.append("releaseYear=").append(releaseYear);
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(String ratingFrom){
        if(ratingFrom == null || ratingFrom.isEmpty()){
            return this;
        }
        this.appendDelimiter();
        this.baseURL.append("ratingFrom=").append(ratingFrom);
        return this;
    }

    public String build(){
        return this.baseURL.toString();

    }

    private void appendDelimiter(){
        if (first){
            this.baseURL.append("?");
            first = false;
        } else {
            this.baseURL.append("&");
        }
    }
}
