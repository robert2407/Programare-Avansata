package org.example;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.title"),
        @NamedQuery(name = "Album.findByArtist",
                query = "select e from Album e where e.artist = ?1")
})
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    private Artist artist;
    @ManyToMany
    private List<Album> genres = new ArrayList<>();
    @JoinColumn(name = "artist_id", referencedColumnName = "id")

    @Column(name = "title")
    private String title;

    public Album() {
    }

    public Album(Artist artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public Integer getId()    {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
