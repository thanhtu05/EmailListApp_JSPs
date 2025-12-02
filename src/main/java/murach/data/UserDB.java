package murach.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import murach.business.User;
// import org.hibernate.exception.ConstraintViolationException; // Bỏ qua import này

public class UserDB {

    public static void insert(User user) throws Exception { // <-- KHAI BÁO NÉM NGOẠI LỆ
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            // Ném ngoại lệ lên để đảm bảo tính toàn vẹn dữ liệu
            throw e;
        } finally {
            em.close();
        }
    }
}