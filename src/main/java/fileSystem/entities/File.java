package fileSystem.entities;

public class File implements FileSystemEntity{
    public String name;

    public File(String name) {
        this.name = name;
    }

    public void ls(){
        System.out.println("file: " + name);
    }
}
