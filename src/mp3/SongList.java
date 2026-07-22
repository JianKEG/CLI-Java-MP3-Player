package mp3;

public class SongList {
    Node head;
    Node tail;
    Node currentSong;

    static class Node {
        String title, artist, duration, album;
        Node next;
        Node prev;

        Node(String t,String a,String d, String ab) {
            title = t;
            artist = a;
            duration = d;
            album = ab;
            next = null;
            prev = null;
        }
    }

    public String formatDuration(String duration){
        int intDuration = Integer.parseInt(duration);

        if(intDuration >= 3600){
            int hrs =  intDuration / 3600;
            int mins = (intDuration % 3600) / 60;
            int secs = intDuration % 60;

            return String.format("%d:%02d:%02d", hrs, mins, secs);
        }

        int mins = intDuration/60;
        int secs = intDuration % 60;

        return String.format("%d:%02d", mins, secs);
    }

    public static void insert(SongList list, String title, String artist, String duration, String album){
        Node new_node = new Node(title, artist, duration, album);

        if(list.head == null){
            list.head = new_node;
            list.tail = new_node;
        }else{
            list.tail.next = new_node;
            new_node.prev = list.tail;
            list.tail = new_node;
        }
    }

    public static void deleteByKey(SongList list, String key){
        Node currNode = list.head;
        boolean found = false;

        if (list.head == null) {
            System.out.println("The Playlist is empty.");
            return;
        }

        while (currNode != null) {
            if (currNode.artist.equalsIgnoreCase(key) || currNode.title.equalsIgnoreCase(key) || currNode.album.equalsIgnoreCase(key)) {
                if (currNode.prev == null) {
                    list.head = currNode.next;
                } else {
                    currNode.prev.next = currNode.next;
                }

                if (currNode.next == null){
                    list.tail = currNode.prev;
                } else {
                    currNode.next.prev = currNode.prev;
                }

                found = true;

            }

            currNode = currNode.next;
        }

        if (found) {
            System.out.println("Song removed successfully.");
        } else {
            System.out.println(key + " not found.");
        }
    }

    public static void deleteById(SongList list, int id) {
        if (list.head == null) {
            System.out.println("The list is empty.");
            return;
        }

        if (id == 1) {
            System.out.println("Song '" + list.head.title + "' deleted.");
            list.head = list.head.next;

            if (list.head != null){
                list.head.prev = null;
            } else {
                list.tail = null;
            }

            return;
        }

        Node currNode = list.head;
        int currentId = 1;

        while (currNode != null && currentId < id) {
            currNode = currNode.next;
            currentId++;
        }

        if (currNode == null) {
            System.out.println("Song number " + id + " not found.");
            return;
        }

        System.out.println("Song '" + currNode.title + " by " + currNode.artist + "' deleted.");

        currNode.prev.next = currNode.next;

        if(currNode.next != null){
            currNode.next.prev = currNode.prev;
        } else {
            list.tail = currNode.prev;
        }
    }

    public static void search (SongList list, String key){
        Node currNode = list.head;
        boolean found = false;
        boolean dontLoop = false;

        while(currNode != null){
            if (currNode.title.equalsIgnoreCase(key)) {
                if(!dontLoop) {
                    System.out.println("\nSearch by title: " + key);
                    System.out.println("\nSong found!");
                    dontLoop = true;
                }

                System.out.println("Title    : " + currNode.title);
                System.out.println("Artist   : " + currNode.artist);
                System.out.println("Duration : " + list.formatDuration(currNode.duration));

                found = true;
            }

            if(currNode.artist.equalsIgnoreCase(key)){
                if(!dontLoop) {
                    System.out.println("\nSearch by artist: " + key);
                    System.out.println("\nSongs found by artists: ");
                    dontLoop = true;
                }

                System.out.println("\nTitle    : " + currNode.title);
                System.out.println("Artist   : " + currNode.artist);
                System.out.println("Duration : " + list.formatDuration(currNode.duration));
                found = true;
            }

            if(currNode.album.equalsIgnoreCase(key)){
                if(!dontLoop) {
                    System.out.println("\nSearch by album: " + key);
                    System.out.println("\nSongs found by album: ");
                    dontLoop = true;
                }

                System.out.println("\nTitle    : " + currNode.title);
                System.out.println("Artist   : " + currNode.artist);
                System.out.println("Duration : " + list.formatDuration(currNode.duration));
                found = true;
            }


            currNode = currNode.next;
        }

        if(!found){
            System.out.println("\nSong not found!");
        }
    }

    public static int countSongs (SongList list){
        int count = 0;
        Node currNode = list.head;

        while(currNode != null){
            count += 1;
            currNode = currNode.next;
        }

        return count;
    }

    public static String totalDuration (SongList list){
        int totalDur = 0;

        Node currNode = list.head;

        while(currNode != null){
            totalDur += Integer.parseInt(currNode.duration);
            currNode = currNode.next;
        }

        if(totalDur >= 3600){
            int hrs =  totalDur / 3600;
            int mins = (totalDur % 3600) / 60;
            int secs = totalDur % 60;

            return String.format("%d:%02d:%02d", hrs, mins, secs);
        }

        int mins = totalDur/60;
        int secs = totalDur % 60;

        return String.format("%d:%02d", mins, secs);
    }

    public static void printSongList(SongList list){
        Node currNode = list.head;
        System.out.println("==================\n\tPLAYLIST\n==================");
        int i = 1;
        while (currNode != null){
            System.out.println();
            System.out.println(i + ". " + currNode.title + " by " + currNode.artist + " ");
            System.out.println("   " + list.formatDuration(currNode.duration));
            currNode = currNode.next;
            i++;
        }
    }

    public static void printFirstSong(SongList list){
        if(list.head == null){
            System.out.println("The playlist is empty");
            return;
        }

        System.out.println("First Song: " + list.head.title + " by " + list.head.artist + " [" + list.formatDuration(list.head.duration)+"]");
    }

    public static void printLastSong(SongList list){
        if(list.tail == null){
            System.out.println("The playlist is empty");
            return;
        }

        System.out.println("Last Song: " + list.tail.title + " by " + list.tail.artist + " [" + list.formatDuration(list.tail.duration)+"]");
    }

    public static void nextSong(SongList list){
        if(list.head == null){
            System.out.println("The playlist is empty.");
            return;
        }

        if(list.currentSong == null){
            list.currentSong = list.head;
            System.out.println("Selected: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
            return;
        }

        if(list.currentSong.next != null){
            list.currentSong = list.currentSong.next;
            System.out.println("[NEXT] Skipping song...");
            System.out.println("\nSelected: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
        }else{
            System.out.println("You are now already at the end of the playlist.");
        }
    }

    public static void prevSong(SongList list){
        if(list.head == null){
            System.out.println("The playlist is empty.");
            return;
        }

        if(list.currentSong.prev == null){
            System.out.println("You are now already at the beginning of the playlist.");
            return;
        }

        list.currentSong = list.currentSong.prev;
        System.out.println("[PREV] Skipping song...");
        System.out.println("\nSelected: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
    }

    public static void selectCurrentSong(SongList list){
        if (list.head == null){
            System.out.println("The playlist is empty.");
            return;
        }

        if(list.currentSong == null){
            list.currentSong = list.head;
            System.out.println("Current song: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
        }

        System.out.println("Current song: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
    }

    public static void playCurrentSong(SongList list){
        if (list.head == null){
            System.out.println("The playlist is empty.");
            return;
        }

        if(list.currentSong == null){
            list.currentSong = list.head;
            System.out.println("Now playing: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
            return;
        }

        System.out.println("Now playing: " + list.currentSong.title + " by " + list.currentSong.artist + " [" + list.formatDuration(list.currentSong.duration) + "]");
    }
}
