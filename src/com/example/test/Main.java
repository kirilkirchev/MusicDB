package com.example.test;

import com.example.test.model.Artist;
import com.example.test.model.Datasource;
import com.example.test.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
//        List<Artist> artists = datasource.queryArtists(datasource.ORDER_BY_ASC);
//        if (artists == null) {
//            System.out.println("No artists");
//            return;
//            }
//
//        for (Artist artist : artists) {
//            System.out.println("ID = " + artist.getId() + ", name = " + artist.getName());
//        }

        //List<String> albumsFromArtist = datasource.queryAlbumsForArtist("Pink Floyd", Datasource.ORDER_BY_ASC);
//        for(String album : albumsFromArtist) {
//            System.out.println(album);
//        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if(songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track = " + artist.getTrack());
        }


        datasource.querySongsMetaData();
        int count = datasource.getCount(datasource.TABLE_ARTISTS);
        datasource.creteViewForSongArtist();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scanner.nextLine();

        songArtists = datasource.querySongInfoView(title);
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for(SongArtist artist : songArtists) {
            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                    " Album name = " + artist.getAlbumName() +
                    " Track number = " + artist.getTrack());
        }

        datasource.close();
    }
}
