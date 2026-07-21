package mp3;

public class Main {
    public static void main(String[] args) {
        SongList list = new SongList();

        SongList.insert(list,"Leaves", "Ben & Ben", "320", "album1");
        SongList.insert(list,"Atlas", "Keshi", "320", "album1");
        SongList.insert(list,"Bago", "lolo", "320", "album2");
        SongList.insert(list,"Bagogg", "lolo", "320", "album23");

        SongList.printSongList(list);
    }
}


