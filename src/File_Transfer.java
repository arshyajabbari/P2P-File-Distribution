import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//this class handles the file transfer faze and search of a file by a peer which finally gets instantiated in TCP Class
public class File_Transfer {
    private File directory;
    private File[] listOfFiles;
    private byte[] requestedFile;
    private FileInputStream fileInputStream;

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

    //returns the wanted file in byte array
    public byte[] getFile(String name) throws IOException {
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName() == name){
                    fileInputStream= new FileInputStream(file);
                    fileInputStream.read(requestedFile);
                    fileInputStream.close();
                }

            }
        }
        return requestedFile;
    }

}

