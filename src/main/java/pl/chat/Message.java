package pl.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String from;
    private String content;
    private LocalDateTime createdDate;


    public Message(String name, String from, String content) {
        this.name = name;
        this.from = from;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

}
