package murach.business;

import java.io.Serializable;
import javax.persistence.*; // Import các thư viện JPA cần thiết

@Entity // 1. Đánh dấu class là một thực thể JPA
@Table(name = "user") // Tùy chọn: Đặt tên bảng trong database
public class User implements Serializable {

    // Khai báo trường khóa chính (Bắt buộc cho JPA)
    private long userId;

    private String firstName;
    private String lastName;
    private String email;

    // Constructors và logic khác giữ nguyên
    public User() {
        // userId = 0; // Tùy chọn: Khởi tạo khóa chính
        firstName = "";
        lastName = "";
        email = "";
    }

    // Constructor dùng để tạo đối tượng mới (không cần userId)
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Thêm Getter và Setter cho trường khóa chính userId
    @Id // 2. Đánh dấu đây là Khóa chính (trên Getter)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Cấu hình tự động tạo giá trị
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    // Các Getter/Setter khác (Chuyển chú thích @Column/tên cột DB nếu cần)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // @Column(name="EmailAddress") // Ví dụ: Nếu bạn muốn thay đổi tên cột DB
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}