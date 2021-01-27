package pl.law_case;

import lombok.*;
import pl.client.Client;
import pl.court.Court;
import pl.law_case.activity.Activity;
import pl.law_case.notes.Notes;
import pl.user.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "law_case")
@ToString
public class LawCase {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "law_case_id")
   private Long id;

   @ManyToMany
   @JoinTable(name = "workers",
      joinColumns = @JoinColumn(name = "id_law_case"),
      inverseJoinColumns = @JoinColumn(name = "id_worker"))
   private Set<User> workers;

   @ManyToMany
   @JoinTable(name = "clients",
      joinColumns = @JoinColumn(name = "id_law_case"),
      inverseJoinColumns = @JoinColumn(name = "id_client"))
   private Set<Client> clients;

   @OneToMany(mappedBy = "lawCase")
   private List<Activity> activities;

   @OneToOne
   @MapsId
   private Court court;

   private String signature;

   @Enumerated
   private Priority priority;

   @OneToMany(mappedBy = "lawCase")
   private List<Notes> notes;


   @Builder
   public LawCase(Long id, Set<User> workers, Set<Client> clients, List<Activity> activities, Court court, String signature, Priority priority) {
      this.id = id;
      this.workers = workers;
      this.clients = clients;
      this.activities = activities;
      this.court = court;
      this.signature = signature;
      this.priority = priority;
   }

   public void addWorker(User worker){
      if(workers==null)
         workers = new HashSet<User>(Collections.singleton(worker));
      else
         workers.add(worker);
   }
}