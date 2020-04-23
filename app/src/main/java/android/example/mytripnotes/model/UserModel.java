package android.example.mytripnotes.model;

public class UserModel {
    private String id;
    private String nama;
    private String email;
    private String tlp;

    public UserModel(String id, String nama, String email, String tlp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.tlp = tlp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTlp() {
        return tlp;
    }
}
