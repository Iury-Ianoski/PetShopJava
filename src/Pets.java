public class Pets {
    private String breed;
    private String name;
    private int age;
    private String description;

    public Pets(String breed, String name, int age, String description) {
        this.breed = breed;
        this.name = name;
        this.age = age;
        this.description = description;
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
