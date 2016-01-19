package de.tzeidler.myvideos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * 
 * 
 * @author Toni Zeidler (email: "")
 */
@Stateless
public class MyVideoService {
    
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * Add a new video into database
     * 
     * @param myvideo
     */
    public void createNewVideo(MyVideo myvideo){
        em.persist(myvideo);
    }
    
    /**
     *  Get a List from database with all MyVideo objects
     * 
     * @return - Return a List from database with MyVideo objects
     */
    public List<MyVideo> getAllVideos(){
        TypedQuery<MyVideo> query = em.createQuery("select mv from MyVideo mv", MyVideo.class);
        return query.getResultList();
    }
    
    /**
     * Get false if query.getResultList() = NULL
     * 
     * @return 
     */
    public boolean isEmpty(){
        return getAllVideos().isEmpty();
    }
    
}
