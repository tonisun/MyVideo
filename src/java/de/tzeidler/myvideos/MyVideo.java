package de.tzeidler.myvideos;

import java.io.Serializable;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Toni Zeidler
 */
@Entity
@Table(name="myvideo")
public class MyVideo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String info;
    private GENRES genres;
    private int stars;
    private SerialBlob file;
    private String videopath;

    /**
     * Constructor
     * 
     * @param title - Zoro
     * @param info - This is my best film ever.
     * @param genres - Western 
     * @param stars - 5 from 5
     * @param file - Zoro Cavar
     * @param videopath - in the Kichen
     */
    public MyVideo(String title, String info, GENRES genres, int stars, SerialBlob file, String videopath) {
        this.title = title;
        this.info = info;
        this.genres = genres;
        this.stars = stars;
        this.file = file;
        this.videopath = videopath;
    }

    /**
     * Default Constructor
     */
    public MyVideo() {
    }
    
    /**
     * Get the film title
     * 
     * @return 
     */
    public String getTitel() {
        return title;
    }

    /**
     * Set the film title
     * 
     * @param titel 
     */
    public void setTitel(String titel) {
        this.title = titel;
    }

    /**
     * Get describtion about the film.
     * 
     * @return 
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set description about the film
     * 
     * @param info 
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @see GENRES
     * 
     * @return 
     */
    public GENRES getGenres() {
        return genres;
    }

    /**
     * @see GENRES
     * 
     * @param genres 
     */
    public void setGenres(GENRES genres) {
        this.genres = genres;
    }

    /**
     * And Oscar gos to ....5 Stars Film :-D
     * 
     * @return 
     */
    public int getStars() {
        return stars;
    }

    /**
     * Set the stars
     * 
     * @param stars 
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * Get the film cover image
     * 
     * @return 
     */
    public SerialBlob getFile() {
        return file;
    }

    /**
     * SET the film cover image
     * 
     * @param file 
     */
    public void setFile(SerialBlob file) {
        this.file = file;
    }

    /**
     * Where TF is my DVD ?
     * In the Badroom ? 
     * It wasn't me! :-D
     * 
     * @return 
     */
    public String getPath() {
        return videopath;
    }

    /**
     * 
     * 
     * @param path 
     */
    public void setPath(String path) {
        this.videopath = path;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyVideo)) {
            return false;
        }
        MyVideo other = (MyVideo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.tzeidler.myvideos.MyVideos[ id=" + id + " ]";
    }
    
}
