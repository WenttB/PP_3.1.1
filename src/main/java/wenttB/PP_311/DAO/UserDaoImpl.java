package wenttB.PP_311.DAO;

import org.springframework.stereotype.Repository;
import wenttB.PP_311.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void saveUser (User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> allUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u",User.class);
        return query.getResultList();

    }
    public void update (long id,User upUser) {
        User updateUser = show(id);
        updateUser.setName(upUser.getName());
        updateUser.setSurname(upUser.getSurname());
        updateUser.setEmail(upUser.getEmail());

    }
    public User show (long id) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.id =:id", User.class)
                .setParameter("id",id);
        return (User) query.getSingleResult();
    }
    public void delete (long id) {
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id =:id").setParameter("id",id);
                query.executeUpdate();

    }
}
