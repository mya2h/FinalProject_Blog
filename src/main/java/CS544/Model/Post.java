package CS544.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter

@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "title must not be blank")
    private String title;
    @Lob
    @NotBlank(message = "description must not be blank")
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="post_author")
    @Valid
   // @NotNull(message = "User must not be null")
    private User author;

    private final LocalDate date = LocalDate.now();

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
