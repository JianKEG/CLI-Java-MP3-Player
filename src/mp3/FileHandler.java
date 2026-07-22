package mp3;

import java.io.*;

public class FileHandler {
    public static void savePlaylist(SongList list){
        try(FileWriter writer = new FileWriter("playlist.txt")){
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

    public static void loadPlaylist(SongList list){
        System.out.println("Loading playlist...");

        File file = new File("playlist.txt");

        if(!file.exists()){
            System.out.println("Playlist not found!");
            System.out.println("A new empty playlist has been created.");
            return;
        }


        try(FileReader fr = new FileReader("playlist.txt"); BufferedReader br = new BufferedReader(fr)){
            String line;

            System.out.println("Loading playlist...");

            while ((line = br.readLine()) != null){
                String[] data = line.split("\\|");

                if(data.length == 4) {
                    SongList.insert(list, data[0], data[1], data[2], data[3]);
                }
            }

            System.out.println("Playlist successfully loaded.");

        }catch (IOException e) {
            System.out.println("An error occured while loading the playlist: " + e.getMessage());
        }
    }
}
