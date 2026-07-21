package mp3;

public class SongList {
    Node head;

    static class Node {
        String title, artist, duration, album;
        Node next;

        Node(String t,String a,String d, String ab) {
            title = t;
            artist = a;
            duration = d;
            album = ab;
            next = null;
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
        }else{
            Node last = list.head;

            while(last.next != null){
                last = last.next;
            }

            last.next = new_node;
        }
    }

    public static void deleteByKey(SongList list, String key){
        Node currNode = list.head, prev = null;
        boolean found = false;

        if (list.head == null) {
            System.out.println("The list is empty.");
            return;
        }

        while (currNode != null) {
            if (currNode.artist.equalsIgnoreCase(key) || currNode.title.equalsIgnoreCase(key) || currNode.album.equalsIgnoreCase(key)) {
                if (prev == null) {
                    list.head = currNode.next;
                } else {
                    prev.next = currNode.next;
                }

                found = true;

            } else {
                prev = currNode;
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
            return;
        }

        Node currNode = list.head;
        Node prev = null;
        int currentId = 1;

        while (currNode != null && currentId < id) {
            prev = currNode;
            currNode = currNode.next;
            currentId++;
        }

        if (currNode == null) {
            System.out.println("Song number " + id + " not found.");
            return;
        }

        prev.next = currNode.next;
        System.out.println("Song '" + currNode.title + " by " + currNode.artist + "' deleted.");
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
}
