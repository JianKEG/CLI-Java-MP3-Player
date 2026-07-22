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
 
        FileHandler.loadPlaylist(list);
 
        int choice;
        
        do {
            
            System.out.println("================================");
            System.out.println("        SPOTIFEE PLAYER");
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
                        System.out.print("Enter title, artist, or album to delete: ");
                        String key = scanner.nextLine().trim();
                        SongList.deleteByKey(list, key);
                    } else {
                        System.out.println("Invalid choice. No song removed.");
                    }
                    break;
                //Playlist information
                case 8:
                    System.out.println("Total songs: " + SongList.countSongs(list));
                    System.out.println("Total duration: " + SongList.totalDuration(list));
                    break;
                //Saveing Playlist
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


