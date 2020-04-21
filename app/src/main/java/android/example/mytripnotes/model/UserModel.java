package android.example.mytripnotes.model;

public class UserModel {
    public String id;
    private String nama;
    private String email;
    private String tlp;

    public UserModel() {
    }

    public UserModel(String id, String nama, String email, String tlp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.tlp = tlp;
    }
}
