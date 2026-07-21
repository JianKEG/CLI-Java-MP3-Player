package mp3;

import java.io.*;

public class FileHandler {
    public static void savePlaylist(SongList list, String filename){
        try(FileWriter writer = new FileWriter(filename)){
            SongList.Node currNode = list.head;

            System.out.println("Saving playlist...");

            while(currNode != null){
                writer.write(currNode.title + "|" + currNode.artist + "|" + currNode.duration + "|" + currNode.album + System.lineSeparator());

                currNode = currNode.next;
            }

            System.out.println(SongList.countSongs(list) + " songs successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the playlist: " + e.getMessage());
        }
    }
}
