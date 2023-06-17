package CS544.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max=1000)
    private String description;

    @NotNull
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="comment_author")
    private User author;

    @NotNull
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "comment_post")
    private Post post;
    private final LocalDate date = LocalDate.now();
    @AssertTrue(message = "Invalid Comment")
    public boolean isValidComment(){
        if(description==null||description.trim().isEmpty()){return false;}
        if(author==null){return false;}
        if(post==null){return false;}
        return true;
    }
}
