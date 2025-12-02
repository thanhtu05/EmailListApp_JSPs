package murach.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    // Tên của Persistence Unit (như đã khai báo trong persistence.xml)
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("murach_jpa_unit");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}