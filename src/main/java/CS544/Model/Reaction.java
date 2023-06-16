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
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean reacted;
    @ManyToOne
    private User reactor;
    @ManyToOne
    @JoinColumn(name = "reaction_post")
    private Post post;
    private final LocalDate date = LocalDate.now();
}
