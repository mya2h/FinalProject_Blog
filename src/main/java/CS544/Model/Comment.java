package CS544.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="comment_author")
    private User author;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "comment_post")
    private Post post;
    private final LocalDate date = LocalDate.now();
}
