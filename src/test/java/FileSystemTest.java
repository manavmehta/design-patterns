import fileSystem.entities.Directory;
import fileSystem.entities.File;
import org.junit.jupiter.api.Test;

public class FileSystemTest {
    @Test
    void ls(){
        var dir = new Directory("Movie");

        var english = new Directory("English Movies");
        english.objects.add(new File("Memento"));
        dir.objects.add(english);
        // This will also print because english is being passed by reference
        english.objects.add(new File("Spider Man"));

        var hindi = new Directory("Hindi Movies");
        hindi.objects.add(new File("3 Idiots"));
        dir.objects.add(hindi);

        dir.ls();
    }
}
