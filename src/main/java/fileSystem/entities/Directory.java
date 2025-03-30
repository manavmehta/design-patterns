package fileSystem.entities;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemEntity{
    public String name;
    public List<FileSystemEntity> objects;

    public Directory(String name) {
        this.objects = new ArrayList<>();
        this.name = name;
    }

    public void ls(){
        System.out.println("directory: " + name);
        for (var obj: objects)
                obj.ls();
    }
}
