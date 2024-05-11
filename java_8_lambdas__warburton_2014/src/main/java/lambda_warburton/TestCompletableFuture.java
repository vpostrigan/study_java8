package lambda_warburton;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TestCompletableFuture {

    public static void main(String[] args) throws Exception {
        String albumName = "albumName";
        CompletableFuture<List<String>> artistLookup = loginTo("artist")
                .thenCompose(artistLogin -> lookupArtists(albumName, artistLogin));
        System.out.println(new Date() + " p1>");
        CompletableFuture<List<String>> trackstLookup = loginTo("track")
                .thenCompose(trackLogin -> lookupTracks(albumName, trackLogin));
        System.out.println(new Date() + " p2>");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = trackstLookup.thenCombine(artistLookup, (tracks, artists) -> {
            tracks.forEach(s0 -> System.out.println("Final: " + s0));
            artists.forEach(s0 -> System.out.println("Final: " + s0));
            return "";
        }).join();
        System.out.println(new Date() + " p3>");

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + " end.");
    }

    private static CompletableFuture<String> loginTo(String name) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date() + " login: " + name);
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " login end: " + name);
            return "login>" + name;
        });
    }

    private static CompletableFuture<List<String>> lookupArtists(String name, String login) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date() + " lookupArtists: " + name);
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " lookupArtists end: " + name);
            return Arrays.asList(name + "#lookupArtists");
        });
    }

    private static CompletableFuture<List<String>> lookupTracks(String name, String login) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date() + " lookupTracks: " + name);
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " lookupTracks end: " + name);
            return Arrays.asList(name + "#lookupTracks");
        });
    }

}
