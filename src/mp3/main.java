package mp3;

import java.io.*;
import java.util.*;

public class main{
    public static void main(String[] args) {
        songList list = new songList();

        songList.insert(list,"Leaves", "Ben & Ben", "320", "album1");
        songList.insert(list,"Atlas", "Keshi", "320", "album1");
        songList.insert(list,"Bago", "lolo", "320", "album2");
        songList.insert(list,"Bagogg", "lolo", "320", "album23");

        songList.printSongList(list);
    }
}


