package android.example.mytripnotes.model;

public class PackingModel {
    private int id;
    private String item;
    private int id_activitas;

    public PackingModel(int id, String item, int id_activitas) {
        this.id = id;
        this.item = item;
        this.id_activitas = id_activitas;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getId_activitas() {
        return id_activitas;
    }
}
