import java.io.File;

//this class handles the file transfer faze and search of a file by a peer which finally gets instantiated in TCP Class
public class File_Transfer {
    private File directory;
    private File[] listOfFiles;

    public File_Transfer(String path){
        this.directory = new File(path);
        listOfFiles = directory.listFiles();
    }

    //checks if the requested file exists
    public boolean check(String address){
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName() == address)
                    return true;
            }
        }
        return false;
    }

}

