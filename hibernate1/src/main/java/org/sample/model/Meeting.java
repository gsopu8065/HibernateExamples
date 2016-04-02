package org.sample.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by srujangopu on 4/2/16.
 */

@Entity
@Table(name = "MEETING")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int meetingId;

    @Column(name = "meetingName")
    private String meetingName;

    @Column(name = "roomNo")
    private String roomNo;

    @Column(name = "host")
    private String host;

    @ManyToMany(mappedBy="meetings")
    private Set<Student> students = new HashSet<Student>();

    public Meeting() {
    }

    public Meeting(String meetingName, String roomNo, String host) {
        this.meetingName = meetingName;
        this.roomNo = roomNo;
        this.host = host;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
