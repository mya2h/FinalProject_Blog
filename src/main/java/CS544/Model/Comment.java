package CS544.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    @NotBlank(message = "description must not be blank")
    @Size(max=1000)
    private String description;

    //@NotNull(message = "user must not be null")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="comment_author")
    @Valid
    private User author;

   // @NotNull(message = "post must not be null")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "comment_post")
    @Valid
    private Post post;
    private final LocalDate date = LocalDate.now();

}
