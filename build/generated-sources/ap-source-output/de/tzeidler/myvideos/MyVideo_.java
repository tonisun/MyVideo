package de.tzeidler.myvideos;

import de.tzeidler.myvideos.GENRES;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.sql.rowset.serial.SerialBlob;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-01-18T12:09:42")
@StaticMetamodel(MyVideo.class)
public class MyVideo_ { 

    public static volatile SingularAttribute<MyVideo, SerialBlob> file;
    public static volatile SingularAttribute<MyVideo, GENRES> genres;
    public static volatile SingularAttribute<MyVideo, Long> id;
    public static volatile SingularAttribute<MyVideo, Integer> stars;
    public static volatile SingularAttribute<MyVideo, String> title;
    public static volatile SingularAttribute<MyVideo, String> info;
    public static volatile SingularAttribute<MyVideo, String> videopath;

}