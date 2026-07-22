package mp3;
import java.util.*;
/*
Developers:
Afable, David Walter C.
Asidao, Justin Aquiles O.
Gapatan, Jian Kristian E.
 */

public class Main {
    public static void main(String[] args) {
        SongList list = new SongList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("\tSPOTIFEE PLAYER");
        System.out.println("================================");
        FileHandler.loadPlaylist(list);
 
        int choice;
        
        do {
            
            System.out.println("================================");
            System.out.println("\tSPOTIFEE PLAYER");
            System.out.println("================================");
            System.out.println("1. Add Song");
            System.out.println("2. Display Playlist");
            System.out.println("3. Play Current Song");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Search Song");
            System.out.println("7. Remove Song");
            System.out.println("8. Playlist Information");
            System.out.println("9. Save Playlist");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
 
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
 
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine().trim();
 
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine().trim();
 
                    System.out.print("Enter duration (in total seconds): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid duration. Please enter a whole number of seconds.");
                        scanner.next();
                        System.out.print("Enter duration (in total seconds): ");
                    }
                    int seconds = scanner.nextInt();
                    scanner.nextLine();
 
                    while (seconds < 0) {
                        System.out.print("Duration cannot be negative. Enter duration (in total seconds): ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid duration. Please enter a whole number of seconds.");
                            scanner.next();
                            System.out.print("Enter duration (in total seconds): ");
                        }
                        seconds = scanner.nextInt();
                        scanner.nextLine();
                    }
 
                    String duration = String.valueOf(seconds);
 
                    System.out.print("Enter album: ");
                    String album = scanner.nextLine().trim();
 
                    SongList.insert(list, title, artist, duration, album);
                    System.out.println("Song added successfully.");
                    break;
                
                case 2:
                    SongList.printSongList(list);
                    break;
 
                case 3:
                    SongList.playCurrentSong(list);
                    break;
 
                case 4:
                    SongList.nextSong(list);
                    break;
 
                case 5:
                    SongList.prevSong(list);
                    break;
                //Searching Song
                case 6:
                    System.out.print("Enter title, artist, or album to search: ");
                    String searchKey = scanner.nextLine().trim();
                    SongList.search(list, searchKey);
                    break;
                //Removing Song
                case 7:
                    System.out.println("Remove by: 1) Song number   2) Title/Artist/Album");
                    System.out.print("Enter choice: ");
 
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        scanner.next();
                        System.out.print("Enter choice: ");
                    }
                    int removeChoice = scanner.nextInt();
                    scanner.nextLine();
 
                    if (removeChoice == 1) {
                        SongList.printSongList(list);
                        System.out.print("Enter song number to delete: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid song number.");
                            scanner.next();
                            System.out.print("Enter song number to delete: ");
                        }
                        int id = scanner.nextInt();
                        scanner.nextLine();
 
                        SongList.deleteById(list, id);
                    } else if (removeChoice == 2) {
                        SongList.printSongList(list);
                        System.out.print("Enter title, artist, or album to delete: ");
                        String key = scanner.nextLine().trim();
                        SongList.deleteByKey(list, key);
                    } else {
                        System.out.println("Invalid choice. No song removed.");
                    }
                    break;
                //Playlist information
                case 8:
                    boolean inPlaylistInfoMenu = true;

                    while (inPlaylistInfoMenu) {
                        System.out.println("\n================================");
                        System.out.println("\tPLAYLIST INFORMATION");
                        System.out.println("================================");
                        System.out.println("1. Total Number of Songs");
                        System.out.println("2. Total Playlist Duration");
                        System.out.println("3. First Song");
                        System.out.println("4. Last Song");
                        System.out.println("5. Currently Selected Song");
                        System.out.println("0. Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        int playlistInfoChoice = scanner.nextInt();

                        switch (playlistInfoChoice) {
                            case 1:
                                System.out.println("\nTotal songs: " + SongList.countSongs(list));
                                break;
                            case 2:
                                System.out.println("\nTotal duration: " + SongList.totalDuration(list));
                                break;
                            case 3:
                                System.out.println();
                                SongList.printFirstSong(list);
                                break;
                            case 4:
                                System.out.println();
                                SongList.printLastSong(list);
                                break;
                            case 5:
                                System.out.println();
                                SongList.selectCurrentSong(list);
                                break;
                            case 0:
                                inPlaylistInfoMenu = false;
                                break;
                            default:
                                System.out.println("\nInvalid choice. Please try again.");
                                break;
                        }
                    }
                    break;

                case 9:
                    FileHandler.savePlaylist(list);
                    break;
 
                case 0:
                    break;
 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
 
            System.out.println();
 
        } while (choice != 0);
 
        FileHandler.savePlaylist(list);
        scanner.close();
        System.out.println("Goodbye!");
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


/*
============================
        DOCUMENTATION
============================

--------------------------------------
1. Selected Linked-list implementation
--------------------------------------
    We decided to implement a Doubly Linked-list to manage the playlist. Where each node contains
        - next pointer which holds the address of the next song in the list, and
        - prev pointer which holds the address of the previous song in the list,
        - head tracker which tracks the first song in the list
        - tail tracker which tracks the last song in the list, and
        - currentSong tracker which tracks whatever song is currently reading/playing

    This is chosen because we achieved a much more efficient time complexity than our initial Singly Linked List.
        - O(1) Navigation by holding the address of the neighboring songs in the playlist from next and prev pointers
        - O(1) Insertion by keeping track of the tail or the end of the song, so we don't have to loop the entire playlist
          to search for the last song (which was our initial method)

--------------------------------------
2. File Format Used
--------------------------------------
    For saving and loading, we used a simple txt file (playlist.txt). where each line is a single song detail and used
    "|" a pipe character as our delimiters (for example: title|artist|duration|album).

    When loading the playlist, our FileHandler reads each line and splits the attributes with string.split("\\|") and temporarily
    store the song attributes in a String[] Array. We implemented a lenght validation where if(data.lenght == 4) before inserting
    the data into our Linked List. This will make sure that our program will not crash with an Index Out of Bounds error.

--------------------------------------
3. General Program Design
--------------------------------------
    To keep our source code organized and clean we separated our code into 3 modular classes.
    - Main.java handles the user menus and input validation,
    - Songlist.java manages the linked-list operations/data structure,
    - FileHandler.java handles reading and writing playlist data into a text file, by using Java's FileReader and FileWriter.
    This ensures that UI, File handling, and backend logic are handled independently
 */