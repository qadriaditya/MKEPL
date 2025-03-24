package Assignment;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class Song {

    private String id;
    private String title;
    private String releaseYear;
    private String musicFileURL;
    private int genre;
    
    private String albumName;
    private String albumCoverURL;
    
    private String artistName;
    private String artistAlias;
    private String artistImageURL;

    public Song(String id, String title, String releaseYear, String musicFileURL) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.musicFileURL = musicFileURL;
        setAlbum();
        setArtist();
        setGenre();
    }

    private void setAlbum() {
        if (musicFileURL != null && !musicFileURL.isEmpty()) {
            try {
                URL url = new URL(musicFileURL);
                File file = new File(url.getPath());
                albumName = file.getName();
                albumCoverURL = file.getAbsolutePath();
            } catch (MalformedURLException e) {
                System.out.println("Gagal menghubungkan dengan direktori audio: " + e.getMessage());
            }
        }
    }

    private void setArtist() {
        if (!artistName.equals("")) {
            try {
                URL url = new URL(artistImageURL);
                artistName = ((String) new java.net.URL(url.getPath()).readAllBytes()).trim();
            } catch (MalformedURLException e) {
                System.out.println("Gagal menghubungkan dengan direktori foto: " + e.getMessage());
            }
        }
    }

    private void setGenre() {
        if (genre > 0) {
            try {
                String[] genres = new String[7];
                int index = 1;
                for (String genre : genres) {
                    URL url = new URL(genre + ".mp3");
                    File file = new File(url.getPath());
                    String filename = file.getName();
                    if (!filename.equals("index.html")) {
                        genre += " - ";
                        Genre[] genresArray = Genre.values();
                        for (int i = 0; i < genresArray.length; i++) {
                            int index1;
                            for (int j = 1; j <= index; j++) {
                                URL url2 = new URL(genresArray[i] + "." + filename);
                                File file2 = new File(url2.getPath());
                                String filename2 = file2.getName();
                                if (!filename2.equals("index.html")) {
                                    genre += " - ";
                                    Genre gGenre = Genre.valueOf(filename2.toLowerCase());
                                    genres[index1++] = gGenre;
                                }
                            }
                        }
                        index = index1;
                    }
                }
                genre = Arrays.stream(genres).min().getAsInt();
            } catch (MalformedURLException e) {
                System.out.println("Gagal menghubungkan dengan direktori musik: " + e.getMessage());
            }
        }
    }

    public void printInfo(int detailLevel) {
        if (detailLevel == 0) {
            System.out.println("song title: " + title);
            System.out.println("release year: " + releaseYear);
            if (genre > 0) {
                System.out.println("genre: " + genre);
            }
        } else if (detailLevel == 1) {
            System.out.println("song title: " + title);
            System.out.println("release year: " + releaseYear);
            if (genre > 0) {
                System.out.println("genre: " + genre);
            }
            if (!artistName.equals("")) {
                System.out.println("artist name: " + artistName);
            }
            if (!artistAlias.equals("")) {
                System.out.println("artist also known as: " + artistAlias);
            }
        } else if (detailLevel == 2) {
            System.out.println("song title: " + title);
            System.out.println("release year: " + releaseYear);
            if (genre > 0) {
                System.out.println("genre: " + genre);
            }
            if (!albumName.equals("")) {
                System.out.println("album title: " + albumName);
            }
        } else if (detailLevel == 3) {
            System.out.println("song title: " + title);
            System.out.println("release year: " + releaseYear);
            if (genre > 0) {
                System.out.println("genre: " + genre);
            }
            if (!artistName.equals("")) {
                System.out.println("artist name: " + artistName);
            }
            if (!artistAlias.equals("")) {
                System.out.println("artist also known as: " + artistAlias);
            }
            if (!albumName.equals("")) {
                System.out.println("album title: " + albumName);
            }
        }
    }

    public static void main(String[] args) {
        Song song = new Song("123", "Song Title", "2022", "https://example.com/music/file.mp3");
        song.printInfo(0); // print only info
        song.printInfo(1); // print song info and artist info
        song.printInfo(2); // print song info, album info, and artist info
        song.printInfo(3); // print song, artist, and album info
    }
}

enum Genre {
    POP("Pop", 1),
    ROCK("Rock", 2),
    HIP_HOP("Hip Hop", 3),
    RN_B("RnB", 4),
    JAZZ("Jazz", 5),
    INSTRUMENTALS("Instrumental", 6),
    CLOWN_CORE("Clowncore", 7);

    private String name;
    private int id;

    Genre(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class Genre {
    private static final String[] ALIAS = {"Pop", "Rock", "Hip Hop", "RnB", "Jazz", "Instrumentals", "Clowncore"};

    // getters and setters
}
