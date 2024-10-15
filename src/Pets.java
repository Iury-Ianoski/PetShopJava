import java.io.Serial;
import java.io.Serializable;

public class Pets implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String species;
    private String breed;
    private String name;
    private int age;
    private String description;

    public Pets(String species, String breed, String name, int age, String description)  {
        this.species = species;
        this.breed = breed;
        this.name = name;
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return  "Nome: " + name + "\n" +
                "Idade: " + age + "\n" +
                "Espécie: " + species + "\n" +
                "Raça: " + breed + "\n" +
                "Descrição: " + description;

    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
