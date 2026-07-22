package mp3;

/*
Developers:
Afable, David Walter C.
Asidao, Justin Aquiles O.
Gapatan, Jian Kristian E.
 */

public class Main {
    public static void main(String[] args) {
        SongList list = new SongList();

    }
}

/*
============================
        REFERENCES
============================

---------------------------
        1. SETUP
---------------------------
Instantiate the playlist first inside main;
    for example: SongList list = new SongList();

    then the 'list' will be the first argument into all the methods below, ikaw n bahala if you want to change 'list'.

---------------------------
2. Adding & Deleting songs
---------------------------

>> SongList.insert(list, "title", "artist", "duration", "album");
    -- Make sure that the duration is a string and in total seconds, this will be automatically formatted into (H:MM:SS) or (MM:SS)

>> SongList.deleteByKey(list, "key");
    -- This deletes ALL songs that will match the title, artist, or album

>> SongList.deleteById(list, id);
    -- Deletes a specific song by its number. So when you delete 1. it will delete the first song in the list.

---------------------------
    3. Display Playlist
---------------------------
>> SongList.printSongList(list);
    -- Prints the entire playlist

>> SongList.printFirstSong(list);
    -- Prints the first song in the playlist

>> SongList.printLastSong(list);
    -- Prints the last song in the playlist

>> SongList.search(list, "key")
    -- Searches for key and prints the matching song to the key and its details

---------------------------
4. Metrics/Overview (Totals)
---------------------------
>> SongList.countSongs(list);
    -- This will return an int of total number of songs sa playlist.

>> SongList.totalDuration(list);
    -- This will return a formatted string of the total duration or runtime of the songs in the playlist.

---------------------------
 5. Navigation & Playback
---------------------------
>> SongList.playCurrentSong(list);
    -- This will print "Now Playing: [Song deets]". Auto-select narin sa first song when starting the program.

>> SongList.selectCurrentSong(list);
    -- Same as playCurrentSong, but prints "Current Song: [Song deets]" instead.

>> SongList.nextSong(list);
    -- Skips to the next song.

>> SongList.prevSong(list);
    -- Skips to the previous song.

---------------------------
    6. Saving & Loading
---------------------------
>> FileHandler.loadPlaylist(list);
    -- In the "Sample Console Outputs" in Sir Macam's README.md file,
       I implemented here the "Starting the program" console output, call this once when starting the program before the menu loop.

>> FileHandler.savePlaylist(list);
    -- Writes the current playlist in a txt file (playlist.txt).
       Call this once when exiting or terminating the program.
============================================================================================================================================
 */



