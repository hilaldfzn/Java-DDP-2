package Lab08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class PacilfyMerger {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    private static Song[] songs = new Song[0];

    public static void main(String[] args) {
        try {
            out.print("File playlist pertama: ");
            var firstFile = openFile(in.nextLine());
            verifyAndInsert(firstFile);
            firstFile.close();

            out.print("File playlist kedua: ");
            var secondFile = openFile(in.nextLine());
            verifyAndInsert(secondFile);
            secondFile.close();

            out.print("File playlist output: ");
            var outputFile = in.nextLine();
            writeOutput(outputFile);

            out.printf("Berhasil menimpa playlist, jumlah lagu adalah: %d", songs.length);
        } catch (FileNotFoundException e) {
            out.println("File tidak ditemukan!");
        } catch (InvalidPlaylistException e) {
            out.println("Playlist tidak valid!");
        }
    }

    private static Scanner openFile(String filePath) throws FileNotFoundException {
        var file = new File(filePath);
        return new Scanner(file);
    }

    private static void writeOutput(String path) throws FileNotFoundException {
        var file = new File(path);
        var writer = new PrintWriter(file);
        for (var song : songs) {
            writer.printf("%s||%s%n", song.getArtist(), song.getTitle());
        }
        writer.close();
    }

    private static void verifyAndInsert(Scanner playlistFile) throws InvalidPlaylistException {
        while (playlistFile.hasNextLine()) {
            var line = playlistFile.nextLine();
            var splitted = line.split("\\|\\|");
            if (splitted.length != 2)
                throw new InvalidPlaylistException();

            var artist = splitted[0];
            var title = splitted[1];
            if (artist.equals("") || title.equals("")
                    || artist.startsWith("|") || artist.endsWith("|")
                    || title.startsWith("|") || title.endsWith("|"))
                throw new InvalidPlaylistException();

            var newSong = new Song(artist, title);
            if (!exists(newSong))
                songs = append(songs, newSong);
        }
    }

    private static boolean exists(Song elem) {
        for (var song : songs) {
            if (song.getArtist().equals(elem.getArtist()) && song.getTitle().equals(elem.getTitle()))
                return true;
        }
        return false;
    }

    private static Song[] append(Song[] arr, Song element) {
        var arrLen = arr.length;
        var newArr = Arrays.copyOf(arr, arrLen + 1);
        newArr[arrLen] = element;
        return newArr;
    }
}
