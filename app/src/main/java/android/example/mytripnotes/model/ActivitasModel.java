package android.example.mytripnotes.model;

public class ActivitasModel {
    private int id;
    private String activitas;

    public ActivitasModel(int id, String activitas) {
        this.id = id;
        this.activitas = activitas;
    }

    public int getId() {
        return id;
    }

    public String getActivitas() {
        return activitas;
    }
}
