package android.example.mytripnotes;

public class UserModel {
    public String id;
    public String nama;
    public String email;
    public String tlp;

    public UserModel() {
    }

    public UserModel(String id, String nama, String email, String tlp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.tlp = tlp;
    }
}
